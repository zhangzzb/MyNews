package com.zzb.mynew.bean.va;

import java.util.List;

/**
 * @author 张智斌
 * @time 2017/4/11 9:24
 * @desc ${TODD}
 */

public class HotBean {
    private boolean has_more;
    private List<LiveBean> lives;
    private List<BannerBean> banners;
    private int total;
    private int err;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public List<LiveBean> getLives() {
        return lives;
    }

    public void setLives(List<LiveBean> lives) {
        this.lives = lives;
    }

    public List<BannerBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannerBean> banners) {
        this.banners = banners;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }
}
