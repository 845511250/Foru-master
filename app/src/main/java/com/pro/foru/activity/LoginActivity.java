package com.pro.foru.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pro.foru.bean.TokenClass;
import com.pro.foru.config.Constants;
import com.pro.foru.foru.R;
import com.pro.foru.net.BaseResponse;
import com.pro.foru.net.NetMethod;
import com.pro.foru.utils.F;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Subscriber;

public class LoginActivity extends Activity {
    Context context;
    @BindView(R.id.et_email)  EditText et_email;
    @BindView(R.id.et_password)  EditText et_password;
    @BindView(R.id.bt_login) Button bt_login;
    @BindView(R.id.bt_register)  Button bt_register;
    @BindView(R.id.bt_forgetpwd)  Button bt_forgetpwd;
    private String email,password,token;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        context=this;

        sharedPreferences=getSharedPreferences("userinfo",1);
    }

    @OnClick(R.id.bt_login)
    public void setBt_login(){
        email = et_email.getText().toString().trim();
        password = et_password.getText().toString().trim();
        if (email.equals("") || password.equals(""))
            Toast.makeText(getApplicationContext(), "Cant Be Empty!", Toast.LENGTH_SHORT).show();
        else
            gologin();
    }
    @OnClick(R.id.bt_register)
    public void setBt_register(){
        Intent intent = new Intent(context, RegisterActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.bt_forgetpwd)
    public void setBt_forgetpwd(){
        Intent intent = new Intent(context, ForgetpwdActivity.class);
        startActivity(intent);
    }

    //////////////////////////////
    private void gologin() {
        final long starttime = new Date().getTime();
        Subscriber subscriber=new Subscriber<BaseResponse>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                F.e("ONERROR");
            }

            @Override
            public void onNext(BaseResponse response) {
                if(response.code.equals("200")){
                    token=response.getData(TokenClass.class).token;
                    Toast.makeText(context,"succeed",Toast.LENGTH_SHORT).show();
                    F.e(token);
                }
                else {
                    Toast.makeText(context,response.msg,Toast.LENGTH_SHORT).show();
                    F.e(response.msg);
                }
                F.e("finish in "+(new Date().getTime()-starttime)+" ms");
            }
        };
        NetMethod.getInstance().login(subscriber,email,password, Constants.ForU_key);
    }

}