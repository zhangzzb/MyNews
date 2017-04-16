package com.zzb.mynew.api;

/**
 * 所有的网址常量类
 */

public class ApiClient {
    private static final String EMBARRASSING_THINGS_URL = "http://m2.qiushibaike.com/";
    //广告请求地址
    public static final String AD_URI = "http://g1.163.com/madr?app=7A16FBB6&platform=android&category=STARTUP&location=1&timestamp=1462779408364&uid=OaBKRDb%2B9FBz%2FXnwAuMBWF38KIbARZdnRLDJ6Kkt9ZMAI3VEJ0RIR9SBSPvaUWjrFtfw1N%2BgxquT0B2pjMN5zsxz13RwOIZQqXxgjCY8cfS8XlZuu2bJj%2FoHqOuDmccGyNEtV%2FX%2FnBofofdcXyudJDmBnAUeMBtnIzHPha2wl%2FQnUPI4%2FNuAdXkYqX028puyLDhnigFtrX1oiC2F7UUuWhDLo0%2BE0gUyeyslVNqLqJCLQ0VeayQa%2BgbsGetk8JHQ";
    /*热点新闻*/
    public static final String HOT_NEWS_JSON_URL = "http://c.m.163.com/nc/article/headline/T1348647909107/START-END.html?from=toutiao&size=10&passport=&devId=bMo6EQztO2ZGFBurrbgcMQ%3D%3D&net=wifi";
    public static final String HOT_NEWS_JSON_URL_REFRESH = "http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html";
    public static final String NEWS_DETAIL_JSON_URL = "http://c.m.163.com/nc/article/DOCID/full.html";
    /*娱乐*/
    public static final String DALIY_NEWS_COMMETN_URL = "http://app3.qdaily.com/app3/home/index/%s.json";
    //纯文
    private static final String PLAIN_URL = EMBARRASSING_THINGS_URL + "list/text?page=DOCID&count=30&readarticles=[118313381]&rqcnt=30&r=104cc21a1483441059936";
    private static final String COMMENTARY_URL = EMBARRASSING_THINGS_URL + "DOCID/latest/comments?page=1&count=50&rqcnt=153&r=104cc21a1483519946841";
    //视频
    private static final String VIDEO_URL = EMBARRASSING_THINGS_URL + "list/video?page=VIEDIOID&count=30&readarticles=[118313064]&rqcnt=33&r=104cc21a1483441099377";
    //好友圈
    private static final String QYQ_URL = EMBARRASSING_THINGS_URL+"/article/list?page=DOCID";
    //直播
    private static final String BROADCAST_URL = "http://live.qiushi.com/live/list?count=30&page=DOCID&rqcnt=23&r=104cc213429685602";
    private static final String DISCOVERY_URL = "http://live.qiushi.com/live/list?count=30&page=DOCID&rqcnt=16&r=104cc483614468422";
    public static final String getHotUrl(int start, int end, boolean isRefresh) {
        String url;
        if (isRefresh) {
            url = HOT_NEWS_JSON_URL;
            url = url.replace("START", String.valueOf(start))
                    .replace("END", String.valueOf(end));
        } else {
            url = HOT_NEWS_JSON_URL_REFRESH;
        }
        return url;
    }
    public static final String getNewsDetailUrl(String docId) {
        String url = NEWS_DETAIL_JSON_URL;
        url = url.replace("DOCID", docId);
        return url;
    }
    public static final String getDalitUrl(String docId) {
        String url = String.format(DALIY_NEWS_COMMETN_URL, docId);
        url = url.replace("COMMENTID", docId);
        return url;
    }
    /**
     * @return 返回动态页面的URL
     */
    public static String getTopicUrl(int id) {
        return QYQ_URL.replace("DOCID", String.valueOf(id));
    }
    public static String getVideoUrl() {
        return "http://c.m.163.com/recommend/getChanListNews?channel=T1457068979049&size=20";
    }
    /**
     * @param id 索引
     * @return 返回纯文页面的URL
     */
    public static String getPlainUrl(int id) {
        return PLAIN_URL.replace("DOCID", id + "");
    }
    /**
     * @param id 帖子的ID
     * @return 返回评论的URL
     */
    public static String getCommentaryUrl(long id) {
        return COMMENTARY_URL.replace("DOCID", id + "");
    }
    /**
     * 糗事百科
     * @param id 索引
     * @return 返回视频页面的URL
     */
    public static String getVideoUrl(int id) {
        return VIDEO_URL.replace("VIEDIOID", String.valueOf(id));
    }
    /** 直播 **/

    /**
     * @param id
     * @return 返回直播全部界面的URL
     */
    public static String getBroadcastUrl(int id) {
        return BROADCAST_URL.replace("DOCID", id + "");
    }

    /**
     * @param id
     * @return 返回直播发现界面的URL
     */
    public static String getDiscoveryUrl(int id) {
        return DISCOVERY_URL.replace("DOCID", id + "");
    }
}
