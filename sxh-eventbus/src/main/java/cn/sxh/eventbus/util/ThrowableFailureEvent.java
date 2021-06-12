package cn.sxh.eventbus.util;

public class ThrowableFailureEvent implements HasExecutionScope {

    protected final Throwable throwable;
    protected final boolean suppressErrorUi;
    protected Object executionContext;


    public ThrowableFailureEvent(Throwable throwable) {
        this.throwable = throwable;
        suppressErrorUi = false;
    }

    public ThrowableFailureEvent(Throwable throwable, boolean suppressErrorUi) {
        this.throwable = throwable;
        this.suppressErrorUi = suppressErrorUi;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public boolean isSuppressErrorUi() {
        return suppressErrorUi;
    }

    @Override
    public Object getExecutionScope() {
        return executionContext;
    }

    @Override
    public void setExecutionScope(Object executionScope) {
        this.executionContext = executionScope;
    }
}
