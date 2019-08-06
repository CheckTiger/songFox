package cn.sxh.songfox.mvp.model;

import java.util.List;

import cn.sxh.songfox.mvp.entity.NewsBean;

/**
 * @package-name: cn.sxh.songfox.mvp.model
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/22 0022 : 14 :48
 * @project-name: songFox
 */
public interface HkUsNewsModel {

    void requestNews();

    void receiveSuccess(List<NewsBean> newsBeans);
}
