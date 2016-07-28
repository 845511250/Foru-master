package com.pro.foru.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pro.foru.bean.TokenClass;
import com.pro.foru.bean.ShopList;
import com.pro.foru.foru.R;
import com.pro.foru.net.BaseResponse;
import com.pro.foru.net.NetMethod;
import com.pro.foru.utils.ACache;
import com.pro.foru.utils.F;
import com.trello.rxlifecycle.components.support.RxFragment;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

/**
 * Created by hjy on 16/6/4.
 */
public class NetFragment extends RxFragment {
    @BindView(R.id.tv_tip)
    public TextView mTip;
    @BindView(R.id.imageView)
    public ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nettest, null);
        ButterKnife.bind(this, view);
        Glide.with(this).load("http://goo.gl/gEgYUd").into(mImageView);
        ACache aCache = ACache.get(getActivity());
        return view;
    }
    public class Ttoken{
        String token;
    }
    @OnClick(R.id.btn_post)
    public void doPost() {
        final long starttime = new Date().getTime();
        Subscriber subscriber=new Subscriber<BaseResponse>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                F.e("ONERROR");
            }

            @Override
            public void onNext(BaseResponse response) {
                F.e(response.code);
                if(response.msg!=null)
                    F.e(response.msg);
                if(response.data!=null)
                    F.e(response.getData(TokenClass.class).token);
                F.e("finish in "+(new Date().getTime()-starttime)+" ms");
            }
        };
        NetMethod.getInstance().login(subscriber,"abc@qq.com","123456");
    }

    @OnClick(R.id.btn_get)
    public void doGet() {
        final long time1 = new Date().getTime();
        Subscriber subscriber = new Subscriber<ShopList>() {
            @Override
            public void onStart() {
                F.e("show progress"+(new Date().getTime()-time1)+"");
            }

            @Override
            public void onCompleted() {
                F.e("hide progress"+(new Date().getTime()-time1)+"");
            }

            @Override
            public void onError(Throwable e) {
                F.e("ONERROR");
            }

            @Override
            public void onNext(ShopList shopList) {
                F.e(shopList.shopData.get(0).shop_detail+(new Date().getTime()-time1)+"");
            }
        };
        NetMethod.getInstance().getshop(subscriber);

    }

}
