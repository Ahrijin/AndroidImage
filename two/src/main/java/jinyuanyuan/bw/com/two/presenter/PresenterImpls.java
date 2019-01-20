package jinyuanyuan.bw.com.two.presenter;

import java.util.Map;

import jinyuanyuan.bw.com.two.callback.MyCallBack;
import jinyuanyuan.bw.com.two.model.ModelImpls;
import jinyuanyuan.bw.com.two.view.IView;
import okhttp3.MultipartBody;


/*
 *Author:Ahri_Love
 *Date:2019/1/14
 */public class PresenterImpls implements Presenter {
    private IView iView;
    private ModelImpls modelImpls;

    public PresenterImpls(IView iView) {
        this.iView = iView;
        modelImpls = new ModelImpls();
    }

    @Override
    public void getRequest(String url, Map<String, Object> map, Class clazz) {
        modelImpls.get(url, map, clazz, new MyCallBack() {
            @Override
            public void setSuceess(Object suceess) {
                iView.Success(suceess);
            }

            @Override
            public void setError(Object error) {
                iView.Error(error);
            }
        });
    }

    @Override
    public void postRequest(String url, Map<String, Object> map,MultipartBody.Part body, Class clazz) {
        modelImpls.post(url, map, clazz, body, new MyCallBack() {
            @Override
            public void setSuceess(Object suceess) {
                iView.Success(suceess);
            }

            @Override
            public void setError(Object error) {
                iView.Error(error);
            }
        });
    }


    public void destory() {
        if (iView != null) {
            iView = null;
        }
        if (modelImpls != null) {
            modelImpls = null;
        }
    }
}
