package com.zzb.mynew.adapter;

import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzb.mynew.R;
import com.zzb.mynew.api.Constants;
import com.zzb.mynew.bean.hotnews.HotNewsBean;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;
import com.zzb.mynew.common.commonutil.SharePrefUtil;
import com.zzb.mynew.common.commonutil.TimeUtil;

/**
 * @author 张智斌
 * @time 2017/3/29 9:32
 * @desc ${TODD}
 */

public class NewsHotAdapter extends BaseQuickAdapter<HotNewsBean.NewDetailBean, BaseViewHolder> {
    public NewsHotAdapter() {
        super(R.layout.hot_list_item);
    }
    @Override
    protected void convert(BaseViewHolder helper, HotNewsBean.NewDetailBean item) {
        if(item!=null){
            TextView titleTv = helper.getView(R.id.tv_title);
            if(item.ptime!=null){
                helper.setText(R.id.tv_time, TimeUtil.formatDate(item.ptime));
            }
            String idKey = SharePrefUtil.getString(Constants.HOT_NEW_KEY+item.docid, "");
            if(idKey.equals(item.docid)){
              titleTv.setTextColor(Color.GRAY);
            }else{
                titleTv.setTextColor(Color.BLACK);
            }
            titleTv.setText(item.title);
            helper.setText(R.id.tv_sourse,item.source);
            ImageView imageView=helper.getView(R.id.iv_image);
            ImageLoaderUtils.display(imageView.getContext(),imageView,item.imgsrc);
        }
    }
}
