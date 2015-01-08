package com.astuetz.viewpager.extensions.sample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.ComplexSlidingTabParams;
import com.astuetz.PagerSlidingTabStrip;


public class TabsInBottomActivity extends Activity {

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private ContactPagerAdapter adapter;
    private ImageView imageView;

    int tagBadge1=11;
    int tagBadge2=22;
    int tagBadge3=33;
    int tagBadge4=33;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs_in_bottom);
        initViews();
    }
    
    private void initViews(){
        tabs = (PagerSlidingTabStrip)findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new ContactPagerAdapter();

        pager.setAdapter(adapter);
        tabs.setShouldExpand(true);
        tabs.setViewPager(pager);
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabs_in_bottom, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        switch (id){
            case R.id.action_plus:
                tagBadge1++;
                tabs.notifyDataSetChanged();
                break;
            case R.id.action_original:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class ContactPagerAdapter extends PagerAdapter implements PagerSlidingTabStrip.ComplexTabProvider {
        private final String[] TITLES = {"首页", "商务","笔记","我的"};
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
            TextView v = new TextView(TabsInBottomActivity.this
            );
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
                    params.setResId(R.drawable.tab_icon_property);
                    params.setCheckedResId(R.drawable.tab_icon_property_select);
                    break;
                case 1:
                    params.setBadgeText("22");
                    params.setResId(R.drawable.tab_icon_home);
                    params.setCheckedResId(R.drawable.tab_icon_home_select);
                    break;
                case 2:
                    params.setBadgeText("33");
                    params.setResId(R.drawable.tab_icon_daikan);
                    params.setCheckedResId(R.drawable.tab_icon_daikan_select);
                    params.setShowTextTitle(true);
                    break;
                case 3:
                    params.setBadgeText(tagBadge4+"");
                    params.setResId(R.drawable.tab_icon_client);
                    params.setCheckedResId(R.drawable.tab_icon_client_select);
                    break;
            }
            return params;
        }
    }
}
