package cn.tsd.exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.tsd.exam.adapter.MyViewPagerAdapter;
import cn.tsd.exam.base.Academy;
import cn.tsd.exam.base.TestPaper;
import cn.tsd.exam.base.TestQuestions;
import cn.tsd.exam.base.TqType;
import cn.tsd.exam.base.User;

//考试页面
public class ExamPaper extends AppCompatActivity {
    private ViewPager mViewPager;
    private MyViewPagerAdapter myViewPagerAdapter; //适配器
    private FragmentManager mFragmentManager; //Fragment管理器
    private List<Fragment> mFragmentList = new ArrayList<Fragment>(); //Fragment集合
    private TestPaper testPaper; //试卷
    private TextView correct,mistake; //正确和错误统计textview
    public int ex_correct =0 ,ex_mistake = 0; //统计正确和错误
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_paper);
        init();


        mFragmentManager = getSupportFragmentManager();//定义fragment管理器
        initFragmetList();//初始化fragment的列表
        myViewPagerAdapter = new MyViewPagerAdapter(mFragmentManager, mFragmentList);//设置viewpager的适配器
        initViewPager();//初始化viewpager
    }
    public void  setStatistics(){
        correct.setText("正确:"+ex_correct);
        mistake.setText("错误:"+ex_mistake);
    }
    private void init() {
        correct = (TextView) findViewById(R.id.correct);
        mistake = (TextView) findViewById(R.id.mistake);
    }
    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.addOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
        mViewPager.setAdapter(myViewPagerAdapter);
        mViewPager.setCurrentItem(1);//初始化显示第一个页面
    }

    private void initFragmetList() {
        mFragmentList.clear(); //清空fragment

        //模拟试题集合
        ArrayList<TestQuestions> testQuestions = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            //模拟题目选项
            List<String> options = new ArrayList<String>();
            options.add("JAVA");
            options.add("PHP");
            options.add("选项C");
            options.add("选项D");
            options.add("选项E");
            //模拟正确选项
            List<String> result = new ArrayList<String>();
            result.add("PHP");
            TestQuestions questions = new TestQuestions("123", "123", "世界上最好的编程语言是什么?",
                    TqType.SINGLE_CHOICE, "世界上最好的编程语言是PHP", options, result);
            testQuestions.add(questions);
        }


        testPaper = new TestPaper("123", "JAVA基础测试题", "123", testQuestions,
                Academy.ELECTRONIC_INFORMATION, "Java测试题目", "123",
                new User("123", "唐好帅", "移动2", Academy.ADMINISTERED)); //试卷

        for (int i = 0; i < testPaper.getTestQuestions().size(); i++) { //根据题目有多少个题目就创建多少个fragment
            Exam_Paper_Fragment fragment = new Exam_Paper_Fragment();
            fragment.title = testPaper.getName();//设置试卷题目
            fragment.testQuestions = testPaper.getTestQuestions().get(i);//设置选项
            fragment.ex_type = testPaper.getTestQuestions().get(i).getTqType().getName();//设置选项类型
            fragment.ex_count = i + "/" + testPaper.getTestQuestions().size(); //设置题目计数
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
//            Log.d("CHANGE","onPageSelected");
        }

        @Override
        public void onPageScrollStateChanged(int state) {
//            Log.d("SCROLLCHANGE","onPageScrollStateChanged");
        }
    }
}