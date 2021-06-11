package cn.tsd.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.tsd.exam.adapter.MyViewPagerAdapter;

//考试页面
public class ExamPaper extends AppCompatActivity {
    private ViewPager mViewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_paper);

        mFragmentManager = getSupportFragmentManager();//定义fragment管理器
        initFragmetList();//初始化fragment的列表
        myViewPagerAdapter = new MyViewPagerAdapter(mFragmentManager,mFragmentList);//设置viewpager的适配器
        initViewPager();//初始化viewpager
    }

    private void initViewPager() {
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mViewPager.addOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
        mViewPager.setAdapter(myViewPagerAdapter);
        mViewPager.setCurrentItem(1);//初始化显示第一个页面
    }

    private void initFragmetList() {
        mFragmentList.clear();
        for (int i = 0; i < 5; i++) {
            Exam_Paper_Fragment fragment = new Exam_Paper_Fragment();
            fragment.title = String.valueOf(i);
            mFragmentList.add(fragment);
        }
    }

    private class ViewPagetOnPagerChangedLisenter implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //            Log.d(TAG,"onPageScrooled");

        }
        @Override
        public void onPageSelected(int position) {
            Log.d("CHANGE","onPageSelected");
        }
        @Override
        public void onPageScrollStateChanged(int state) {

            Log.d("SCROLLCHANGE","onPageScrollStateChanged");
        }
    }
}