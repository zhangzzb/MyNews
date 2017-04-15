package com.zzb.mynew.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zzb.mynew.R;
import com.zzb.mynew.activity.ArticleDetailActivity;
import com.zzb.mynew.bean.article.ArticleBean;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;
import com.zzb.mynew.view.ExpandableTextView;

/**
 * @author 张智斌
 * @time 2017/4/5 22:39
 * @desc ${TODD}
 */

public class ArticleAdapter extends BaseQuickAdapter<ArticleBean.ItemsBean, BaseViewHolder> {
    public ArticleAdapter() {
        super(R.layout.fragment_article_item);
    }
    @Override
    protected void convert(BaseViewHolder helper, final ArticleBean.ItemsBean item) {
        if (item != null) {
            ImageView userImage = helper.getView(R.id.iv_user);
            ArticleBean.ItemsBean.UserBean user = item.getUser();
            if (user != null) {
                ImageLoaderUtils.displayRound(userImage.getContext(), userImage, "http:" + user.getThumb());
                helper.setText(R.id.tv_article_name, item.getUser().getLogin());
            }
            ExpandableTextView tvContent = helper.getView(R.id.tv_article_content);
            tvContent.setText(item.getContent());
            final int up = item.getVotes().getUp();
            final int commentsCount = item.getComments_count();
            final int shareCount = item.getShare_count();
            final TextView article = helper.getView(R.id.tv_article_smil);
            article.setText("好笑 " + up + " · 评论" + commentsCount + " · 分享" + shareCount);
            final ImageView smill = helper.getView(R.id.iv_article_smill);
            final ImageView cry = helper.getView(R.id.iv_article_cry);
            smill.setImageResource(R.drawable.operation_support);
            smill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    smill.setImageResource(R.drawable.operation_support_press);
                    cry.setImageResource(R.drawable.operation_unsupport);
                    article.setText("好笑 " + (up + 1) + " · 评论" + commentsCount + " · 分享" + shareCount);
                    //做缩放动画
                    startAnimator(smill);
                }
            });
            cry.setImageResource(R.drawable.operation_support);
            cry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    smill.setImageResource(R.drawable.operation_support);
                    cry.setImageResource(R.drawable.operation_unsupport_press);
                    article.setText("好笑 " + (up - 1) + " · 评论" + commentsCount + " · 分享" + shareCount);
                    //做缩放动画
                    startAnimator(cry);
                }
            });
            CardView cardView=helper.getView(R.id.card_layout);
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ArticleDetailActivity.startHotDetailActivity(smill.getContext(),item);
                        }
                    });
            helper.addOnClickListener(R.id.tv_article_content);
        }
    }
    private void startAnimator(View v) {
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(v, "scaleY", 1.0f, 1.5f, 1.0f);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(v, "scaleX", 1.0f, 1.5f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.play(scaleY).with(scaleX);
        animatorSet.start();
    }
}
