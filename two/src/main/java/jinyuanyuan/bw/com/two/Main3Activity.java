package jinyuanyuan.bw.com.two;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jinyuanyuan.bw.com.two.fragment.FenLeiFragment;
import jinyuanyuan.bw.com.two.fragment.ShoppingCardFragment;
import jinyuanyuan.bw.com.two.fragment.ShouYeFragment;
import jinyuanyuan.bw.com.two.fragment.WoDeFragment;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.home)
    RadioButton home;
    @BindView(R.id.fenl)
    RadioButton fenl;
    @BindView(R.id.card)
    RadioButton card;
    @BindView(R.id.my)
    RadioButton my;
    @BindView(R.id.Radio_Group)
    RadioGroup RadioGroup;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.frame_layout, new ShouYeFragment()).commit();
    }

    @OnClick({R.id.home, R.id.fenl, R.id.card, R.id.my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home:
                manager.beginTransaction().replace(R.id.frame_layout, new ShouYeFragment()).commit();
                break;
            case R.id.fenl:
                manager.beginTransaction().replace(R.id.frame_layout, new FenLeiFragment()).commit();
                break;
            case R.id.card:
                manager.beginTransaction().replace(R.id.frame_layout, new ShoppingCardFragment()).commit();
                break;
            case R.id.my:
                manager.beginTransaction().replace(R.id.frame_layout, new WoDeFragment()).commit();
                break;
        }
    }
}
