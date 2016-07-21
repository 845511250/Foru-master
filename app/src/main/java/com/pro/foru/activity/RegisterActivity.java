package com.pro.foru.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
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

public class RegisterActivity extends Activity {
    private Context context;
    @BindView(R.id.register_et_email) EditText et_email;
    @BindView(R.id.register_et_verifycode) EditText et_verifycode;
    @BindView(R.id.register_et_password) EditText et_password;
    @BindView(R.id.register_et_repassword) EditText et_repassword;
    @BindView(R.id.register_et_firstname) EditText et_firstname;
    @BindView(R.id.register_et_lastname) EditText et_lastname;
    @BindView(R.id.register_bt_getCode) Button bt_getCode;
    @BindView(R.id.register_bt_register) Button bt_register;

    private String email,verifycode,password,repassword,firstname,lastname;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        context=this;
    }

    @OnClick(R.id.register_bt_getCode)
    public void setBt_getCode(){

    }

    @OnClick(R.id.register_bt_register)
    public void setBt_registerr(){
        email = et_email.getText().toString().trim();
        verifycode = et_verifycode.getText().toString().trim();
        password = et_password.getText().toString().trim();
        repassword = et_repassword.getText().toString().trim();
        firstname = et_firstname.getText().toString().trim();
        lastname = et_lastname.getText().toString().trim();

        if(email.isEmpty()||verifycode.isEmpty()||password.isEmpty()||repassword.isEmpty()||firstname.isEmpty()||lastname.isEmpty())
            Toast.makeText(context,"Cant Be Empty!",Toast.LENGTH_SHORT).show();
        else if (!password.equals(repassword))
            Toast.makeText(context,"Password Not Same!",Toast.LENGTH_SHORT).show();
        else
            goregister();
    }

    //
    public void goregister() {
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
                    token = response.getData(TokenClass.class).token;
                    Toast.makeText(context,"succeed",Toast.LENGTH_SHORT).show();
                    F.e(token);
                }
                else{
                    Toast.makeText(context,response.msg,Toast.LENGTH_SHORT).show();
                    F.e(response.msg);
                }
            }
        };
        NetMethod.getInstance().register(subscriber,email,password, Constants.ForU_key);
    }

}

