package jinyuanyuan.bw.com.two;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jinyuanyuan.bw.com.two.bean.RegsiterBean;
import jinyuanyuan.bw.com.two.presenter.PresenterImpls;
import jinyuanyuan.bw.com.two.utils.Contacts;
import jinyuanyuan.bw.com.two.view.IView;

public class Main2Activity extends AppCompatActivity implements IView {

    @BindView(R.id.reg_name)
    EditText regName;
    @BindView(R.id.reg_pass)
    EditText regPass;
    @BindView(R.id.reg_m_pass)
    EditText regMPass;
    @BindView(R.id.reg_email)
    EditText regEmail;
    @BindView(R.id.reg)
    Button reg;
    @BindView(R.id.back)
    TextView back;
    private PresenterImpls presenterImpls;
    private Map<String, Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        presenterImpls = new PresenterImpls(this);
    }

    @OnClick({R.id.reg, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reg:
                String name = regName.getText().toString().trim();
                String pass = regPass.getText().toString().trim();
                String email = regEmail.getText().toString().trim();
                String quepass = regMPass.getText().toString().trim();
                if (name.isEmpty() || pass.isEmpty() || email.isEmpty() || quepass.isEmpty()) {
                    Toast.makeText(this, "手机号或密码或邮箱不能为空！", Toast.LENGTH_SHORT).show();
                }
                map.put("mobile", name);
                map.put("password", pass);
                presenterImpls.getRequest(Contacts.USER_REG_URL, map, RegsiterBean.class);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    @Override
    public void Success(Object daa) {
        RegsiterBean regsiterBean = (RegsiterBean) daa;
        if (regsiterBean.getMsg().equals("注册成功")) {
            Toast.makeText(this, regsiterBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void Error(Object err) {

    }

}
