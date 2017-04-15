
# 实践！业余时间编写的一款新闻阅读App
* 整体项目基于 （MVC + OKHttp + RecyclerView）
* 代码优化抽取主要类有BaseActivity、BaseFragment、BaseListFragment、BaseViewPagerFragment.
* 使用recycleview+BaseRecyclerViewHelper第三方优秀库实现整体界面
* 自己也封装了自定义对话框、吐司、ListView适配器、沉浸式状态栏、大图查看、数据加载界面等大量工具类
* 使用OKhttp封装实现网络请求、请求缓存
* 实现了二维码扫描、换肤、侧滑返回、MD风格、FloatingActionMenu悬浮菜单
* 用到了toolbar、cardview、NavigationView、FloatingActionButton、SwipeRefreshLayout、CoordinatorLayout、CollapsingToolbarLayout等新UI控件渲染界面
* 使用了诸如：OKhttp、EventBus、bufferknife、skin-loader-lib、photopicker、glide、jiecaoplayer、vitamio、BaseRecyclerViewHelper等优秀开源项目
* 后期将陆续使用MVP模式、RXJava、Retrofit等新技术插入项目当中、希望关注我的朋友能与我交流学习

![](https://github.com/zhangzzb/MobileSafe/blob/master/screenshots/截图六.png) ![](https://github.com/zhangzzb/MobileSafe/blob/master/screenshots/截图四.png)
![](https://github.com/zhangzzb/MobileSafe/blob/master/screenshots/截图二.png)
### 主界面
* 常见的主页面
* APP分为新闻、直播、动态、我的四个模块
* 感谢诸多优秀开源项目、项目中用到的api所有权归 网易、好奇心日报、糗事新闻所有
* 若内容有侵权请联系作者进行删除处理。本应用仅用作分享与学习
