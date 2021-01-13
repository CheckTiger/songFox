package cn.sxh.network.observer

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class BaseObserver<T> : Observer<T> {

    override fun onError(e: Throwable?) {
        onFailure(e)
    }

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onSubscribe(d: Disposable?) {
    }

    override fun onComplete() {
    }

    abstract fun onSuccess(t:T)
    abstract fun onFailure(e: Throwable?)
}