package com.zzb.mynew.bean.va;

/**
 * @author 张智斌
 * @time 2017/4/11 9:25
 * @desc ${TODD}
 */

public class LiveBean {
    /**
     * status : 1
     * live_type : 1
     * author : {"origin":2,"name":"黎宝儿\"","gender":"u","platform_id":32591230,"intro":"We will and countless people passing by, and many people meet, and some people forward together.","is_follow":false,"nick_id":18144732,"id":66351398308039010,"headurl":"https://avatar.app-remix.com/53J7C7IJBXEFIJ4W.jpg?imageMogr2/thumbnail/300x300/auto-orient"}
     * is_follow : false
     * update_at : 2017-01-05 19:34:59
     * created_at : 2017-01-05 19:34:59
     * rtmp_live_url : rtmp://ks-rtmplive.app-remix.com/live/88067935947465771
     * share : {"url":"http://www.app-remix.com/share/live/66351398308039010","caption":"黎宝儿\"在热猫开直播，手机快要没电了！厉害了，我的宝宝们。","web_info":"直播传送门猛戳","title":"热猫"}
     * accumulated_count : 1129
     * content : 手机快要没电了
     * stream_id : 88067935947465771
     * thumbnail_url : https://avatar.app-remix.com/53J7C7IJBXEFIJ4W.jpg?imageMogr2/thumbnail/480x480/auto-orient
     * room_id : 88067936377995570
     * location :
     * hdl_live_url : http://ks-hdllive.app-remix.com/live/88067935947465771.flv
     * visitors_count : 1129
     * id : 66351398308039010
     */

    private int status;
    private int live_type;
    private AuthorBean author;
    private boolean is_follow;
    private String update_at;
    private String created_at;
    private String rtmp_live_url;
    private ShareBean share;
    private int accumulated_count;
    private String content;
    private String stream_id;
    private String thumbnail_url;
    private long room_id;
    private String location;
    private String hdl_live_url;
    private int visitors_count;
    private long id;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLive_type() {
        return live_type;
    }

    public void setLive_type(int live_type) {
        this.live_type = live_type;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public boolean isIs_follow() {
        return is_follow;
    }

    public void setIs_follow(boolean is_follow) {
        this.is_follow = is_follow;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getRtmp_live_url() {
        return rtmp_live_url;
    }

    public void setRtmp_live_url(String rtmp_live_url) {
        this.rtmp_live_url = rtmp_live_url;
    }

    public ShareBean getShare() {
        return share;
    }

    public void setShare(ShareBean share) {
        this.share = share;
    }

    public int getAccumulated_count() {
        return accumulated_count;
    }

    public void setAccumulated_count(int accumulated_count) {
        this.accumulated_count = accumulated_count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStream_id() {
        return stream_id;
    }

    public void setStream_id(String stream_id) {
        this.stream_id = stream_id;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHdl_live_url() {
        return hdl_live_url;
    }

    public void setHdl_live_url(String hdl_live_url) {
        this.hdl_live_url = hdl_live_url;
    }

    public int getVisitors_count() {
        return visitors_count;
    }

    public void setVisitors_count(int visitors_count) {
        this.visitors_count = visitors_count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public static class AuthorBean {
        /**
         * origin : 2
         * name : 黎宝儿"
         * gender : u
         * platform_id : 32591230
         * intro : We will and countless people passing by, and many people meet, and some people forward together.
         * is_follow : false
         * nick_id : 18144732
         * id : 66351398308039010
         * headurl : https://avatar.app-remix.com/53J7C7IJBXEFIJ4W.jpg?imageMogr2/thumbnail/300x300/auto-orient
         */

        private int origin;
        private String name;
        private String gender;
        private int platform_id;
        private String intro;
        private boolean is_follow;
        private int nick_id;
        private long id;
        private String headurl;

        public int getOrigin() {
            return origin;
        }

        public void setOrigin(int origin) {
            this.origin = origin;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getPlatform_id() {
            return platform_id;
        }

        public void setPlatform_id(int platform_id) {
            this.platform_id = platform_id;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public boolean isIs_follow() {
            return is_follow;
        }

        public void setIs_follow(boolean is_follow) {
            this.is_follow = is_follow;
        }

        public int getNick_id() {
            return nick_id;
        }

        public void setNick_id(int nick_id) {
            this.nick_id = nick_id;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getHeadurl() {
            return headurl;
        }

        public void setHeadurl(String headurl) {
            this.headurl = headurl;
        }
    }

    public static class ShareBean {
        /**
         * url : http://www.app-remix.com/share/live/66351398308039010
         * caption : 黎宝儿"在热猫开直播，手机快要没电了！厉害了，我的宝宝们。
         * web_info : 直播传送门猛戳
         * title : 热猫
         */

        private String url;
        private String caption;
        private String web_info;
        private String title;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            this.caption = caption;
        }

        public String getWeb_info() {
            return web_info;
        }

        public void setWeb_info(String web_info) {
            this.web_info = web_info;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
