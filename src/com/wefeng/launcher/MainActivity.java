package com.wefeng.launcher;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wefeng.launcher.widget.TabApp;
import com.wefeng.launcher.widget.TabChannels;
import com.wefeng.launcher.widget.TabTop;

public class MainActivity extends Activity {

	private ArrayList<View> mViewList = null;
	private ArrayList<String> mTitleList = null;
	private ViewPager mViewPager = null;
    private RadioButton mRadioTitle1;
    private RadioButton mRadioTitle2;
    private RadioButton mRadioTitle3;
    private RadioButton mRadioTitle4;
    private RadioGroup mRadioGroup = null;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mViewPager = (ViewPager) findViewById(R.id.main_page_view_page); 
		addPage();
		
		mViewPager.requestFocus();
        mRadioTitle1.setChecked(true);
	}

	public void addPage()
	{
		LayoutInflater lf = getLayoutInflater().from(this);
		
		TabApp view1 = (TabApp)new TabApp(this);
        TabTop view2 = (TabTop)new TabTop(this);

        TabChannels view3 = new TabChannels(this);
		View view4 = lf.inflate(R.layout.view_tab_settings, null);

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
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener()); 
        
        
        mRadioTitle1 = (RadioButton)this.findViewById(R.id.tab_recommend_text);
        mRadioTitle2 = (RadioButton)this.findViewById(R.id.tab_tops_text);
        mRadioTitle3 = (RadioButton)this.findViewById(R.id.tab_channels_text);
        mRadioTitle4 = (RadioButton)this.findViewById(R.id.tab_settings_text);
        
        //mRadioTitle1.setOnClickListener(new MyOnClickListener(0));
        mRadioTitle1.setOnFocusChangeListener(new RadioFocusChangeListener(0));

        //mRadioTitle2.setOnClickListener(new MyOnClickListener(1));
        mRadioTitle2.setOnFocusChangeListener(new RadioFocusChangeListener(1));

        //mRadioTitle3.setOnClickListener(new MyOnClickListener(2));
        mRadioTitle3.setOnFocusChangeListener(new RadioFocusChangeListener(2));

        //mRadioTitle4.setOnClickListener(new MyOnClickListener(3));
        mRadioTitle4.setOnFocusChangeListener(new RadioFocusChangeListener(3));

        mRadioGroup = (RadioGroup)findViewById(R.id.main_page_bottom_pannel);
        
    }
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
                    rb.setChecked(true);
                    selectPage(mIndex);
                }
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
        
        public void onPageScrollStateChanged(int arg0) {  
              
              
        }  
  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
              
              
        }  
  
        public void onPageSelected(int arg0) {
            RadioButton rb = (RadioButton)mRadioGroup.getChildAt(arg0);

            if(!rb.isChecked())
            {
                rb.setChecked(true);
            }
        }  
          
    }  

}
