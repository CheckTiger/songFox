package cn.sxh.songfox.mvp.presenter;

import cn.sxh.base.UtilsFragmentView;
import cn.sxh.base.FirstPageBean;
import cn.sxh.songfox.mvp.model.Impl.UtilsFragmentModelImpl;
import cn.sxh.songfox.mvp.model.UtilsFragmentModel;

/**
 * @package-name: cn.sxh.songfox.mvp.presenter
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/10/24 0024 : 15 :21
 * @project-name: songFox
 */
public class UtilsFragmentPresenter implements UtilsFragmentModel {

    private UtilsFragmentModelImpl fragmentModel;
    private UtilsFragmentView view;
    public UtilsFragmentPresenter(UtilsFragmentView view) {
        this.view = view;
        fragmentModel = new UtilsFragmentModelImpl();
        fragmentModel.addUtilsFragmentModelListener(this::notifyDataReceive);
    }

    public void getFirstPageData() {
        fragmentModel.getFirstPageData();
    }

    @Override
    public void notifyDataReceive(FirstPageBean firstPageBean) {
        view.notifyDataReceive(firstPageBean);
    }
}
