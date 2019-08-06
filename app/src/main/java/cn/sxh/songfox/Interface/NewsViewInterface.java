package cn.sxh.songfox.Interface;

import java.util.List;

import cn.sxh.songfox.mvp.entity.NewsBean;

/**
 * @package-name: cn.sxh.songfox.Interface
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/22 0022 : 14 :47
 * @project-name: songFox
 */
public interface NewsViewInterface {

    void showNews(List<NewsBean> news);

    void showLoading();

    void hideLoading();

}
