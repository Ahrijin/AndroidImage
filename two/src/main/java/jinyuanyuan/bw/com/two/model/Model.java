package jinyuanyuan.bw.com.two.model;

import java.util.Map;

import jinyuanyuan.bw.com.two.callback.MyCallBack;
import okhttp3.MultipartBody;


/*
 *Author:Ahri_Love
 *Date:2019/1/14
 */public interface Model {
     void get(String url, Map<String, Object> map, Class clazz, MyCallBack callBack);

    void post(String url, Map<String, Object> map, Class clazz, MultipartBody.Part body, MyCallBack callBack);
}
