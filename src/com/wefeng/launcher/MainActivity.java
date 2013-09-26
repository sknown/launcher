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
import android.widget.TextView;
import android.widget.Toast;

import com.wefeng.launcher.widget.TabApp;
import com.wefeng.launcher.widget.TabChannels;
import com.wefeng.launcher.widget.TabTop;

public class MainActivity extends Activity {

	private ArrayList<View> mViewList = null;
	private ArrayList<String> mTitleList = null;
	private ViewPager mViewPager = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mViewPager = (ViewPager) findViewById(R.id.main_page_view_page); 
		addPage();
		
		mViewPager.requestFocus();
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
        
        
        RadioButton textView1 = (RadioButton)this.findViewById(R.id.tab_recommend_text);
        RadioButton textView2 = (RadioButton)this.findViewById(R.id.tab_tops_text);
        RadioButton textView3 = (RadioButton)this.findViewById(R.id.tab_channels_text);
        RadioButton textView4 = (RadioButton)this.findViewById(R.id.tab_settings_text);
        
        textView1.setOnClickListener(new MyOnClickListener(0));  
        textView2.setOnClickListener(new MyOnClickListener(1));  
        textView3.setOnClickListener(new MyOnClickListener(2));
        textView4.setOnClickListener(new MyOnClickListener(3));

        
    } 
    private class MyOnClickListener implements OnClickListener{  
        private int index=0;  
        public MyOnClickListener(int i){  
            index=i;  
        }  
        public void onClick(View v) {  
        	selectPage(index);              
        }  
          
    } 
    
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
            Toast.makeText(MainActivity.this, "��ѡ����"+ mViewPager.getCurrentItem()+"ҳ��", Toast.LENGTH_SHORT).show();  
        }  
          
    }  

}
