package com.wefeng.launcher;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
	}

	public void addPage()
	{
		LayoutInflater lf = getLayoutInflater().from(this);
		
		View view1 = lf.inflate(R.layout.view_tab_apps, null);
		View view2 = lf.inflate(R.layout.view_tab_tops, null);
		View view3 = lf.inflate(R.layout.view_tab_channels, null);
		View view4 = lf.inflate(R.layout.view_tab_settings, null);
		
		mViewList = new ArrayList<View>();// 将要分页显示的View装入数组中
		mViewList.add(view1);
		mViewList.add(view2);
		mViewList.add(view3);
		mViewList.add(view4);
		
		mTitleList = new ArrayList<String>();// 每个页面的Title数据  
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
  
                return mTitleList.get(position);//直接用适配器来完成标题的显示，所以从上面可以看到，我们没有使用PagerTitleStrip。当然你可以使用。  
  
            }  
  
            @Override  
            public Object instantiateItem(ViewGroup container, int position) {  
                container.addView(mViewList.get(position));  
//                weibo_button=(Button) findViewById(R.id.button1);//这个需要注意，我们是在重写adapter里面实例化button组件的，如果你在onCreate()方法里这样做会报错的。  
//                weibo_button.setOnClickListener(new OnClickListener() {  
//                      
//                    public void onClick(View v) {  
//                        intent=new Intent(ViewPagerDemo.this,WeiBoActivity.class);  
//                        startActivity(intent);  
//                    }  
//                });  
                return mViewList.get(position);  
            }  
  
        };  
        mViewPager.setAdapter(pagerAdapter);  
        mViewPager.setCurrentItem(0); 
        mViewPager.setOnPageChangeListener(new MyOnPageChangeListener()); 
        
        
        TextView textView1 = (TextView)this.findViewById(R.id.tab_recommend_text);
        TextView textView2 = (TextView)this.findViewById(R.id.tab_tops_text);
        TextView textView3 = (TextView)this.findViewById(R.id.tab_channels_text);
        TextView textView4 = (TextView)this.findViewById(R.id.tab_settings_text);
        
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
            Toast.makeText(MainActivity.this, "您选择了"+ mViewPager.getCurrentItem()+"页卡", Toast.LENGTH_SHORT).show();  
        }  
          
    }  

}
