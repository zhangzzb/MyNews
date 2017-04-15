package com.zzb.mynew.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzb.mynew.R;
import com.zzb.mynew.bean.article.CommentaryBean;
import com.zzb.mynew.common.base.ListBaseAdapter;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;
import com.zzb.mynew.common.commonutil.ViewHolderUtil;
import com.zzb.mynew.util.DatasUtil;

/**
 * @author 张智斌
 * @time 2017/4/6 21:57
 * @desc ${TODD}
 */

public class ArticleCommentAdapter extends ListBaseAdapter<CommentaryBean.ItemsBean> {
    public ArticleCommentAdapter(Context context){
        super(context);
    }
    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView =mInflater.inflate(R.layout.article_detail_list_item, parent, false);
        }
        CommentaryBean.ItemsBean itemsBean = mDatas.get(position);
        ImageView ivareIv = ViewHolderUtil.get(convertView, R.id.icon);
        TextView nameTv = ViewHolderUtil.get(convertView, R.id.name);
        TextView likeTv = ViewHolderUtil.get(convertView, R.id.thumb_up);
        TextView contentTv = ViewHolderUtil.get(convertView,R.id.expandable_content);
        TextView TimeTv = ViewHolderUtil.get(convertView, R.id.time);
        TextView floorTv = ViewHolderUtil.get(convertView, R.id.floor);
        if(itemsBean!=null){
            CommentaryBean.ItemsBean.UserBean user = itemsBean.getUser();
            if(user!=null){
                nameTv.setText(user.getLogin());
                ImageLoaderUtils.displayRound(ivareIv.getContext(), ivareIv, "http:" + user.getThumb());
            }
            TimeTv.setText(DatasUtil.getRandomTime());
            contentTv.setText(itemsBean.getContent());
            likeTv.setText(String.valueOf(itemsBean.getLike_count()));
            floorTv.setText(String.valueOf(itemsBean.getFloor()+"楼"));
        }
        return convertView;
    }
}
