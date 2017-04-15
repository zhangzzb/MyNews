package com.zzb.mynew.bean.video.netease;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张智斌
 * @time 2017/4/5 11:46
 * @desc ${网易视频}
 */

public class VideoBean implements Serializable{
    private String cover;
    private String mp4_url;
    private String topicImg;
    private String topicName;
    private String title;
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "cover='" + cover + '\'' +
                ", mp4_url='" + mp4_url + '\'' +
                ", topicImg='" + topicImg + '\'' +
                ", topicName='" + topicName + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
    public static List<VideoBean> parse(String json){
        List<VideoBean> list = new ArrayList<>();
        try {
            JSONObject response =new JSONObject(json);
            JSONArray array = response.getJSONArray("视频");
            for (int i = 0, length = array.length(); i < length; i++) {
                JSONObject obj = array.optJSONObject(i);
                VideoBean videoBean = new VideoBean();
                videoBean.setCover(obj.optString("cover"));
                videoBean.setMp4_url(obj.optString("mp4_url"));
                videoBean.setTitle(obj.optString("title"));
                videoBean.setTopicImg(obj.optString("topicImg"));
                videoBean.setTopicName(obj.optString("topicName"));
                list.add(videoBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return list;
        }
        return list;
    }
}
