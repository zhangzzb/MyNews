package com.zzb.mynew.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzb.mynew.R;
import com.zzb.mynew.bean.daliy.DaliyBanner;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张智斌
 * @time 2017/4/5 20:22
 * @desc ${TODD}
 */

public class DaliyAdapter extends BaseMultiItemQuickAdapter<DaliyBanner,BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public DaliyAdapter() {
        super(new ArrayList<DaliyBanner>());
        addItemType(DaliyBanner.TYPE_TOP,R.layout.item_daily_feed_0);
        addItemType(DaliyBanner.LOAD_END,R.layout.item_daily_feed_0);
        addItemType(DaliyBanner.LOAD_NONE, R.layout.item_daily_feed_1);
    }

    @Override
    protected void convert(BaseViewHolder helper, DaliyBanner item) {
        if(item!=null){
        switch (helper.getItemViewType()) {
            case DaliyBanner.TYPE_TOP:
                helper.setText(R.id.tv_feed_0_title, item.getPost().getTitle());
                helper.setText(R.id.tv_feed_0_desc, item.getPost().getDescription());
                helper.setText(R.id.tv_Feed_0_type, item.getPost().getCategory().getTitle());
                ImageView icon0=helper.getView(R.id.iv_feed_0_icon);
                ImageView feedType0=helper.getView(R.id.iv_feed_0_type);
                ImageLoaderUtils.display(icon0.getContext(),icon0,item.getImage());
                if (item.getType() == 0) {
                    feedType0.setImageResource(R.drawable.feed_0_icon);
                }else if(item.getType() == 2){
                    feedType0.setImageResource(R.drawable.feed_1_icon);
                }
                break;
            case DaliyBanner.LOAD_NONE:
                changUI1(helper,item);
                break;
            case DaliyBanner.LOAD_END:
                changUI(helper,item);
                break;
          }
        }
    }

    private void changUI(BaseViewHolder helper,DaliyBanner item) {
        helper.setText(R.id.tv_feed_0_title, item.getPost().getTitle());
        helper.setText(R.id.tv_feed_0_desc, item.getPost().getDescription());
        helper.setText(R.id.tv_Feed_0_type, item.getPost().getCategory().getTitle());
        ImageView icon0=helper.getView(R.id.iv_feed_0_icon);
        ImageView feedType0=helper.getView(R.id.iv_feed_0_type);
        ImageLoaderUtils.display(icon0.getContext(),icon0,item.getImage());
        if (item.getType() == 0) {
            feedType0.setImageResource(R.drawable.feed_0_icon);
        }else if(item.getType() == 2){
            feedType0.setImageResource(R.drawable.feed_1_icon);
        }
    }

    private void changUI1(BaseViewHolder helper,DaliyBanner item) {
        helper.setText(R.id.tv_feed_1_title, item.getPost().getTitle());
        helper.setText(R.id.tv_feed_1_type, item.getPost().getCategory().getTitle());
        ImageView icon1=helper.getView(R.id.iv_feed_1_icon);
        ImageView feedType1=helper.getView(R.id.iv_feed_1_type_icon);
        ImageLoaderUtils.display(feedType1.getContext(),feedType1,item.getPost().getCategory().getImage_lab());
        ImageLoaderUtils.display(icon1.getContext(),icon1,item.getImage());
       /* holder.mTvFeed1Title.setText(daily.getPost().getTitle());
        holder.mTvFeed1Type.setText(daily.getPost().getCategory().getTitle());
        ImageUtil.getInstance().display(daily.getPost().getCategory().getImage_lab(),holder.mIvFeed1TypeIcon);
        ImageUtil.getInstance().display(daily.getImage(),holder.mIvFeed1Icon);*/
    }
}
