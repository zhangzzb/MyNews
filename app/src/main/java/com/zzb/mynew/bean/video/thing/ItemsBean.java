package com.zzb.mynew.bean.video.thing;

import java.io.Serializable;

/**
 * @author 张智斌
 * @time 2017/3/3 14:53
 * @desc ${TODD}
 */

public class ItemsBean implements Serializable{
    /**
     * allow_comment : true
     * comments_count : 19
     * content : 这宝宝太逗了
     * created_at : 1488451997
     * format : video
     * high_loc : //qiubai-video.qiushibaike.com/PUBTL2JGUZQIOYLU.mp4
     * high_url : http://qiubai-video.qiushibaike.com/PUBTL2JGUZQIOYLU.mp4
     * id : 118648289
     * image : PUBTL2JGUZQIOYLU.mp4
     * image_size : {"m":[480,480,15],"s":[480,480,15]}
     * loop : 45716
     * low_loc : //qiubai-video.qiushibaike.com/PUBTL2JGUZQIOYLU_3g.mp4
     * low_url : http://qiubai-video.qiushibaike.com/PUBTL2JGUZQIOYLU_3g.mp4
     * pic_loc : //qiubai-video.qiushibaike.com/PUBTL2JGUZQIOYLU.jpg
     * pic_size : [480,480]
     * pic_url : http://qiubai-video.qiushibaike.com/PUBTL2JGUZQIOYLU.jpg
     * published_at : 1488458102
     * share_count : 200
     * state : publish
     * tag : null
     * user : {"avatar_updated_at":1488333149,"created_at":1487247467,"icon":"20170301095228.JPEG","id":33426448,"last_device":"android_10.8.1","last_visited_at":1487247467,"login":"vip雨夹雪","medium":"//pic.qiushibaike.com/system/avtnew/3342/33426448/medium/20170301095228.JPEG","role":"","state":"active","thumb":"//pic.qiushibaike.com/system/avtnew/3342/33426448/thumb/20170301095228.JPEG","updated_at":1487247467}
     * votes : {"down":-23,"up":531}
     */
    private boolean allow_comment;
    private int comments_count;
    private String content;
    private int created_at;
    private String format;
    private String high_loc;
    private String high_url;
    private int id;
    private String image;
    private String low_loc;
    private String low_url;
    private String pic_loc;
    private String pic_url;
    private int share_count;
    private User user;
    private int loop;
    public int getLoop() {
        return loop;
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }

    public boolean isAllow_comment() {
        return allow_comment;
    }

    public void setAllow_comment(boolean allow_comment) {
        this.allow_comment = allow_comment;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getHigh_loc() {
        return high_loc;
    }

    public void setHigh_loc(String high_loc) {
        this.high_loc = high_loc;
    }

    public String getHigh_url() {
        return high_url;
    }

    public void setHigh_url(String high_url) {
        this.high_url = high_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLow_loc() {
        return low_loc;
    }

    public void setLow_loc(String low_loc) {
        this.low_loc = low_loc;
    }

    public String getLow_url() {
        return low_url;
    }

    public void setLow_url(String low_url) {
        this.low_url = low_url;
    }

    public String getPic_loc() {
        return pic_loc;
    }

    public void setPic_loc(String pic_loc) {
        this.pic_loc = pic_loc;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ItemsBean{" +
                "allow_comment=" + allow_comment +
                ", comments_count=" + comments_count +
                ", content='" + content + '\'' +
                ", created_at=" + created_at +
                ", format='" + format + '\'' +
                ", high_loc='" + high_loc + '\'' +
                ", high_url='" + high_url + '\'' +
                ", id=" + id +
                ", image='" + image + '\'' +
                ", low_loc='" + low_loc + '\'' +
                ", low_url='" + low_url + '\'' +
                ", pic_loc='" + pic_loc + '\'' +
                ", pic_url='" + pic_url + '\'' +
                ", share_count=" + share_count +
                ", user=" + user +
                '}';
    }
}
