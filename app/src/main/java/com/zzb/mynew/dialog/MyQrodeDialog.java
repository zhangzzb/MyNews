package com.zzb.mynew.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.zzb.mynew.R;
import com.zzb.mynew.common.commonutil.FileUtil;
import com.zzb.mynew.util.QrCodeUtils;

/**
 * Created by poi on 2017/1/6.
 * 生成我的二维码
 */

public class MyQrodeDialog extends Dialog{
    private ImageView mIvCode;
    private Bitmap bitmap;
    public MyQrodeDialog(Context context) {
        this(context, R.style.quick_option_dialog);
    }
    public MyQrodeDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_my_qr_code);
        getWindow().setGravity(Gravity.CENTER);
        initView();
    }
    private void initView() {
        mIvCode = (ImageView)findViewById(R.id.iv_qr_code);
        try {
            bitmap = QrCodeUtils.Create2DCode(String.format(
                    "http://www.baidu.com/%s",""));
            mIvCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        mIvCode.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                boolean result = FileUtil.compressFile(bitmap,"MyNew","myqrcode.png");
                if(result){
                    Toast.makeText(getContext(), "已保存到下载目录", Toast.LENGTH_SHORT).show();
                    dismiss();
                }else{
                    Toast.makeText(getContext(), "二维码保存失败", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
                return false;
            }
        });
    }
}
