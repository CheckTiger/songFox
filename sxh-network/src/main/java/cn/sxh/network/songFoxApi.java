package cn.sxh.network;

import cn.sxh.network.bean.ThsNewsBean;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface songFoxApi {

    @GET("adHomePage?platform=gphone")
    Observable<ThsNewsBean> getThsNews();

    @GET("users/rengwuxian/repos")
    Call<ResponseBody> listRepos();

}
