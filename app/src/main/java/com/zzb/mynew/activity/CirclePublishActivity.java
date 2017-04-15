package com.zzb.mynew.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuyh.library.imgsel.ImageLoader;
import com.yuyh.library.imgsel.ImgSelActivity;
import com.yuyh.library.imgsel.ImgSelConfig;
import com.zzb.mynew.R;
import com.zzb.mynew.adapter.NinePicturesAdapter;
import com.zzb.mynew.bean.topic.TopicDetailBean;
import com.zzb.mynew.common.base.BaseActivity;
import com.zzb.mynew.common.commonutil.ImageLoaderUtils;
import com.zzb.mynew.common.commonutil.KeyBordUtil;
import com.zzb.mynew.common.commonutil.ToastUitl;
import com.zzb.mynew.common.commonwidget.NoScrollGridView;
import com.zzb.mynew.dialog.CommonDialog;
import com.zzb.mynew.fragment.MeFragment;
import com.zzb.mynew.view.SimpleTextWatcher;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

import static com.zzb.mynew.R.id.gridview;

/**
 * @author 张智斌
 * @time 2017/4/4 0:08
 * @desc ${TODD}
 */

public class CirclePublishActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.et_content)
    EditText mEtContent;
    @Bind(gridview)
    NoScrollGridView mGridview;
    @Bind(R.id.tv_save)
    TextView mTvSave;
    @Bind(R.id.tv_clear)
    TextView mTvClear;
    private static final int MAX_TEXT_LENGTH = 160;
    private NinePicturesAdapter ninePicturesAdapter;
    private int REQUEST_CODE = 200;
    private ImageLoader loader = new ImageLoader() {
        @Override
        public void displayImage(Context context, String path, ImageView imageView) {
            ImageLoaderUtils.display(context, imageView, path);
        }
    };
    @Override
    public int getLayoutId() {
        return R.layout.act_publish_zone;
    }
    @Override
    public void initData() {
        ninePicturesAdapter = new NinePicturesAdapter(this, 9, new NinePicturesAdapter.OnClickAddListener() {
            @Override
            public void onClickAdd(int position) {
                choosePhoto();
            }
        });
        mGridview.setAdapter(ninePicturesAdapter);
    }

    @Override
    public void initView() {
         /*设置焦点模式防止底部覆盖*/
        int mode = WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
        getWindow().setSoftInputMode(mode);
        initToolBar("发布动态", R.drawable.ic_arrow_back, true);
        mTvSave.setOnClickListener(this);
        mTvClear.setOnClickListener(this);
        mTvClear.setText(MAX_TEXT_LENGTH + "");
        mEtContent.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                mTvClear.setText((MAX_TEXT_LENGTH - s.length()) + "");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        KeyBordUtil.showSoftKeyboard(mEtContent);
    }

    @Override
    public void onClick(View view) {
      switch (view.getId()) {
          case  R.id.tv_save:
                saveData();
              break;
          case  R.id.tv_clear:
              handleClearWords();
              break;
      }
    }

    private void saveData() {
        if (!TextUtils.isEmpty(mEtContent.getText().toString())) {
            TopicDetailBean circleItem = new TopicDetailBean();
            circleItem.setContent(mEtContent.getText().toString());
            TopicDetailBean.UserBean bean = new TopicDetailBean.UserBean();
            bean.setLogin("爱迪生");
            bean.setCreated_at(0);
            bean.setIcon(MeFragment.URL_HEADER);
            circleItem.setUser(bean);
            TopicDetailBean.PicUrlsBean urlsBean = new TopicDetailBean.PicUrlsBean();
            urlsBean.setPic_url(getPictureString());
            ArrayList<TopicDetailBean.PicUrlsBean> picUrlsBeen = new ArrayList<>();
            picUrlsBeen.add(urlsBean);
            circleItem.setPic_urls(picUrlsBeen);
            //发送数据
            EventBus.getDefault().post(circleItem);
            finish();
        } else {
            ToastUitl.showToastWithImg("数据不能为空", R.drawable.ic_empty_picture);
        }
    }

    /**
     * 启动入口
     *
     * @param context
     */
    public static void startAction(Context context) {
        Intent intent = new Intent(context, CirclePublishActivity.class);
        context.startActivity(intent);
    }
    /**
     * 开启图片选择器
     */
    private void choosePhoto() {
        ImgSelConfig config = new ImgSelConfig.Builder(loader)
                // 是否多选
                .multiSelect(true)
                // 确定按钮背景色
                .btnBgColor(Color.TRANSPARENT)
                .titleBgColor(ContextCompat.getColor(this, R.color.main_color))
                // 使用沉浸式状态栏
                .statusBarColor(ContextCompat.getColor(this, R.color.main_color))
                // 返回图标ResId
                .backResId(R.drawable.ic_arrow_back)
                .title("图片")
                // 第一个是否显示相机
                .needCamera(true)
                // 最大选择图片数量
                .maxNum(9 - ninePicturesAdapter.getPhotoCount())
                .build();
        ImgSelActivity.startActivity(this, config, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra(ImgSelActivity.INTENT_RESULT);
            if (ninePicturesAdapter != null) {
                ninePicturesAdapter.addData(pathList);
            }
        }
    }

    /**
     * 获取到拼接好的图片
     *
     * @return
     */
    private String getPictureString() {
        //拼接图片链接
        List<String> strings = ninePicturesAdapter.getData();
        if (strings != null && strings.size() > 0) {
            StringBuilder allUrl = new StringBuilder();
            for (int i = 0; i < strings.size(); i++) {
                if (!TextUtils.isEmpty(strings.get(i))) {
                    allUrl.append(strings.get(i) + ";");
                }
            }
            if (!TextUtils.isEmpty(allUrl)) {
                String url = allUrl.toString();
                url = url.substring(0, url.lastIndexOf(";"));
                return url;
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
    private void handleClearWords() {
        if (TextUtils.isEmpty(mEtContent.getText().toString()))
            return;
        final CommonDialog dialog = new CommonDialog(CirclePublishActivity.this);
        dialog.setMessage(R.string.clearwords);
        dialog.setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mEtContent.getText().clear();
                    }
                });
        dialog.setNegativeButton(R.string.cancle, null);
        dialog.show();
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.staystill, R.anim.activity_out);
    }
}
