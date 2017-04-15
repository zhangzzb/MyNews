package com.zzb.mynew.bean.daliy;

import java.util.List;

/**
 * @author 张智斌
 * @time 2017/2/27 21:20
 * @desc ${TODD}
 */

public class DaliyBean {
    private List<DaliyBanner> feeds;
    private boolean has_more;
    private HeadLine headline;
    private String last_key;
    private List<DaliyBanner> banners;

    public List<DaliyBanner> getBanners() {
        return banners;
    }

    public void setBanners(List<DaliyBanner> banners) {
        this.banners = banners;
    }

    public List<DaliyBanner> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<DaliyBanner> feeds) {
        this.feeds = feeds;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public HeadLine getHeadline() {
        return headline;
    }

    public void setHeadline(HeadLine headline) {
        this.headline = headline;
    }

    public String getLast_key() {
        return last_key;
    }

    public void setLast_key(String last_key) {
        this.last_key = last_key;
    }

    @Override
    public String toString() {
        return "DaliyBean{" +
                "banners=" + banners +
                ", feeds=" + feeds +
                ", has_more=" + has_more +
                ", headline=" + headline +
                ", last_key='" + last_key + '\'' +
                '}';
    }
}
