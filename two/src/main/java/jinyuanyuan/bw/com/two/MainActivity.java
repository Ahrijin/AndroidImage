package jinyuanyuan.bw.com.two;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jinyuanyuan.bw.com.two.bean.LoginBean;
import jinyuanyuan.bw.com.two.presenter.PresenterImpls;
import jinyuanyuan.bw.com.two.utils.Contacts;
import jinyuanyuan.bw.com.two.view.IView;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.imgs)
    ImageView imgs;
    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_pass)
    EditText loginPass;
    @BindView(R.id.new_resiger)
    TextView newResiger;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.qq_login)
    ImageView qqLogin;
    private PresenterImpls presenterImpls;
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenterImpls = new PresenterImpls(this);
    }

    @OnClick({R.id.new_resiger, R.id.login, R.id.qq_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.new_resiger://新用户注册
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
                break;
            case R.id.login://登录
                String tels = loginPhone.getText().toString().trim();
                String pass = loginPass.getText().toString().trim();
                if (tels.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(this, "手机号或密码不能为空！", Toast.LENGTH_SHORT).show();
                }
                map.put("mobile", tels);
                map.put("password", pass);
                presenterImpls.getRequest(Contacts.USER_LOGIN_URL, map, LoginBean.class);
                break;
            case R.id.qq_login://qq 登录

                break;
        }
    }

    @Override
    public void Success(Object daa) {
        LoginBean loginBean = (LoginBean) daa;
        if(loginBean.getMsg().equals("登录成功")){
            Toast.makeText(this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,Main3Activity.class));
        }
    }

    @Override
    public void Error(Object err) {
        Toast.makeText(this, err.toString(), Toast.LENGTH_SHORT).show();
    }
}
