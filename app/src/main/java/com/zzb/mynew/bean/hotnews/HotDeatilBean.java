package com.zzb.mynew.bean.hotnews;

import java.util.List;

/**
 * @author 张智斌
 * @time 2017/2/23 21:43
 * @desc ${TODD}
 */

public class HotDeatilBean {
    String title;
    String source;
    String articleTags;
    String ptime;
    String body;
    List<ArticleImageBean> img;
    int replyCount;

    public String getArticleTags() {
        return articleTags;
    }

    public void setArticleTags(String articleTags) {
        this.articleTags = articleTags;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<ArticleImageBean> getImg() {
        return img;
    }

    public void setImg(List<ArticleImageBean> img) {
        this.img = img;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "HotDeatilBean{" +
                "articleTags='" + articleTags + '\'' +
                ", title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", ptime='" + ptime + '\'' +
                ", body='" + body + '\'' +
                ", img=" + img +
                ", replyCount=" + replyCount +
                '}';
    }
}
