package com.zzb.mynew.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyh.library.imgsel.common.Constant;
import com.zzb.mynew.R;
import com.zzb.mynew.activity.CaptureActivity;
import com.zzb.mynew.api.Constants;
import com.zzb.mynew.common.base.BaseFragment;
import com.zzb.mynew.common.commonutil.FileUtil;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;
import com.zzb.mynew.common.commonutil.SharePrefUtil;
import com.zzb.mynew.dialog.CommonDialog;
import com.zzb.mynew.dialog.MyQrodeDialog;
import com.zzb.mynew.util.DataCleanManager;
import com.zzb.mynew.view.togglebutton.util.ToggleButton;
import com.zzb.mynew.view.userview.UserGuideView;

import java.io.File;

import butterknife.Bind;
import butterknife.OnClick;
import cn.feng.skin.manager.listener.ILoaderListener;
import cn.feng.skin.manager.loader.SkinManager;
import cn.feng.skin.manager.util.L;

/**
 * @author 张智斌
 * @time 2017/3/27 19:06
 * @desc ${TODD}
 */

public class MeFragment extends BaseFragment {
    @Bind(R.id.cache_size_tv)
    TextView mCacheSizeTv;
    @Bind(R.id.auto_send_toggle)
    ToggleButton mAutoSendToggle;
    @Bind(R.id.header_iv)
    ImageView mHeaderIv;
    @Bind(R.id.name_tv)
    TextView mNameTv;
    @Bind(R.id.depart_tv)
    TextView mDepartTv;
    public static final String URL_HEADER = "http://up.qqjia.com/z/25/tu32712_10.jpg";
    @Bind(R.id.guideView)
    UserGuideView mGuideView;
    @Bind(R.id.auto_skin_toggle)
    ToggleButton mAutoSkinToggle;
    @Bind(R.id.tv_scan)
    TextView mTvScan;
    //皮肤切换
    private static final String SKIN_NAME = "BlackFantacy.skin";
    private static final String SKIN_DIR = Environment
            .getExternalStorageDirectory() + File.separator + SKIN_NAME;
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_me;
    }

    @Override
    public void initData() {
        //本地推送,数据回显
        boolean flag = SharePrefUtil.getBoolean(Constants.AUTO_SEND, false);
        if (flag) {
            mGuideView.setHighLightView(mTvScan);
            mAutoSendToggle.setToggleOn();
        } else {
            mAutoSendToggle.setToggleOff();
        }
        ImageLoaderUtils.display(getContext(), mHeaderIv, URL_HEADER);
        boolean flag1 = SharePrefUtil.getBoolean(Constants.AUTO_SKIN_MODE, false);
        if (flag1) {
            mAutoSkinToggle.setToggleOn();
        } else {
            mAutoSkinToggle.setToggleOff();
        }
    }

    @Override
    protected void initView() {
        mAutoSendToggle.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {
                    showShortToast("已启动");
                } else {
                    showShortToast("已关闭");
                }
                SharePrefUtil.saveBoolean(Constants.AUTO_SEND, on);
            }
        });
         /*夜间模式*/
        mAutoSkinToggle.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {
                    //换肤
                    onSkinSetClick();
                } else {
                    //换肤
                    onSkinResetClick();
                }
                SharePrefUtil.saveBoolean(Constants.AUTO_SKIN_MODE, on);
            }
        });
    }
    @OnClick({R.id.clear_layout, R.id.set_server_layout,
            R.id.scan_login_layout, R.id.exit_layout, R.id.update_layout
            , R.id.header_iv,R.id.iv_qr_code
            , R.id.about_layout, R.id.skin_layout, R.id.auto_send_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_layout:
                clearData();
                break;
            case R.id.set_server_layout:
                setServerData();
                break;
            case R.id.scan_login_layout:
                scanLogin();
                break;
            case R.id.exit_layout:
                exitLogin();
                break;
            case R.id.update_layout:
                showShortToast("当前已是最新版本");
                break;
            case R.id.skin_layout:
                mAutoSkinToggle.toggle();
                break;
            case R.id.about_layout:
                final CommonDialog dialog = new CommonDialog(getActivity());
                dialog.setMessage(Html.fromHtml("&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;此练习项目API来源于:网易、好奇心日报、糗事新闻"+"<a href='https://github.com/tigerguixh/QuickNews'>MyView</a><br>"
                        +"&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;实现了二维码扫描、换肤、侧滑返回、MD风格、FloatingActionMenu悬浮菜单同时自己也封装了自定义对话框、吐司、ListView适配器、沉浸式状态栏、大图查看、数据加载界面等大量工具类<br>"
                        +"&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;使用OKhttp封装实现网络请求、请求缓存、运用了诸如：OKhttp、EventBus、bufferknife、skin-loader-lib、photopicker、glide、 jiecaoplayer、vitamio、BaseRecyclerViewHelper等优秀开源项目<br>"
                        +"&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;感谢诸多优秀开源项目，后期将陆续使用新技术插入项目当中、希望关注我的朋友能与我交流学习<br>"));
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                dialog.setNegativeButton("",null);
                dialog.show();
                break;
            case R.id.header_iv:
                break;
            case R.id.iv_qr_code:
                addFriends();
                break;
            case R.id.auto_send_layout:
                mAutoSendToggle.toggle();
                break;
        }
    }
    /*加好友*/
    private void addFriends() {
        MyQrodeDialog dialog =new MyQrodeDialog(getContext());
        dialog.show();
    }
    /*退出登录*/
    private void exitLogin() {
        mNameTv.setText("未登录");
        mDepartTv.setText("所在部门");
        mHeaderIv.setImageResource(R.drawable.avatar_default);
    }
    private void clearData() {
        final CommonDialog dialog = new CommonDialog(getActivity());
        dialog.setMessage("确定要清除所有缓存数据？");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DataCleanManager.cleanApplicationData(getContext(), FileUtil.getDownloadDir(),FileUtil.getIconDir(),FileUtil.getCacheDir());
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    /*扫描登录*/
    private void scanLogin() {
        startActivity(new Intent(getActivity(), CaptureActivity.class));
    }
    private void setServerData() {
        String select[] = {"外网", "内网"};
        final CommonDialog dialog = new CommonDialog(getActivity());
        dialog.setItems(select, SharePrefUtil.getInt(Constant.NETSELECT, 0), new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharePrefUtil.saveInt(Constant.NETSELECT, position);
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void onSkinSetClick() {
        File skin = new File(SKIN_DIR);
        if(skin == null || !skin.exists()){
            showShortToast("请检查" + SKIN_DIR + "是否存在");
            return;
        }
        SkinManager.getInstance().load(skin.getAbsolutePath(),
                new ILoaderListener() {
                    @Override
                    public void onStart() {
                        L.e("startloadSkin");
                    }

                    @Override
                    public void onSuccess() {
                        L.e("loadSkinSuccess");
                        showShortToast("切换成功");
                    }
                    @Override
                    public void onFailed() {
                        L.e("loadSkinFail");
                        showShortToast("切换失败");
                    }
                });
    }
    protected void onSkinResetClick() {
            SkinManager.getInstance().restoreDefaultTheme();
    }
}
