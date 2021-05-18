package cn.sxh.common.fingerprint;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public abstract class BaseFingerprint {
    private Context mContext;
    private Handler mHandler;
    private IdentifyListener mIdentifyListener;
    private ExceptionListener mExceptionListener;

    private int mNumbersOfFailures = 0;
    private int mMaxAvailableTimes = 3;

    private boolean mIsHardwareEnable = false;
    private boolean mIsRegisteredFingerprint = false;
    private boolean mIsCalledStartIdentify = false;
    private boolean mIsCancelIdentify = false;

    public BaseFingerprint(Context mContext, ExceptionListener exceptionListener) {
        this.mContext = mContext;
        this.mExceptionListener = exceptionListener;
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void startIdentify(IdentifyListener identifyListener, int maxAvailableTimes) {
        this.mIdentifyListener = identifyListener;
        this.mMaxAvailableTimes = maxAvailableTimes;

        mNumbersOfFailures = 0;
        mIsCalledStartIdentify = true;
        mIsCancelIdentify = false;

        doIdentify();
    }

    public void resumeIdentify() {
        if (mIsCalledStartIdentify && mIdentifyListener != null
                && mNumbersOfFailures < mMaxAvailableTimes) {
            mIsCancelIdentify = false;
            doIdentify();
        }
    }

    public void cancelIdentify(){
        mIsCancelIdentify = true;
        doCancelIdentify();
    }

    protected void onSucceed(){
        if (mIsCancelIdentify) {
            return;
        }
        mNumbersOfFailures = mMaxAvailableTimes;
        if (mIdentifyListener != null) {
            runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    mIdentifyListener.onSucceed();
                }
            });
        }

        cancelIdentify();
    }

    protected void onNotMatch(){
        if (mIsCancelIdentify) {
            return;
        }

        if (++mNumbersOfFailures < mMaxAvailableTimes) {
            if (mIdentifyListener != null) {
                final int chancesLeft = mMaxAvailableTimes - mNumbersOfFailures;
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        mIdentifyListener.onNotMatch(chancesLeft);
                    }
                });
            }

            if (needToCallDoIdentifyAgainAfterNotMatch()) {
                doIdentify();
            }
            return;
        }

        onFailed(false);
    }

    private void onFailed(final boolean isDeviceLocked) {
        if (mIsCancelIdentify) {
            return;
        }
        final boolean isStartFailedByDeviceLocked = isDeviceLocked && mNumbersOfFailures == 0;
        mNumbersOfFailures = mMaxAvailableTimes;
        if (mIdentifyListener != null) {
            runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    if (isStartFailedByDeviceLocked) {
                        mIdentifyListener.onStartFailedByDeviceLocked();
                    } else {
                        mIdentifyListener.onFailed(isDeviceLocked);
                    }
                }
            });
        }
        cancelIdentify();

    }


    protected void onCatchException(Throwable throwable) {
        if (mExceptionListener != null && throwable != null) {
            mExceptionListener.onCatchException(throwable);
        }
    }

    public boolean isEnable(){
        return mIsHardwareEnable && mIsRegisteredFingerprint;
    }

    public boolean isHardwareEnable() {
        return mIsHardwareEnable;
    }

    public void setHardwareEnable(boolean mIsHardwareEnable) {
        this.mIsHardwareEnable = mIsHardwareEnable;
    }

    public boolean isRegisteredFingerprint() {
        return mIsRegisteredFingerprint;
    }

    public void setRegisteredFingerprint(boolean mIsRegisteredFingerprint) {
        this.mIsRegisteredFingerprint = mIsRegisteredFingerprint;
    }

    protected void runOnUiThread(Runnable runnable) {
        mHandler.post(runnable);
    }

    public boolean needToCallDoIdentifyAgainAfterNotMatch(){
        return true;
    }


    public interface IdentifyListener{
        void onSucceed();

        void onNotMatch(int availableTimes);

        void onFailed(boolean isDeviceLocked);

        void onStartFailedByDeviceLocked();
    }

    public interface ExceptionListener{
        void onCatchException(Throwable exception);
    }

    protected abstract void doCancelIdentify();

    protected abstract void doIdentify();
}
