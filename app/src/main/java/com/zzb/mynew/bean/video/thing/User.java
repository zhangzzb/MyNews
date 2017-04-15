package com.zzb.mynew.bean.video.thing;

import java.io.Serializable;

/**
 * @author 张智斌
 * @time 2017/3/3 14:55
 * @desc ${TODD}
 */

public class User implements Serializable{
    /**
     * avatar_updated_at : 1488333149
     * created_at : 1487247467
     * icon : 20170301095228.JPEG
     * id : 33426448
     * last_device : android_10.8.1
     * last_visited_at : 1487247467
     * login : vip雨夹雪
     * medium : //pic.qiushibaike.com/system/avtnew/3342/33426448/medium/20170301095228.JPEG
     * role :
     * state : active
     * thumb : //pic.qiushibaike.com/system/avtnew/3342/33426448/thumb/20170301095228.JPEG
     * updated_at : 1487247467
     */

    private int avatar_updated_at;
    private int created_at;
    private String icon;
    private int id;
    private int last_visited_at;
    private String medium;
    private String thumb;
    private String login;
    public int getAvatar_updated_at() {
        return avatar_updated_at;
    }

    public void setAvatar_updated_at(int avatar_updated_at) {
        this.avatar_updated_at = avatar_updated_at;
    }

    public int getCreated_at() {
        return created_at;
    }

    public void setCreated_at(int created_at) {
        this.created_at = created_at;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLast_visited_at() {
        return last_visited_at;
    }

    public void setLast_visited_at(int last_visited_at) {
        this.last_visited_at = last_visited_at;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "avatar_updated_at=" + avatar_updated_at +
                ", created_at=" + created_at +
                ", icon='" + icon + '\'' +
                ", id=" + id +
                ", last_visited_at=" + last_visited_at +
                ", medium='" + medium + '\'' +
                ", thumb='" + thumb + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
