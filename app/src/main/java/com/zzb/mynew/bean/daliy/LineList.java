package com.zzb.mynew.bean.daliy;

/**
 * @author 张智斌
 * @time 2017/2/28 0:12
 * @desc ${TODD}
 */

public class LineList {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "LineList{" +
                "description='" + description + '\'' +
                '}';
    }
}
