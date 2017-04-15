package com.zzb.mynew.bean.hotnews;

import java.util.List;

/**
 * 头条新闻数据
 * 使用Gson解析时,对象书写技巧: 1. 逢{}创建对象, 逢[]创建集合(ArrayList) 2. 所有字段名称要和json返回字段高度一致
 */

public class HotNewsBean {
    public List<NewDetailBean> T1348647909107;
    public class NewDetailBean {
        public List<BannerBean> ads;//轮播图
        public String imgsrc;//图片地址
        public String title;//标题
        public String source;//新闻来源
        public String replyCount;//跟帖数
        public String docid;//新闻的id,用来标记当前是哪条新闻,方便后面展示详情
        public String specialID;//是否为专题,我这里简单处理,认为有specialID,就是置顶帖
        public String ptime;//时间
        public class BannerBean {
            public String imgsrc;
            public String title;
            public String url;
        }
    }
}
