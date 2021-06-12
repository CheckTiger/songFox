package cn.sxh.technology.model.impl

import android.annotation.SuppressLint
import cn.sxh.common.API.RetrofitManager
import cn.sxh.technology.model.NewsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsModelImpl(var newsModel: NewsModel){

    @SuppressLint("CheckResult")
    fun requestNews(){
        RetrofitManager.getInstance().firstPageData
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    newsModel.notifyHandleNewsData(it)
                }
    }

}