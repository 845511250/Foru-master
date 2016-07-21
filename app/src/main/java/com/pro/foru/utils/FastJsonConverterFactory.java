package com.pro.foru.utils;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;


import retrofit.Converter;

/**
 * Created by zuoyun on 2016/7/21.
 */
public class FastJsonConverterFactory extends  Converter.Factory {
    public static FastJsonConverterFactory create() {
        return new FastJsonConverterFactory();
    }

    /**
     * 需要重写父类中responseBodyConverter，该方法用来转换服务器返回数据
     */
    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return new FastJsonRequestBodyConverter<>();
    }

    /**
     * 需要重写父类中responseBodyConverter，该方法用来转换发送给服务器的数据//FastJsonResponseBodyConverter
     */

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new FastJsonResponseBodyConverter<>(type);
    }
}