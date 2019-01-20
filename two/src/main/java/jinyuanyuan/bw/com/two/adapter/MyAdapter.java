package jinyuanyuan.bw.com.two.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import jinyuanyuan.bw.com.two.R;
import jinyuanyuan.bw.com.two.bean.MyData;

/*
 *Author:Ahri_Love
 *Date:2019/1/18
 */public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<MyData.DataBean> mLists;
    private Context context;

    public MyAdapter(List<MyData.DataBean> mLists, Context context) {
        this.mLists = mLists;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder viewHolder, int i) {
        String images = mLists.get(i).getImages();
        Log.e("aaa", images + "56+");
        String replace = images.replace("https", "http");
        String[] split = replace.split("\\|");
        Uri uri = Uri.parse(split[0]);
        viewHolder.imgs.setImageURI(uri);
        viewHolder.title.setText(mLists.get(i).getTitle());
        viewHolder.price.setText(mLists.get(i).getPrice() + "");
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView imgs;
        private TextView title;
        private TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.list_title);
            price = itemView.findViewById(R.id.list_price);
            imgs = itemView.findViewById(R.id.list_imgs);
        }
    }
}
