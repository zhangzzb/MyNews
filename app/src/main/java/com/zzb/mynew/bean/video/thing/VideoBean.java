package com.zzb.mynew.bean.video.thing;

import java.util.List;

/**
 * @author 张智斌
 * @time 2017/3/3 14:51
 * @desc ${TODD}
 */

public class VideoBean {
    private int total;
    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "VideoBean{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }
}
