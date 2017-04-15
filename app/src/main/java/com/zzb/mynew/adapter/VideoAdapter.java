package com.zzb.mynew.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzb.mynew.R;
import com.zzb.mynew.bean.video.netease.VideoBean;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;

/**
 * @author 张智斌
 * @time 2017/4/5 16:05
 * @desc ${TODD}
 */

public class VideoAdapter extends BaseQuickAdapter<VideoBean,BaseViewHolder>{
    public VideoAdapter() {
        super(R.layout.video_grid_item);
    }
    @Override
    protected void convert(BaseViewHolder helper, VideoBean item) {
        if(item!=null){
            helper.setText(R.id.tv_user,item.getTopicName());
            helper.setText(R.id.tv_content,item.getTitle());
            ImageView videoImage=helper.getView(R.id.iv_video);
            ImageView avatarImage=helper.getView(R.id.iv_avatar);
            ImageLoaderUtils.display(videoImage.getContext(),videoImage,item.getCover());
            ImageLoaderUtils.display(avatarImage.getContext(),avatarImage,item.getTopicImg());
        }
    }
}
