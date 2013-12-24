package com.wefeng.launcher;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.wefeng.launcher.util.GetApk;
import com.wefeng.launcher.util.GetSystem;
import com.wefeng.launcher.widget.TabApk;
import com.wefeng.launcher.widget.TabSettings;
import com.wefeng.launcher.widget.TabTop;
import com.wefeng.launcher.widget.TabWatchTv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

public class MainActivity extends Activity {

	private ArrayList<View> mViewList = null;
	private ArrayList<String> mTitleList = null;
	private ViewPager mViewPager = null;
    private RadioButton mRadioTitle1;
    private RadioButton mRadioTitle2;
    private RadioButton mRadioTitle3;
    private RadioButton mRadioTitle4;
    private RadioGroup mRadioGroup = null;
    private GetApk mApk = null;
    private HashMap<String, Drawable> mApkList = null;
    private HashMap<String, PackageInfo>  mPackageList = null;
    private TabApk mTabApk = null;
    private MyOnPageChangeListener myOnPageChangeListener;

    private TextView mTimeText = null;
    private int TIME_REFRESH_TIME = 1000;
    private Handler mHandler = new Handler();
    private Runnable mRefreshTime = new Runnable() {
        @Override
        public void run() {
            mTimeText.setText(GetSystem.getTime());
            mHandler.removeCallbacks(this);
            mHandler.postDelayed(this, TIME_REFRESH_TIME);
        }
    };

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        Log.i("hello","onCreate");
		mViewPager = (ViewPager) findViewById(R.id.main_page_view_page); 
		addPage();
		
		mViewPager.requestFocus();
        mRadioTitle1.setChecked(true);

        ImageView phoneImage = (ImageView)findViewById(R.id.main_page_phone_image);
        phoneImage.setVisibility(View.INVISIBLE);

        ImageView phoneImageTip = (ImageView)findViewById(R.id.main_page_phone_image_tip);
        phoneImageTip.setVisibility(View.INVISIBLE);

        mTimeText = (TextView)findViewById(R.id.main_page_time_text);

