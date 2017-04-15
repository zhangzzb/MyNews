package com.zzb.mynew.bean.topic;

import java.util.List;

/**
 * @author 张智斌
 * @time 2017/4/3 17:07
 * @desc ${TODD}
 */

public class TopicDetailBean {

    /**
     * comment_count : 0
     * content : 能买一千快钱的水果，但是却买不了十块钱的零食！作为一个特别喜欢吃水果的女汉子，又特别不喜欢欠人家人情，所以今儿自己跑批发市场弄回来的，回来老大说知道你为什么单身么，这就是原因，我想知道她这话啥意思……😳☺☺
     * created_at : 1491207427
     * distance : 亳州 · 蒙城
     * id : 11511516
     * is_me : false
     * like_count : 0
     * pic_urls : [{"format":"image","pic_url":"http://circle-pic.qiushibaike.com/FueDKbnvBwXWaazFfYmMh6vcM_k6?imageView2/2/w/500/q/80","status":2}]
     * status : 1
     * topic_id : 0
     * type : 1
     * user : {"age":26,"astrology":"天秤座","created_at":1375245915,"gender":"F","icon":"20170401224735.JPEG","id":10418339,"login":"麦之糗","nick_status":0}
     */

    private int comment_count;
    private String content;
    private long created_at;
    private String distance;
    private int id;
    private int like_count;
    private UserBean user;
    private List<PicUrlsBean> pic_urls;

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public List<PicUrlsBean> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<PicUrlsBean> pic_urls) {
        this.pic_urls = pic_urls;
    }

    public static class UserBean {
        /**
         * age : 26
         * astrology : 天秤座
         * created_at : 1375245915
         * gender : F
         * icon : 20170401224735.JPEG
         * id : 10418339
         * login : 麦之糗
         * nick_status : 0
         */

        private int age;
        private String astrology;
        private int created_at;
        private String gender;
        private String icon;
        private int id;
        private String login;
        private int nick_status;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAstrology() {
            return astrology;
        }

        public void setAstrology(String astrology) {
            this.astrology = astrology;
        }

        public int getCreated_at() {
            return created_at;
        }

        public void setCreated_at(int created_at) {
            this.created_at = created_at;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
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

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getNick_status() {
            return nick_status;
        }

        public void setNick_status(int nick_status) {
            this.nick_status = nick_status;
        }

        @Override
        public String toString() {
            return "UserBean{" +
                    "age=" + age +
                    ", astrology='" + astrology + '\'' +
                    ", created_at=" + created_at +
                    ", gender='" + gender + '\'' +
                    ", icon='" + icon + '\'' +
                    ", id=" + id +
                    ", login='" + login + '\'' +
                    ", nick_status=" + nick_status +
                    '}';
        }
    }

    public static class PicUrlsBean {
        /**
         * format : image
         * pic_url : http://circle-pic.qiushibaike.com/FueDKbnvBwXWaazFfYmMh6vcM_k6?imageView2/2/w/500/q/80
         * status : 2
         */

        private String format;
        private String pic_url;
        private int status;

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getPic_url() {
            return pic_url;
        }

        public void setPic_url(String pic_url) {
            this.pic_url = pic_url;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "PicUrlsBean{" +
                    "format='" + format + '\'' +
                    ", pic_url='" + pic_url + '\'' +
                    ", status=" + status +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TopicDetailBean{" +
                "comment_count=" + comment_count +
                ", content='" + content + '\'' +
                ", created_at=" + created_at +
                ", distance='" + distance + '\'' +
                ", id=" + id +
                ", like_count=" + like_count +
                ", user=" + user +
                ", pic_urls=" + pic_urls +
                '}';
    }
}
