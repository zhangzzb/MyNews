package com.zzb.mynew.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzb.mynew.R;
import com.zzb.mynew.bean.article.ArticleBean;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;

/**
 * @author 张智斌
 * @time 2017/4/6 20:50
 * @desc ${TODD}
 */

public class ArticleHeader extends LinearLayout implements View.OnClickListener {
    private TextView mHeadContent, mHeadName, mHeadCount;
    private ImageView mHeadSmail, mHeadCry,mHeadUser;
    private int mUp;
    private int mCommentsCount;
    private int mShareCount;

    public ArticleHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public ArticleHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArticleHeader(Context context) {
        this(context, null);
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.article_header_item, this);
        mHeadContent = (TextView) view.findViewById(R.id.tv_content);
        mHeadName = (TextView) view.findViewById(R.id.tv_user_name);
        mHeadCount = (TextView) view.findViewById(R.id.tv_count);
        mHeadContent = (TextView) view.findViewById(R.id.tv_content);
        mHeadUser = (ImageView) view.findViewById(R.id.iv_user);
        view.findViewById(R.id.iv_details_share).setOnClickListener(this);
        mHeadSmail=(ImageView)view.findViewById(R.id.iv_details_smill);
        mHeadSmail.setOnClickListener(this);
        mHeadCry=(ImageView)view.findViewById(R.id.iv_details_cry);
        mHeadCry.setOnClickListener(this);
    }

    public void setHeader(ArticleBean.ItemsBean itemsBean) {
            ArticleBean.ItemsBean.UserBean user = itemsBean.getUser();
            if (user != null) {
                mHeadName.setText(user.getLogin());
                ImageLoaderUtils.displayRound(mHeadUser.getContext(), mHeadUser, "http:" + user.getThumb());
            }
            mUp = itemsBean.getVotes().getUp();
            mCommentsCount = itemsBean.getComments_count();
            mShareCount = itemsBean.getShare_count();
            mHeadCount.setText("好笑 " + mUp + " · 评论" + mCommentsCount + " · 分享" + mShareCount);
            mHeadContent.setText(itemsBean.getContent());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_details_smill:
                mHeadSmail.setImageResource(R.drawable.operation_support_press);
                mHeadCry.setImageResource(R.drawable.operation_unsupport);
                mHeadCount.setText("好笑 " + (mUp + 1) + " · 评论" + mCommentsCount + " · 分享" + mShareCount);
                //做缩放动画
                startAnimator(mHeadSmail);
                break;
            case R.id.iv_details_cry:
                mHeadSmail.setImageResource(R.drawable.operation_support);
                mHeadCry.setImageResource(R.drawable.operation_unsupport_press);
                mHeadCount.setText("好笑 " + (mUp - 1) + " · 评论" + mCommentsCount + " · 分享" + mShareCount);
                //做缩放动画
                startAnimator(mHeadCry);
                break;
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
