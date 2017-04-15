package com.zzb.mynew.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzb.mynew.R;
import com.zzb.mynew.bean.va.LiveBean;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;

/**
 * @author 张智斌
 * @time 2017/4/11 9:38
 * @desc ${TODD}
 */

public class VaAdapter extends BaseQuickAdapter<LiveBean, BaseViewHolder> {
    public VaAdapter() {
        super(R.layout.fragment_va_item);
    }
    @Override
    protected void convert(BaseViewHolder helper, LiveBean item) {
        if(item!=null){
            TextView tvContent = helper.getView(R.id.tv_find_white);
            if(!item.getContent().isEmpty()){
                tvContent.setText(item.getContent());
            }
            ImageView userImage = helper.getView(R.id.iv_find_icon);
            ImageLoaderUtils.display(userImage.getContext(), userImage,item.getThumbnail_url());
            LiveBean.AuthorBean author = item.getAuthor();
            if(author!=null){
                helper.setText(R.id.tv_find_name,author.getName());
            }
            helper.setText(R.id.tv_find_personCount,item.getAccumulated_count()+"人");
        }
    }
}
