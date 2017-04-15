package com.zzb.mynew.bean.daliy;

import java.util.List;

/**
 * @author 张智斌
 * @time 2017/2/27 21:36
 * @desc ${TODD}
 */

public class HeadLine {
    Post post;
    List<LineList> list;
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<LineList> getList() {
        return list;
    }

    public void setList(List<LineList> list) {
        this.list = list;
    }
}
