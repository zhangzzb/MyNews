package com.zzb.mynew.bean.topic;

import java.util.List;

/**
 * @author 张智斌
 * @time 2017/4/3 17:05
 * @desc ${TODD}
 */

public class TopicBean {
    private List<TopicDetailBean> data;
    private boolean has_more;
    private int total;

    public List<TopicDetailBean> getData() {
        return data;
    }

    public void setData(List<TopicDetailBean> data) {
        this.data = data;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "TopicBean{" +
                "data=" + data +
                ", has_more=" + has_more +
                ", total=" + total +
                '}';
    }
}
