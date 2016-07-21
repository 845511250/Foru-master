package com.pro.foru.net;

import com.pro.foru.activity.fragment.NetFragment;
import com.pro.foru.bean.MyBaseResponse;
import com.pro.foru.bean.ShopList;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by hjy on 16/6/22.
 */
public interface IRetrofitService {
    @GET("selectshop.php")
    Observable<ShopList> getshop();

    @POST("api/v1/login")
    Observable<BaseResponse> login(@Query("email") String email, @Query("password") String password, @Query("key") String key);

    @POST("api/v1/register")
    Observable<BaseResponse> register(@Query("email") String email, @Query("password") String password, @Query("key") String key);

}

