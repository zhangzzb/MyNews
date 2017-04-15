package com.zzb.mynew.bean.hotnews;

/**
 * @author 张智斌
 * @time 2017/2/23 21:48
 * @desc ${TODD}
 */

public class ArticleImageBean {
    String src;
    String ref;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String
    toString() {
        return "ArticleImageBean{" +
                "ref='" + ref + '\'' +
                ", src='" + src + '\'' +
                '}';
    }
}
