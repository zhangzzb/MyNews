package com.zzb.mynew.bean.va;

/**
 * @author 张智斌
 * @time 2017/4/11 9:26
 * @desc ${TODD}
 */

public class BannerBean {
    /**
     * ratio : 0.2778
     * url : https://pic.app-remix.com/1342bdad2400e02.png
     * platform_id : 32559702
     * redirect_type : live
     * redirect_source : 2
     * redirect_id : 65449810878014300
     */

    private double ratio;
    private String url;
    private int platform_id;
    private String redirect_type;
    private int redirect_source;
    private long redirect_id;

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }

    public String getRedirect_type() {
        return redirect_type;
    }

    public void setRedirect_type(String redirect_type) {
        this.redirect_type = redirect_type;
    }

    public int getRedirect_source() {
        return redirect_source;
    }

    public void setRedirect_source(int redirect_source) {
        this.redirect_source = redirect_source;
    }

    public long getRedirect_id() {
        return redirect_id;
    }

    public void setRedirect_id(long redirect_id) {
        this.redirect_id = redirect_id;
    }
}
