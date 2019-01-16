package com.jqhee.latte.core.net;

import com.jqhee.latte.core.app.ConfigKeys;
import com.jqhee.latte.core.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCreator {

    // 惰性加载, 静态内部类

    public  static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public  static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RetrofitHolder {
        private  static  final  String BASE_URL = (String) Latte.getLatteConfigurations().get(ConfigKeys.API_HOST.name());

        // 转换器 ScalarsConverterFactory.create()
        private  static  final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OKHttpHolder {
        // 网络请求超时时间
        private  static  final  int TIME_OUT = 60;
        // TimeUnit.SECONDS 以秒为单位
        // okhttp可以添加拦截器
        private  static  final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();

    }

    private static final class RestServiceHolder {
        private  static  final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT
                .create(RestService.class);
    }
}