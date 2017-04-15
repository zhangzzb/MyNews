package com.zzb.mynew.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zzb.mynew.R;
import com.zzb.mynew.common.base.ListBaseAdapter;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;
import com.zzb.mynew.common.commonutil.ViewHolderUtil;
import com.zzb.mynew.common.imagepager.BigImagePagerActivity;

import java.util.List;

/**
 * @author 张智斌
 * @time 2017/4/4 0:13
 * @desc ${TODD}
 */

public class NinePicturesAdapter extends ListBaseAdapter<String>{
    private boolean showAdd = true;
    private int picturnNum = 0;
    private boolean isDelete = false;//当前是否显示删除按钮
    private OnClickAddListener onClickAddListener;
    private boolean isAdd=true;//当前是否显示添加按钮
    public NinePicturesAdapter(Context context, int picturnNum, OnClickAddListener onClickAddListener) {
        super(context);
        this.picturnNum = picturnNum;
        this.onClickAddListener = onClickAddListener;
        showAdd();
    }
    /**
     * 获取图片张数
     * @return
     */
    public int getPhotoCount(){
        return isAdd==true?getCount()-1:getCount();
    }
    /**
     * 加号接口
     */
    public interface OnClickAddListener {
        void onClickAdd(int positin);
    }
    /**
     * 移除add按钮
     */
    public void hideAdd(){
        int lastPosition=getData().size()-1;
        if(getData().get(lastPosition)!=null&& TextUtils.isEmpty(getData().get(lastPosition))){
            getData().remove(lastPosition);
            isAdd=false;
            notifyDataSetChanged();
        }
    }
    /**
     * 显示add按钮
     */
    public void showAdd(){
        if(getData().size()<picturnNum){
            addItem(getData().size(),"");
            isAdd=true;
            notifyDataSetChanged();
        }
    }

    @Override
    protected View getRealView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView =mInflater.inflate(R.layout.item_grid_photo, parent, false);
        }
        final ImageView imageView = ViewHolderUtil.get(convertView, R.id.img_photo);
        ImageView imgDelete = ViewHolderUtil.get(convertView, R.id.img_delete);
        final String url = getData().get(position);
        //显示图片
        if (TextUtils.isEmpty(url) && showAdd) {
            ImageLoaderUtils.display(parent.getContext(), imageView, R.drawable.addphoto);
            imgDelete.setVisibility(View.GONE);
        } else {
            imgDelete.setVisibility(View.VISIBLE);
            ImageLoaderUtils.display(parent.getContext(), imageView, url);
        }
        autoHideShowAdd();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //再次选择图片
                if (TextUtils.isEmpty(url)) {
                    if (onClickAddListener != null) {
                        onClickAddListener.onClickAdd(position);
                    }
                } else {
                    //放大查看图片
                    BigImagePagerActivity.startImagePagerActivity(parent.getContext(), getData(), position);
                }
            }
        });
        //删除按钮
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remove(position);
                if (!isDelete && getCount() < 1) {
                    addItem("");
                    isDelete = true;
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    /**
     * 移除add按钮
     */
    public void autoHideShowAdd(){
        int lastPosition=getData().size()-1;
        if(lastPosition==picturnNum&&getData().get(lastPosition)!=null&&TextUtils.isEmpty(getData().get(lastPosition))){
            remove(lastPosition);
            isAdd=false;
            notifyDataSetChanged();
        }else if(!isAdd){
            showAdd();
        }
    }

    @Override
    public void addData(List<String> data) {
        if(isAdd){
            hideAdd();
        }
        super.addData(data);
        showAdd();
    }
}
