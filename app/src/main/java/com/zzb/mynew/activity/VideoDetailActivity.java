package com.zzb.mynew.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.zzb.mynew.R;
import com.zzb.mynew.adapter.VideoDetailAdapter;
import com.zzb.mynew.adapter.VideoNetDetailAdapter;
import com.zzb.mynew.bean.video.netease.VideoBean;
import com.zzb.mynew.bean.video.thing.ItemsBean;
import com.zzb.mynew.common.commonwidget.StatusBarCompat;

import java.io.Serializable;
import java.util.List;

import butterknife.Bind;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

/**
 * @author 张智斌
 * @time 2017/4/9 19:36
 * @desc ${TODD}
 */

public class VideoDetailActivity extends AppCompatActivity {
    public static final String VIDEO_KEY_IMAGES = "video_key_images";
    private static final String VIDEO_KEY_INDEX = "video_key_index";
    public static final String NET_KEY_ACTION = "new_action";
    public static final String THING_KEY_ACTION = "thing_action";
    @Bind(R.id.video_details_lv)
    ListView mVideoDetailsLv;
    private List<ItemsBean> mItemsBeanList;
    private List<VideoBean> mVideoBeanList;
    private int mIndex;
    private int flag=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        Intent intent = getIntent();
        if(intent.getAction().equals(NET_KEY_ACTION)){
            mVideoBeanList = (List<VideoBean>) intent.getSerializableExtra(VIDEO_KEY_IMAGES);
            flag=1;
        }else if(intent.getAction().equals(THING_KEY_ACTION)){
            mItemsBeanList = (List<ItemsBean>) intent.getSerializableExtra(VIDEO_KEY_IMAGES);
            flag=2;
        }
        mIndex = intent.getIntExtra(VIDEO_KEY_INDEX, 0);
        initView();
        initData();
    }
    public void initData() {
        if(flag==1){
            VideoNetDetailAdapter netDetailAdapter = new VideoNetDetailAdapter(this);
            mVideoDetailsLv.setAdapter(netDetailAdapter);
            netDetailAdapter.setPosition(mIndex);
            netDetailAdapter.setData(mVideoBeanList);
        }else if(flag==2){
            //设置自动播放
            VideoDetailAdapter adapter = new VideoDetailAdapter(this);
            mVideoDetailsLv.setAdapter(adapter);
            adapter.setPosition(mIndex);
            adapter.setData(mItemsBeanList);
        }
        mVideoDetailsLv.setSelection(mIndex);
    }
    public void initView() {
        mVideoDetailsLv=(ListView)findViewById(R.id.video_details_lv);
        StatusBarCompat.translucentStatusBar(this);
    }
    public static void startAction(int position, Context context, List<ItemsBean> itemsBeanList) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(VIDEO_KEY_IMAGES, (Serializable) itemsBeanList);
        intent.putExtra(VIDEO_KEY_INDEX, position);
        intent.setAction(THING_KEY_ACTION);
        context.startActivity(intent);
    }
    public static void startActionNet(int position, Context context, List<VideoBean> itemsBeanList) {
        Intent intent = new Intent(context, VideoDetailActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(VIDEO_KEY_IMAGES, (Serializable) itemsBeanList);
        intent.putExtra(VIDEO_KEY_INDEX, position);
        intent.setAction(NET_KEY_ACTION);
        context.startActivity(intent);
    }
    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.staystill, R.anim.activity_out);
    }
}