        mApk = new GetApk(this, new GetApk.LoadApkFinishListen() {
            @Override
            public void loadApkFinishListen(HashMap<String, Drawable> apkList, HashMap<String, PackageInfo> packageList) {
                mApkList = apkList;
                mPackageList = packageList;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTabApk.setApk(mApkList, mPackageList);
                    }
                });
            }
        });

        mHandler.postDelayed(mRefreshTime, TIME_REFRESH_TIME);
	}

	public void addPage()
	{
		LayoutInflater lf = getLayoutInflater().from(this);
		
		TabWatchTv view1 = new TabWatchTv(this);
        TabTop view2 = new TabTop(this);
        TabApk view3 = new TabApk(this);

		//View view4 = lf.inflate(R.layout.view_tab_settings, null);
        TabSettings view4 = new TabSettings(this);

        mTabApk = view3;

        view1.initImage();
        view2.initImage();
        view3.initImage();
		mViewList = new ArrayList<View>();
		mViewList.add(view1);
		mViewList.add(view2);
		mViewList.add(view3);
		mViewList.add(view4);
		
		mTitleList = new ArrayList<String>();
		mTitleList.add("first");  
		mTitleList.add("channel");  
		mTitleList.add("apps");
		mTitleList.add("settings");
        
        PagerAdapter pagerAdapter = new PagerAdapter() {  
        	  
            @Override  
            public boolean isViewFromObject(View arg0, Object arg1) {  
  
                return arg0 == arg1;  
            }  
  
            @Override  
            public int getCount() {  
  
                return mViewList.size();  
            }  
  
            @Override  
            public void destroyItem(ViewGroup container, int position,  
                    Object object) {  
                container.removeView(mViewList.get(position));  
  
            }  
  
            @Override  
            public int getItemPosition(Object object) {  
  
                return super.getItemPosition(object);  
            }  
  
            @Override  
            public CharSequence getPageTitle(int position) {  
  
                return mTitleList.get(position);
            }  
  
            @Override  
            public Object instantiateItem(ViewGroup container, int position) {  
                container.addView(mViewList.get(position));
                return mViewList.get(position);  
            }  
  
        };  
        mViewPager.setAdapter(pagerAdapter);  
        mViewPager.setCurrentItem(0); 
        myOnPageChangeListener = new MyOnPageChangeListener();
        mViewPager.setOnPageChangeListener(myOnPageChangeListener);
        
        
        mRadioTitle1 = (RadioButton)this.findViewById(R.id.tab_recommend_text);
        mRadioTitle2 = (RadioButton)this.findViewById(R.id.tab_tops_text);
        mRadioTitle3 = (RadioButton)this.findViewById(R.id.tab_channels_text);
        mRadioTitle4 = (RadioButton)this.findViewById(R.id.tab_settings_text);
        
        //mRadioTitle1.setOnClickListener(new MyOnClickListener(0));
        mRadioTitle1.setOnFocusChangeListener(new RadioFocusChangeListener(0));
        mRadioTitle1.setTextColor(getResources().getColor(R.color.white));

        //mRadioTitle2.setOnClickListener(new MyOnClickListener(1));
        mRadioTitle2.setOnFocusChangeListener(new RadioFocusChangeListener(1));
        mRadioTitle2.setTextColor(getResources().getColor(R.color.white));

        //mRadioTitle3.setOnClickListener(new MyOnClickListener(2));
        mRadioTitle3.setOnFocusChangeListener(new RadioFocusChangeListener(2));
        mRadioTitle3.setTextColor(getResources().getColor(R.color.white));

        //mRadioTitle4.setOnClickListener(new MyOnClickListener(3));
        mRadioTitle4.setOnFocusChangeListener(RadioFocusChangeListener3);
        mRadioTitle4.setTextColor(getResources().getColor(R.color.white));

        mRadioGroup = (RadioGroup)findViewById(R.id.main_page_bottom_pannel);
        
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("hello","onNewIntent");
        String str = intent.getAction();
        if(str.equals("settings"))
        {
            Log.i("hello","onNewIntent_settings");
            myOnPageChangeListener.onPageSelected(3);
            selectPage(3);
        }
    }
    private View.OnFocusChangeListener RadioFocusChangeListener3 = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            Log.i("hello","RadioFocusChangeListener3" + b);
            RadioButton rb = (RadioButton)view;
            if(b)
            {
                if(!rb.isChecked())
                {
                    rb.setTextColor(getResources().getColor(R.color.black));
                    rb.setChecked(true);
                    selectPage(3);
                }
                else
                {
                    rb.setTextColor(getResources().getColor(R.color.black));
                }
            }
            else
            {
                rb.setTextColor(getResources().getColor(R.color.white));
            }
        }
    };
    private class RadioFocusChangeListener implements View.OnFocusChangeListener {

        private int mIndex=0;

        public RadioFocusChangeListener(int index)
        {
            mIndex = index;
        }
        @Override
        public void onFocusChange(View view, boolean b) {
            RadioButton rb = (RadioButton)view;

            if(b)
            {
                if(!rb.isChecked())
                {
                    rb.setTextColor(getResources().getColor(R.color.black));
                    rb.setChecked(true);
                    selectPage(mIndex);
                }
                else
                {
                    rb.setTextColor(getResources().getColor(R.color.black));
                }
            }
            else
            {
                rb.setTextColor(getResources().getColor(R.color.white));
            }
        }
    }

//    private class MyOnClickListener implements OnClickListener{
//        private int index=0;
//        public MyOnClickListener(int i){
//            index=i;
//        }
//        public void onClick(View v) {
//        	selectPage(index);
//        }
//
//    }
    
	public void selectPage(int index)
	{
		mViewPager.setCurrentItem(index);
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener{  
        
        public void onPageScrollStateChanged(int arg0) {}
 
        public void onPageScrolled(int arg0, float arg1, int arg2) {}
    
        public void onPageSelected(int arg0) {
            RadioButton rb = (RadioButton)mRadioGroup.getChildAt(arg0);

            if(!rb.isChecked())
            {
                rb.setChecked(true);
            }
        }  
          
    }  

}
