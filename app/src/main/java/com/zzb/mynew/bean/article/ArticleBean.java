package com.zzb.mynew.bean.article;

import java.io.Serializable;
import java.util.List;

/**
 * @author 张智斌
 * @time 2017/4/5 22:52
 * @desc ${TODD}
 */

public class ArticleBean {
    private int count;
    private int err;
    private int total;
    private int page;
    private int refresh;
    private List<ItemsBean> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRefresh() {
        return refresh;
    }

    public void setRefresh(int refresh) {
        this.refresh = refresh;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean implements Serializable{
        /**
         * format : word
         * image : null
         * published_at : 1483485601
         * tag :
         * user : {"avatar_updated_at":1482931660,"medium":"//pic.qiushibaike.com/system/avtnew/2883/28831688/medium/20161228212740.JPEG","thumb":"//pic.qiushibaike.com/system/avtnew/2883/28831688/thumb/20161228212740.JPEG","last_visited_at":1433551498,"created_at":1433551498,"updated_at":1483148626,"state":"active","role":"","login":"7f3a6839841d535c","last_device":"android_6.7.1","id":28831688,"icon":"20161228212740.JPEG"}
         * image_size : null
         * id : 118317481
         * votes : {"down":-4,"up":361}
         * created_at : 1483482509
         * content : 62114eec8fd976f84eb290fd89815148770b76f87247ff0c672c4eba957f76f85c1a53efff0c6211598862ff7740621176847167724753bb76f88fc7n6b214eb290fd6ca167096210529fff0c867d713662114e0d4ee54e3a71364f464e5f756589c95947602aff0c76f452306211770b523090a35f2076f8724720262026
         * 59885988ff0c60a862ff7740621190a35f2062cd5f9750cf56db53415c81800159734eba76848bc14ef6716753bb76f84eb2786e5b9a662f60f3628a62115ac151fa53bb800c4e0d662f8ba962115b6472ec7ec880014e48ff1fff1fff1fff1f
         * state : publish
         * comments_count : 27
         * allow_comment : true
         * share_count : 7
         * type : fresh
         */
        private String format;
        private Object image;
        private int published_at;
        private String tag;
        private UserBean user;
        private Object image_size;
        private int id;
        private VotesBean votes;
        private int created_at;
        private String content;
        private String state;
        private int comments_count;
        private boolean allow_comment;
        private int share_count;
        private String type;

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public Object getImage() {
            return image;
        }

        public void setImage(Object image) {
            this.image = image;
        }

        public int getPublished_at() {
            return published_at;
        }

        public void setPublished_at(int published_at) {
            this.published_at = published_at;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public Object getImage_size() {
            return image_size;
        }

        public void setImage_size(Object image_size) {
            this.image_size = image_size;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public VotesBean getVotes() {
            return votes;
        }

        public void setVotes(VotesBean votes) {
            this.votes = votes;
        }

        public int getCreated_at() {
            return created_at;
        }

        public void setCreated_at(int created_at) {
            this.created_at = created_at;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public boolean isAllow_comment() {
            return allow_comment;
        }

        public void setAllow_comment(boolean allow_comment) {
            this.allow_comment = allow_comment;
        }

        public int getShare_count() {
            return share_count;
        }

        public void setShare_count(int share_count) {
            this.share_count = share_count;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class UserBean implements Serializable{
            private int avatar_updated_at;
            private String medium;
            private String thumb;
            private int last_visited_at;
            private int created_at;
            private int updated_at;
            private String state;
            private String role;
            private String login;
            private String last_device;
            private int id;
            private String icon;

            public int getAvatar_updated_at() {
                return avatar_updated_at;
            }

            public void setAvatar_updated_at(int avatar_updated_at) {
                this.avatar_updated_at = avatar_updated_at;
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

            public int getLast_visited_at() {
                return last_visited_at;
            }

            public void setLast_visited_at(int last_visited_at) {
                this.last_visited_at = last_visited_at;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public int getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(int updated_at) {
                this.updated_at = updated_at;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getLogin() {
                return login;
            }

            public void setLogin(String login) {
                this.login = login;
            }

            public String getLast_device() {
                return last_device;
            }

            public void setLast_device(String last_device) {
                this.last_device = last_device;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

        public static class VotesBean implements Serializable {

            private int down;
            private int up;

            public int getDown() {
                return down;
            }

            public void setDown(int down) {
                this.down = down;
            }

            public int getUp() {
                return up;
            }

            public void setUp(int up) {
                this.up = up;
            }
        }
    }
}
