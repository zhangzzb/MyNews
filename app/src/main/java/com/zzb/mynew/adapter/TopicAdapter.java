package com.zzb.mynew.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzb.mynew.R;
import com.zzb.mynew.bean.topic.TopicDetailBean;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;
import com.zzb.mynew.common.imagepager.BigImagePagerActivity;
import com.zzb.mynew.util.DatasUtil;
import com.zzb.mynew.view.ExpandableTextView;
import com.zzb.mynew.view.MultiImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张智斌
 * @time 2017/4/3 17:03
 * @desc ${TODD}
 */

public class TopicAdapter extends BaseQuickAdapter<TopicDetailBean, BaseViewHolder> {
    public TopicAdapter() {
        super(R.layout.item_circle_list);
    }
    @Override
    protected void convert(BaseViewHolder helper, final TopicDetailBean item) {
        if (item != null) {
            ImageView imageView=helper.getView(R.id.iv_head);
            ImageLoaderUtils.displayRound(imageView.getContext(),imageView,DatasUtil.getRandomPhotoUrl());
            helper.setText(R.id.tv_time,DatasUtil.getRandomTime());
            helper.setText(R.id.tv_from, item.getDistance());
            helper.setText(R.id.tv_comment_count, String.valueOf(item.getComment_count()));
            ExpandableTextView content = (ExpandableTextView) helper.convertView.findViewById(R.id.tv_content);
            MultiImageView multiImageView = (MultiImageView) helper.convertView.findViewById(R.id.multiImagView);
            content.setText(replayContent(item.getContent()));
            final TextView likeTv = (TextView) helper.convertView.findViewById(R.id.tv_like_count);
            likeTv.setText(String.valueOf(item.getLike_count()));
            likeTv.setSelected(false);
            likeTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    likeTv.setSelected(true);
                    if (likeTv.isSelected()) {
                        likeTv.setText(item.getLike_count() + 1 + "");
                    }
                    //做缩放动画
                }
            });
            if(multiImageView!=null){
                final List<String> photos =new ArrayList<>();
                List<TopicDetailBean.PicUrlsBean> pic_urls = item.getPic_urls();
                TopicDetailBean.UserBean user = item.getUser();
                if(user!=null){
                    helper.setText(R.id.tv_name,user.getLogin());
                    for (int i = 0; i < pic_urls.size(); i++) {
                        photos.add(pic_urls.get(i).getPic_url());
                    }
                    if(user.getLogin().equals("爱迪生")){
                        photos.clear();
                        photos.addAll(getPictureList(pic_urls.get(0).getPic_url()));
                    }
                if (photos.size() > 0) {
                    multiImageView.setVisibility(View.VISIBLE);
                    multiImageView.setList(photos);
                    multiImageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            // 查看大图
                            BigImagePagerActivity.startImagePagerActivity(mContext, photos, position);
                        }
                    });
                } else {
                    multiImageView.setVisibility(View.GONE);
                }
                    if(user.getLogin().equals("爱迪生")){
                        helper.setText(R.id.tv_time,"刚刚");
                        content.setText(item.getContent());
                        helper.setText(R.id.tv_from,"广东.广州");
                    }
                }
            }
        }
    }


    public String replayContent(String message) {
        if (message.contains("u") && message.contains("\\")) {
            message = DatasUtil.getRandomString();
        } else if (message.contains("#")) {
            message = message.replace("#", "");
        }
        return message;
    }
    /**
     * 获取图片链接
     */
    public List<String> getPictureList(String pictures) {
        if (!TextUtils.isEmpty(pictures)) {
            List<String> photos = new ArrayList<>();
            String[] strings = pictures.split(";");
            if (strings != null && strings.length > 0) {
                for (String str : strings) {
                    if (!TextUtils.isEmpty(str)) {
                        photos.add(str);
                    }
                }
                return photos;
            } else {
                return photos;
            }
        } else {
            return new ArrayList<>();
        }
    }
}
