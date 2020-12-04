package cn.sxh.common.API;

import cn.sxh.base.FirstPageBean;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @package-name: cn.sxh.common.API
 * @auther:snowFox
 * @Email:snowTigersong@gmail.com
 * @time: 2019/10/24 0024 : 10 :20
 * @project-name: songFox
 */
public interface songFoxApi {

    @GET("adHomePage?platform=gphone")
    Observable<FirstPageBean> getFirstPageData();
}
