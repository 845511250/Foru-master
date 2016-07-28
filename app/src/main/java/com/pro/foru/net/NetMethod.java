package com.pro.foru.net;

import com.pro.foru.config.Constants;
import com.pro.foru.utils.FastJsonConverterFactory;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zuoyun on 2016/7/15.
 */
public class NetMethod {
    private static NetMethod netMethod;
    public IRetrofitService retrofitService;

    public NetMethod(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constants.FORU_PATH)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        retrofitService = retrofit.create(IRetrofitService.class);
    }

    public static NetMethod getInstance() {
        if(null == netMethod)
            netMethod = new NetMethod();
        return netMethod;
    }
    public void login(Subscriber subscriber,String email,String password){
        retrofitService.login(email,password, Constants.ForU_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void register(Subscriber subscriber,String email,String password){
        retrofitService.register(email,password, Constants.ForU_key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
    public void getshop(Subscriber subscriber){
        retrofitService.getshop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
