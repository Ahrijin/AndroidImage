package jinyuanyuan.bw.com.two.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jinyuanyuan.bw.com.two.R;
import jinyuanyuan.bw.com.two.bean.Header;
import jinyuanyuan.bw.com.two.bean.MessageBean;
import jinyuanyuan.bw.com.two.presenter.PresenterImpls;
import jinyuanyuan.bw.com.two.utils.Contacts;
import jinyuanyuan.bw.com.two.view.IView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Url;

/**
 * A simple {@link Fragment} subclass.
 */
public class WoDeFragment extends Fragment implements IView {


    @BindView(R.id.wode_imgs)
    ImageView wodeImgs;
    @BindView(R.id.wode_name)
    TextView wodeName;
    @BindView(R.id.wode_ni_name)
    TextView wodeNiName;
    Unbinder unbinder;
    private View v;
    private PopupWindow pop;
    private Map<String, Object> maps = new HashMap<>();
    private Map<String, Object> map = new HashMap<>();
    private PresenterImpls presenterImpls;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_wo_de, container, false);
        unbinder = ButterKnife.bind(this, v);
        presenterImpls = new PresenterImpls(this);
        map.put("uid", 23807);
        presenterImpls.getRequest(Contacts.USER_INFO, map, MessageBean.class);
        return v;
    }

    private void setLinstener() {
        View view = View.inflate(getActivity(), R.layout.pop, null);
        pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pop.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        TextView paizhao = (TextView) view.findViewById(R.id.paizhao);
        TextView xiangce = (TextView) view.findViewById(R.id.xiangce);
        TextView cancel = (TextView) view.findViewById(R.id.cancel);
        //拍照
        paizhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.addCategory("android.intent.category.DEFAULT");
                startActivityForResult(intent, 1000);
                pop.dismiss();
            }
        });
        //相册
        xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 2000);
                pop.dismiss();
            }
        });
        //取消
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {//拍照
            Bitmap bitmap = data.getParcelableExtra("data");
            Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null,null));
            wodeImgs.setImageURI(uri);
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(uri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            //picturePath就是图片在储存卡所在的位置
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            File file = new File(picturePath);
            RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody1);
            maps.put("uid", 23807);
            presenterImpls.postRequest(Contacts.FILE_UPLOAD_URL, maps, part, Header.class);
        }
        if (requestCode == 2000) {//相册
            Uri uri = data.getData();
            wodeImgs.setImageURI(uri);
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(uri,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            //picturePath就是图片在储存卡所在的位置
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            File file = new File(picturePath);
            RequestBody requestBody1 = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody1);
            maps.put("uid", 23807);
            presenterImpls.postRequest(Contacts.FILE_UPLOAD_URL, maps, part, Header.class);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void Success(Object daa) {
        if (daa instanceof MessageBean) {
            MessageBean messageBean = (MessageBean) daa;
            String icon = messageBean.getData().getIcon();
            String[] split = icon.split("\\|");
            if(split.length>0){
                String replace = split[0].replace("https", "http");//图片
                Uri parse = Uri.parse(replace);
                Glide.with(getActivity()).load(replace).into(wodeImgs);
            }
        }

        if (daa instanceof Header) {
            Header header = (Header) daa;
            Log.e("aaas", header.getMsg());
        }
    }

    @Override
    public void Error(Object err) {

    }

    @OnClick(R.id.wode_imgs)
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.wode_imgs:
                setLinstener();
                break;
        }
    }


}
