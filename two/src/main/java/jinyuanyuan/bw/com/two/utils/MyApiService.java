package jinyuanyuan.bw.com.two.utils;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/*
 *Author:Ahri_Love
 *Date:2019/1/14
 */
public interface MyApiService {
    @GET
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, Object> map);

    //上传文件
    @Multipart
    @POST
    Observable<ResponseBody> post(@Url String url, @QueryMap Map<String, Object> map, @Part MultipartBody.Part file);


}
