package cn.sxh.songfox.mvp.presenter;

import java.util.Arrays;
import java.util.List;

import cn.sxh.songfox.AppContext;
import cn.sxh.songfox.R;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CoolWidgetFragmentPresenterImpl implements CoolWidgetFragmentContract.
        CoolWidgetFragmentPresenter {

    private CoolWidgetFragmentContract.CoolWidgetFragmentView view;
    private CompositeDisposable disposable = new CompositeDisposable();
    private Disposable mDisposable;


    @Override
    public void requestWidgetList() {
        if (mDisposable != null) {
            disposable.remove(mDisposable);
        }
        Observable observable = getRequestWidgetListObservable();

        mDisposable = (Disposable) observable.subscribeWith(getRequestWidgetListObserver());
        disposable.add(mDisposable);
    }

    private Observable getRequestWidgetListObservable() {
        return Observable.create((ObservableOnSubscribe) emitter -> {
            List<String> list = Arrays.asList(AppContext.getInstance().
                    getResources().getStringArray(R.array.all_defined_view));
            emitter.onNext(list);
        }).subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread());
    }


    private DisposableObserver<List<String>> getRequestWidgetListObserver() {
        return new DisposableObserver<List<String>>() {
            @Override
            public void onNext(List<String> o) {
                view.showWidgetList(o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

    @Override
    public void attachView(CoolWidgetFragmentContract.CoolWidgetFragmentView view) {
        this.view = view;
    }

    @Override
    public void unAttachView() {

        if (view != null) {
            view = null;
        }
    }
}
