package jinyuanyuan.bw.com.two.view;

/*
 *Author:Ahri_Love
 *Date:2019/1/14
 */public interface IView<T> {
     void Success(T daa);
     void Error(T err);
}
