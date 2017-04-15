package com.zzb.mynew.bean.daliy;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author 张智斌
 * @time 2017/2/27 21:33
 * @desc ${TODD}
 */

public class DaliyBanner implements MultiItemEntity {
    public static final int TYPE_TOP = 0;
    public static final int LOAD_NONE = 1;
    public static final int LOAD_END = 2;
    private String image;
    private int type;
    private Post post;
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Banners{" +
                "image='" + image + '\'' +
                ", type=" + type +
                ", post=" + post +
                '}';
    }

    @Override
    public int getItemType() {
        return type;
    }
}
