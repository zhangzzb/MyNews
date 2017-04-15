package com.zzb.mynew.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zzb.mynew.R;


/**
 * @author 张智斌
 * @time 2017/2/18 20:11
 * @desc ${TODD}
 */

public class ToolsView extends LinearLayout {
    private final String SPACHNAME = "http://schemas.android.com/apk/res-auto";
    private String mTitle;
    private int  mColor;
    public ToolsView(Context context) {
        this(context, null);
    }

    public ToolsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ToolsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTitle = attrs.getAttributeValue(SPACHNAME, "title");
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ToolsView);
        mColor = typedArray.getColor(R.styleable.ToolsView_toolcolor, 0);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.setting_view_layout, this);
        TextView bigTitle = (TextView) view.findViewById(R.id.tv_title);
        //ImageView imageView=(ImageView)view.findViewById(R.id.iv_toggle);
        bigTitle.setText(mTitle);
        //imageView.setBackgroundColor(mColor);
    }
}
