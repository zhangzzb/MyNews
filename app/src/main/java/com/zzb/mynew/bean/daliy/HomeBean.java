package com.zzb.mynew.bean.daliy;

/**
 * @author 张智斌
 * @time 2017/2/27 22:33
 * @desc ${TODD}
 */

public class HomeBean {
    private DaliyBean response;

    public DaliyBean getResponse() {
        return response;
    }

    public void setResponse(DaliyBean response) {
        this.response = response;
    }

    @Override
    public String
    toString() {
        return "HomeBean{" +
                "response=" + response +
                '}';
    }
}
