package com.pro.foru.activity;


/*
启动引导页
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.pro.foru.foru.R;
import com.pro.foru.utils.ACache;
import com.pro.foru.utils.F;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

public class WelcomeActivity extends Activity {
    private ViewPager viewPager;
    private View view1, view2, view3;//需要滑动的页卡
    Button bt_welcome_done;
    CircleIndicator indicator;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_welcome);

        sharedPreferences = getSharedPreferences("ISFIRSTSTART", MODE_WORLD_WRITEABLE);
        boolean ISFIRSTSTART = sharedPreferences.getBoolean("ISFIRSTSTART", true);
        if(ISFIRSTSTART){
           initview();
        }
        else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    public void initview() {
        //--------------------------------------------------------initviewpager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        indicator = (CircleIndicator) findViewById(R.id.indicator);
        LayoutInflater lf = getLayoutInflater().from(this);
        view1 = lf.inflate(R.layout.welcome_page1, null);
        view2 = lf.inflate(R.layout.welcome_page2, null);
        view3 = lf.inflate(R.layout.welcome_page3, null);

        ArrayList<View> viewList=new ArrayList();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        MyViewPagerAdapter adapter = new MyViewPagerAdapter(viewList);
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        //----------------------------------------------------------findviewbyid

        bt_welcome_done = (Button) view3.findViewById(R.id.bt_welcome_done);
        //----------------------------------------------------------
        bt_welcome_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("ISFIRSTSTART", false).commit();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private ArrayList<View> mListViews;

        public MyViewPagerAdapter(ArrayList<View> mListViews) {
            this.mListViews = mListViews;//构造方法，参数是我们的页卡，这样比较方便。
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)   {
            container.removeView(mListViews.get(position));//删除页卡
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
            container.addView(mListViews.get(position), 0);//添加页卡
            return mListViews.get(position);
        }

        @Override
        public int getCount() {
            return  mListViews.size();//返回页卡的数量
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0==arg1;
        }
    }

}