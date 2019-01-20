package jinyuanyuan.bw.com.two.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import jinyuanyuan.bw.com.two.R;
import jinyuanyuan.bw.com.two.adapter.MyAdapter;
import jinyuanyuan.bw.com.two.bean.MyData;
import jinyuanyuan.bw.com.two.presenter.PresenterImpls;
import jinyuanyuan.bw.com.two.utils.Contacts;
import jinyuanyuan.bw.com.two.view.IView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShouYeFragment extends Fragment implements IView {


    @BindView(R.id.xrecy)
    XRecyclerView xrecy;
    Unbinder unbinder;
    private View v;
    private PresenterImpls presenterImpls;
    private List<MyData.DataBean> mLists = new ArrayList<>();
    private MyAdapter adapter;
    private Map<String, Object> map = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_shou_ye, container, false);
        unbinder = ButterKnife.bind(this, v);
        adapter = new MyAdapter(mLists, getActivity());
        xrecy.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        xrecy.setLayoutManager(linearLayoutManager);
        map.put("pscid", 40);
        presenterImpls = new PresenterImpls(this);
        presenterImpls.getRequest(Contacts.GOODS_URL, map, MyData.class);
        xrecy.setPullRefreshEnabled(true);
        xrecy.setLoadingMoreEnabled(true);

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void Success(Object daa) {
        MyData myData = (MyData) daa;
        mLists.addAll(myData.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void Error(Object err) {

    }
}
