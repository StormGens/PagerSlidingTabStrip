package com.astuetz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.pagerslidingtabstrip.R;

/**
 * Created by zlq on 15/1/8.
 */
public class ComplexTabView extends FrameLayout implements TwoStateTab {
    private View rootView;
    private TextView titleTv;
    private ImageView imageView;
    
    private ComplexSlidingTabParams mParams;
    
    public ComplexTabView(Context context,ComplexSlidingTabParams params) {
        super(context);
        initView();
        mParams=params;
        initData();
    }

    private void initData() {
        BadgeView badge=new BadgeView(getContext());
        badge.setTargetView(rootView);
        
        badge.setText(mParams.getBadgeText());
        imageView.setImageResource(mParams.getResId());
        titleTv.setText(mParams.getTitle());
    }

    private void initView(){
        rootView=LayoutInflater.from(getContext()).inflate(R.layout.custom_tab_view,null);
        titleTv= (TextView) rootView.findViewById(R.id.complex_tab_title_tv);
        imageView= (ImageView) rootView.findViewById(R.id.complex_tab_iv);
        addView(rootView);
    }

    @Override
    public void switchToChecked() {
        if (mParams.getTitleColorChecked()!=0){
            titleTv.setTextColor(mParams.getTitleColorChecked());
        }
        imageView.setImageResource(mParams.getCheckedResId());

    }

    @Override
    public void switchToUnChecked() {
        imageView.setImageResource(mParams.getResId());
        titleTv.setTextColor(mParams.getTitleColorNormal());
    }

    public void setmParams(ComplexSlidingTabParams params) {
        this.mParams = params;
    }
}
