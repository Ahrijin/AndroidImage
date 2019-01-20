package jinyuanyuan.bw.com.two.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jzvd.JZVideoPlayerStandard;
import jinyuanyuan.bw.com.two.Main4Activity;
import jinyuanyuan.bw.com.two.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FenLeiFragment extends Fragment {


    @BindView(R.id.jcvideoplayer)
    JZVideoPlayerStandard jzVideoPlayer;
    Unbinder unbinder;
    @BindView(R.id.imgs)
    ImageView imgs;
    private View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fen_lei, container, false);
        unbinder = ButterKnife.bind(this, v);


        jzVideoPlayer.setUp("http://ips.ifeng.com/video19.ifeng.com/video09/2014/06/16/1989823-102-086-0009.mp4",
                JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "haha");


        Glide.with(getActivity()).load("https://img04.sogoucdn.com/app/a/100520024/128eca52aad5900c55e753f46e98cf00")
                .into(jzVideoPlayer.thumbImageView);//给默认显示的图片赋值


        jzVideoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.imgs)
    public void onViewClicked(View v) {
        switch (v.getId()){
            case R.id.imgs:
                startActivity(new Intent(getActivity(),Main4Activity.class));
                break;
        }
    }
}
