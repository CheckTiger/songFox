package cn.sxh.network;

import cn.sxh.network.bean.ThsNewsBean;
import io.reactivex.Observable;
import retrofit2.http.GET;

public interface songFoxApi {

    @GET("adHomePage?platform=gphone")
    Observable<ThsNewsBean> getThsNews();

}
