package jinyuanyuan.bw.com.two.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/*
 *Author:Ahri_Love
 *Date:2019/1/14
 */public class RetrofitUtils {

    private final MyApiService myApiService;

    private RetrofitUtils() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(Contacts.BASE_URL)
                .build();
        myApiService = retrofit.create(MyApiService.class);
    }

    private static class RetrofitHolder {
        private static final RetrofitUtils util = new RetrofitUtils();
    }

    public static RetrofitUtils getInstance() {
        return RetrofitHolder.util;
    }

    //get
    public void get(String url, Map<String, Object> map, final HttpListener listener) {
        Observer observer = new Observer<ResponseBody>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (listener != null) {
                    listener.setError(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if (listener != null) {
                    try {
                        listener.setSuccess(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        myApiService.get(url, map)
                .subscribeOn(Schedulers.io())//发射（IO就是）子线程
                .observeOn(AndroidSchedulers.mainThread())//运行主线程
                .subscribe(observer);
    }

    //post
    public void post(String url, Map<String, Object> map, MultipartBody.Part body, final HttpListener listener) {
        Observer observer = new Observer<ResponseBody>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (listener != null) {
                    listener.setError(e.getMessage());
                }
            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if (listener != null) {
                    try {
                        listener.setSuccess(responseBody.string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        myApiService.post(url, map, body)
                .subscribeOn(Schedulers.io())//发射（IO就是）子线程
                .observeOn(AndroidSchedulers.mainThread())//运行主线程
                .subscribe(observer);
    }

    public interface HttpListener {
        void setSuccess(String jsonStr);

        void setError(String error);
    }

}
