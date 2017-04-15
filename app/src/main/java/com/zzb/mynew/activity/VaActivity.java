package com.zzb.mynew.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

import com.zzb.mynew.R;
import com.zzb.mynew.common.base.BaseActivity;
import com.zzb.mynew.view.EmptyLayout;

import butterknife.Bind;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * @author 张智斌
 * @time 2017/4/13 22:26
 * @desc ${TODD}
 */

public class VaActivity extends BaseActivity {
    public static final String VIDEOURL = "video_path";
    @Bind(R.id.vitamio)
    VideoView mVideoView;
    @Bind(R.id.error_layout)
    EmptyLayout mErrorLayout;
    private String mUrlpath;
    @Override
    public int getLayoutId() {
        Vitamio.initialize(this);
        return R.layout.activity_vatamio;
    }
    @Override
    public void initData() {
        playfunction();
    }
    @Override
    public void initView() {
        mUrlpath = getIntent().getStringExtra(VIDEOURL);
    }
    public static void startAction(Context activity,String url) {
        Intent intent = new Intent(activity, VaActivity.class);
        intent.putExtra(VIDEOURL, url);
        activity.startActivity(intent);
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (mVideoView != null)
            mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_SCALE, 0);
        super.onConfigurationChanged(newConfig);
    }
    private void playfunction() {
        mVideoView.setVideoPath(mUrlpath);//设置视频网络地址
        mVideoView.setMediaController(new MediaController(this)); //设置媒体控制器
        mVideoView.setVideoLayout(VideoView.VIDEO_LAYOUT_STRETCH, 0);   //设置视频的缩放参数,这里设置为拉伸
        mVideoView.requestFocus();
        //视频播放器的准备,此时播放器已经准备好了,此处可以设置一下播放速度,播放位置等等
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //此处设置播放速度为正常速度1
                mErrorLayout.setErrorType(EmptyLayout.HIDE_LAYOUT);
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
        //当播放完成后,从头开始
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.seekTo(0);//转到第一帧
                mediaPlayer.start();     //开始播放
            }
        });
        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                showNetErrorTip("视频播放失败");
                mErrorLayout.setErrorType(EmptyLayout.NETWORK_ERROR);
                return true;
            }
        });
        /*缓存设置*/
        mVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what) {
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        if (mVideoView.isPlaying()) {
                            mVideoView.pause();
                        }
                        break;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        mVideoView.start();
                        break;
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:

                        break;
                }
                return true;
            }
        });
        mVideoView.setBufferSize(1024);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.staystill, R.anim.activity_out);
    }
}
