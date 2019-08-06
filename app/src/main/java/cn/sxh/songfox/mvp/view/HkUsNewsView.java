package cn.sxh.songfox.mvp.view;

import java.util.List;

import cn.sxh.songfox.base.BaseView;
import cn.sxh.songfox.mvp.entity.NewsBean;

/**
 * @package-name: cn.sxh.songfox.mvp.view
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/7/22 0022 : 14 :44
 * @project-name: songFox
 */
public interface HkUsNewsView extends BaseView {
    void initListViews(List<NewsBean> news);
}
