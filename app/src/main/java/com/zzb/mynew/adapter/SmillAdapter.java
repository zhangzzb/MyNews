package com.zzb.mynew.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzb.mynew.R;
import com.zzb.mynew.bean.video.thing.ItemsBean;
import com.zzb.mynew.bean.video.thing.User;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;

/**
 * @author 张智斌
 * @time 2017/4/7 18:00
 * @desc 糗事视频
 */
public class SmillAdapter extends BaseQuickAdapter<ItemsBean,BaseViewHolder> {
    public SmillAdapter() {
        super(R.layout.video_grid_item);
    }
    @Override
    protected void convert(BaseViewHolder helper, ItemsBean item) {
        if(item!=null){
            User user = item.getUser();
            helper.setText(R.id.tv_content,item.getContent());
            ImageView videoImage=helper.getView(R.id.iv_video);
            ImageView avatarImage=helper.getView(R.id.iv_avatar);
            if(user!=null){
                helper.setText(R.id.tv_user,user.getLogin());
                ImageLoaderUtils.display(avatarImage.getContext(),avatarImage,"http:"+user.getThumb());
            }
            ImageLoaderUtils.display(videoImage.getContext(),videoImage,item.getPic_url());
        }
    }
}
