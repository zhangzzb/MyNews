package com.zzb.mynew.bean.ads;

import java.io.Serializable;
import java.util.List;

/**
 * 名字必须一致
 * Created by poi on 2017/1/19.
 */

public class AdsBeans implements Serializable{
    List<AdsBean> ads;
    int next_req;
    public int getNext_req() {
        return next_req;
    }

    public void setNext_req(int next_req) {
        this.next_req = next_req;
    }

    public List<AdsBean> getAds() {
        return ads;
    }

    public void setAds(List<AdsBean> ads) {
        this.ads = ads;
    }

    @Override
    public String toString() {
        return "AdsBeans{" +
                "ads=" + ads +
                ", next_req=" + next_req +
                '}';
    }
}
