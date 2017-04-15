package com.zzb.mynew.activity;

import android.content.Context;
import android.content.Intent;

import com.zzb.mynew.R;
import com.zzb.mynew.common.base.BaseActivity;
import com.zzb.mynew.fragment.MeFragment;

/**
 * @author 张智斌
 * @time 2017/4/4 14:42
 * @desc ${TODD}
 */

public class SettingActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
    }
    @Override
    public void initData() {
        getSupportFragmentManager().beginTransaction().replace(R.id.setting_container,new MeFragment()).commit();
    }
    @Override
    public void initView() {
        initToolBar("设置中心",R.drawable.actionbar_back_icon_normal,true);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.staystill, R.anim.activity_out);
    }
    /**
     * 启动入口
     *
     * @param context
     */
    public static void startAction(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
}
