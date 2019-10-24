package cn.sxh.songfox.mvp.model.Impl;

import cn.sxh.songfox.API.RetrofitManager;
import cn.sxh.songfox.bean.FirstPageBean;
import cn.sxh.songfox.mvp.model.UtilsFragmentModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @package-name: cn.sxh.songfox.mvp.model.Impl
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/10/24 0024 : 15 :32
 * @project-name: songFox
 */
public class UtilsFragmentModelImpl {
    private UtilsFragmentModel model;
    public UtilsFragmentModelImpl() {

    }
    public void getFirstPageData() {
        RetrofitManager.getInstance().getFirstPageData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FirstPageBean>() {
                    @Override
                    public void accept(FirstPageBean firstPageBean) throws Exception {
                        model.notifyDataReceive(firstPageBean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void addUtilsFragmentModelListener(UtilsFragmentModel fragmentModel) {
        this.model = fragmentModel;
    }

}
