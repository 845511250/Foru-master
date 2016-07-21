package com.pro.foru.bean;

import com.google.gson.JsonObject;

/**
 * Created by zuoyun on 2016/7/19.
 */
public class MyBaseResponse<T> {
    public String code;
    public String msg;
    public T data;
}
