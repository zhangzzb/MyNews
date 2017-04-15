package com.zzb.mynew.bean.ads;

import java.io.Serializable;
import java.util.List;

/**
 * Created by poi on 2017/1/19.
 */
public class AdsBean implements Serializable{
    List<String> res_url;
    Action action_params;

    public Action getAction_params() {
        return action_params;
    }

    public void setAction_params(Action action_params) {
        this.action_params = action_params;
    }

    public List<String> getRes_url() {
        return res_url;
    }

    public void setRes_url(List<String> res_url) {
        this.res_url = res_url;
    }

    @Override
    public String toString() {
        return "AdsBean{" +
                "action_params=" + action_params +
                ", res_url=" + res_url +
                '}';
    }

    public class Action implements Serializable{
        String link_url;

        public String getLink_url() {
            return link_url;
        }

        public void setLink_url(String link_url) {
            this.link_url = link_url;
        }

        @Override
        public String toString() {
            return "Action{" +
                    "link_url='" + link_url + '\'' +
                    '}';
        }
    }
}
