package com.zzb.mynew.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zzb.mynew.R;
import com.zzb.mynew.bean.video.thing.ItemsBean;
import com.zzb.mynew.bean.video.thing.User;
import com.zzb.mynew.common.base.ListBaseAdapter;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @author 张智斌
 * @time 2017/4/9 19:49
 * @desc ${TODD}
 */

public class VideoDetailAdapter extends ListBaseAdapter<ItemsBean> {
    public VideoDetailAdapter(Context context){
        super(context);
    }
    private JCVideoPlayerStandard mJCVideoPlayerStandard;
    private int mPosition;
    public void setPosition(int position) {
        this.mPosition = position;
    }
    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.video_details_list_item, parent, false);
        }
        final ViewHolder holder = ViewHolder.getViewHolder(convertView);
        ItemsBean items = mDatas.get(position);
        if (items != null) {
            User user = items.getUser();
            if (user != null) {
                //用户名
                holder.mTvVideoDetailsName.setText(user.getLogin());
                //用户头像
                ImageLoaderUtils.displayRound(parent.getContext(),holder.mCivUserDetail,"http:" + user.getThumb());
                //标题
                holder.mTvVideoDetailsTitle.setText(items.getContent());
                //视频
                holder.mJcVideoPlayer.setUp(items.getLow_url(),JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,"");
                //加载显示图
                ImageLoaderUtils.display(parent.getContext(), holder.mJcVideoPlayer.thumbImageView,items.getPic_url());
                holder.mJcVideoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
              //  mJCVideoPlayerStandard = holder.mJcVideoPlayer;
                //当用友点击Item时，自动播放相应的视频
                if (position == mPosition) {
                    holder.mJcVideoPlayer.startButton.performClick();
                }
                holder.mIvVideoDetailsSmill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.mTvVideoDetailsSmil.setText("好笑" + (80) + " · 评论" + 100 + " · 分享" + 100 + " · 再生" + (10 > 10000 ? (10 / 10000) + "w" : 10));
                        //更换图片并播放动画
                        holder.mIvVideoDetailsSmill.setImageResource(R.drawable.operation_support_press);
                        holder.mIvVideoDetailsCry.setImageResource(R.drawable.operation_unsupport);
                        startAnimator(holder.mIvVideoDetailsSmill);
                    }
                });
                holder.mIvVideoDetailsCry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //更换图片并播放动画
                        holder.mIvVideoDetailsSmill.setImageResource(R.drawable.operation_support);
                        holder.mIvVideoDetailsCry.setImageResource(R.drawable.operation_unsupport_press);
                        startAnimator(holder.mIvVideoDetailsCry);
                    }
                });
            }
        }
        return convertView;
    }
    private void startAnimator(View v) {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.5f, 1.0f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.5f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.play(scaleY).with(scaleX);
        animatorSet.start();
    }

    static class ViewHolder {
        @Bind(R.id.civ_user_detail)
        ImageView mCivUserDetail;
        @Bind(R.id.tv_video_details_name)
        TextView mTvVideoDetailsName;
        @Bind(R.id.jc_video_player)
        JCVideoPlayerStandard mJcVideoPlayer;
        @Bind(R.id.tv_video_details_title)
        TextView mTvVideoDetailsTitle;
        @Bind(R.id.tv_video_details_smil)
        TextView mTvVideoDetailsSmil;
        @Bind(R.id.iv_video_details_smill)
        ImageView mIvVideoDetailsSmill;
        @Bind(R.id.iv_video_details_cry)
        ImageView mIvVideoDetailsCry;
        @Bind(R.id.iv_ovideo_details_comment)
        ImageView mIvOvideoDetailsComment;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
        private static ViewHolder getViewHolder(View convertView) {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            if (holder == null) {
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
            return holder;
        }
    }
}
