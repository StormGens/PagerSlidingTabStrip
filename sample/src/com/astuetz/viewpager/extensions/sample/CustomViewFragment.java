package com.astuetz.viewpager.extensions.sample;


import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.ComplexSlidingTabParams;
import com.astuetz.PagerSlidingTabStrip;

/**
 * Created by zlq on 15/1/8.
 */
public class CustomViewFragment extends DialogFragment {


    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private ContactPagerAdapter adapter;
    private ImageView imageView;
    
    
    int tagBadge1=11;
    int tagBadge2=22;
    int tagBadge3=33;

    public static CustomViewFragment newInstance() {
        CustomViewFragment f = new CustomViewFragment();
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getDialog() != null) {
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        View root = inflater.inflate(R.layout.fragment_custom_contact, container, false);

        tabs = (PagerSlidingTabStrip) root.findViewById(R.id.tabs);
        pager = (ViewPager) root.findViewById(R.id.pager);
        imageView= (ImageView) root.findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tagBadge1++;
                tabs.notifyDataSetChanged();
            }
        });
        adapter = new ContactPagerAdapter();

        pager.setAdapter(adapter);
        tabs.setShouldExpand(true);
        tabs.setViewPager(pager);


        return root;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onStart() {
        super.onStart();

        // change dialog width
        if (getDialog() != null) {

            int fullWidth = getDialog().getWindow().getAttributes().width;

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                Display display = getActivity().getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                fullWidth = size.x;
            } else {
                Display display = getActivity().getWindowManager().getDefaultDisplay();
                fullWidth = display.getWidth();
            }

            final int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                    .getDisplayMetrics());

            int w = fullWidth - padding;
            int h = getDialog().getWindow().getAttributes().height;

            getDialog().getWindow().setLayout(w, h);
        }
    }

    public class ContactPagerAdapter extends PagerAdapter implements PagerSlidingTabStrip.ComplexTabProvider {
        private final String[] TITLES = {"google+", "gmail","gmap"};
        public ContactPagerAdapter() {
            super();
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // looks a little bit messy here
            TextView v = new TextView(getActivity());
            v.setBackgroundResource(R.color.background_window);
            v.setText("PAGE " + (position + 1));
            final int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources()
                    .getDisplayMetrics());
            v.setPadding(padding, padding, padding, padding);
            v.setGravity(Gravity.CENTER);
            container.addView(v, 0);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object view) {
            container.removeView((View) view);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public boolean isViewFromObject(View v, Object o) {
            return v == ((View) o);
        }

        @Override
        public ComplexSlidingTabParams getPageTabParams(int position) {
            ComplexSlidingTabParams params=new ComplexSlidingTabParams();
            params.setShowTextTitle(true);
            params.setTitle(TITLES[position]);
            params.setTitleColorChecked(Color.RED);
            params.setTitleColorNormal(Color.BLACK);
            switch (position){
                case 0:
                    params.setBadgeText(""+tagBadge1);
                    params.setResId(R.drawable.ic_launcher_gplus);
                    params.setCheckedResId(R.drawable.ic_launcher_chrome);
                    break;
                case 1:
                    params.setBadgeText("22");
                    params.setResId(R.drawable.ic_launcher_gmail);
                    params.setCheckedResId(R.drawable.ic_launcher_chrome);
                    break;
                case 2:
                    params.setBadgeText("33");
                    params.setResId(R.drawable.ic_launcher_gmaps);
                    params.setCheckedResId(R.drawable.ic_launcher_chrome);
                    params.setShowTextTitle(true);
                    break;
            }
            return params;
        }
    }
}
