package cn.sxh.technology.presenter

import cn.sxh.base.FirstPageBean
import cn.sxh.technology.model.NewsModel
import cn.sxh.technology.model.impl.NewsModelImpl
import cn.sxh.technology.view.NewsView

class NewsPresenter(var newsView: NewsView):NewsModel{

    private var newsModel: NewsModelImpl? = null

    init {
        newsModel = NewsModelImpl(this)
    }

    fun requestNews(){
        newsModel?.requestNews()
    }

    override fun notifyHandleNewsData(news: FirstPageBean) {
        newsView.notifyHandleNewsData(news)
    }

}