package cn.tsd.exam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.tsd.exam.adapter.GridViewAdapter;
import cn.tsd.exam.adapter.MyViewPagerAdapter;
import cn.tsd.exam.base.Academy;
import cn.tsd.exam.base.TestPaper;
import cn.tsd.exam.base.TestQuestions;
import cn.tsd.exam.base.TqType;
import cn.tsd.exam.base.User;
import cn.tsd.exam.utils.Utility;

//考试页面
public class ExamPaper extends AppCompatActivity implements View.OnClickListener {
    private ViewPager mViewPager;
    private MyViewPagerAdapter myViewPagerAdapter; //适配器
    private FragmentManager mFragmentManager; //Fragment管理器
    private List<Fragment> mFragmentList = new ArrayList<Fragment>(); //Fragment集合
    private TestPaper testPaper; //试卷
    private TextView correct, mistake; //正确和错误统计textview
    public Set<String> ex_correct = new HashSet<>();  //统计对题id
    public Set<String> ex_mistake = new HashSet<>(); //统计错题id
    private LinearLayout btn_grade;
    public AlertDialog dialog;
    private LinearLayout btn_submit;
    static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_paper);
        activity = this;
        init();
        mFragmentManager = getSupportFragmentManager();//定义fragment管理器
        initFragmetList();//初始化fragment的列表
        myViewPagerAdapter = new MyViewPagerAdapter(mFragmentManager, mFragmentList);//设置viewpager的适配器
        initViewPager();//初始化viewpager
    }

    public void setStatistics() {
        correct.setText("正确:" + ex_correct.size());
        mistake.setText("错误:" + ex_mistake.size());
    }

    private void init() {
        correct = (TextView) findViewById(R.id.correct);
        mistake = (TextView) findViewById(R.id.mistake);
        btn_grade = findViewById(R.id.btn_grade);
        btn_grade.setOnClickListener(this);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
    }

    private void initViewPager() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.addOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
        mViewPager.setAdapter(myViewPagerAdapter);
        mViewPager.setCurrentItem(0);//初始化显示第一个页面
    }

    public void setPage(int page) {
        mViewPager.setCurrentItem(page);
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
            options.add("C#");
            options.add(".NEW");
            options.add("C++");
            //模拟正确选项
            List<String> result = new ArrayList<String>();

            //试题
            TqType tqType = null;
            if (i<=3){
                tqType  = TqType.COMPLETION;
                result.add("PHP");
                result.add("JAVA");
            }else if (i > 3 && i< 8 ){
                tqType  = TqType.MULTIPLE_CHOICE;
                result.add("PHP");
                result.add("JAVA");
            }else if (i>=8 && i<10){
                tqType  = TqType.SINGLE_CHOICE;
                result.add("PHP");
            }else {
                tqType  = TqType.TRUE_OR_FALSE_QUESTIONS;
                result.add("正确");
            }

            TestQuestions questions = new TestQuestions(String.valueOf(1000 + i), "123", "世界上最好的编程语言是什么?",
                    tqType, "世界上最好的编程语言是PHP", options, result);
            testQuestions.add(questions);
        }


        testPaper = new TestPaper("123", "JAVA基础测试题", "123", testQuestions,
                Academy.ELECTRONIC_INFORMATION, "Java测试题目", "123",
                new User("123", "唐好帅", "移动2", Academy.ADMINISTERED,"13123","12332")); //试卷

        for (int i = 0; i < testPaper.getTestQuestions().size(); i++) { //根据题目有多少个题目就创建多少个fragment
            Exam_Paper_Fragment fragment = new Exam_Paper_Fragment(); //实例化答题界面
            fragment.title = testPaper.getName();//设置试卷题目
            fragment.testQuestions = testPaper.getTestQuestions().get(i);//设置选项
            fragment.ex_type = testPaper.getTestQuestions().get(i).getTqType().getName();//设置选项类型
            fragment.ex_count = i + 1 + "/" + testPaper.getTestQuestions().size(); //设置题目计数
            mFragmentList.add(fragment);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_grade:
                //答题卡对话框
                sowDialog();
                break;
            case R.id.btn_submit:
                int tq_count = testPaper.getTestQuestions().size();
                if (tq_count > (ex_correct.size() + ex_mistake.size())) {
                    showVerifyDialog(false);
                }else{
                    showVerifyDialog(true);
                }
                break;
        }
    }

    private void showVerifyDialog( Boolean isComplete) {
        View view = LayoutInflater.from(this).inflate(R.layout.verify_dialog_layout, null);
        AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        TextView mTvDescribe;
        Button mBtnSubmit;
        Button mBtnLookAnswerSheet;
        Button mBtnCancel;
        mTvDescribe = (TextView) view.findViewById(R.id.tv_describe);
        mBtnSubmit = (Button) view.findViewById(R.id.btn_submit);
        mBtnLookAnswerSheet = (Button) view.findViewById(R.id.btn_look_answer_sheet);
        mBtnCancel = (Button) view.findViewById(R.id.btn_cancel);
        if (isComplete){ // 如果题目全选完了
            mBtnLookAnswerSheet.setVisibility(View.GONE);
            mTvDescribe.setText("确认交卷？");
            mTvDescribe.setGravity(Gravity.CENTER);
        }
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mBtnLookAnswerSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                sowDialog();
            }
        });
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamPaper.this,GradeActivity.class);
                intent.putExtra("count",String.valueOf(testPaper.getTestQuestions().size())); //总题数
                intent.putExtra("ex_correct",String.valueOf(ex_correct.size())); //答对的题数
                startActivity(intent);
            }
        });
        dialog.show();

    }

    //答题卡对话框
    private void sowDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);
        dialog = new AlertDialog.Builder(this).setView(view).create();
        GridView grid_view = view.findViewById(R.id.grid_view);
        GridViewAdapter adapter = new GridViewAdapter(testPaper.getTestQuestions(), this, ex_correct, ex_mistake);
        grid_view.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        dialog.show();
        Window dialogWindow = dialog.getWindow();
//      dialogWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //透明
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();//获取属性对象
        //设置属性
        lp.gravity = Gravity.BOTTOM;
        lp.width = LinearLayout.LayoutParams.MATCH_PARENT;
        lp.height = Utility.getScreenWidth(this) * 4 / 3;
        dialogWindow.setAttributes(lp);
        dialogWindow.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
    }

    private class ViewPagetOnPagerChangedLisenter implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //  Log.d(TAG,"onPageScrooled");
        }

        @Override
        public void onPageSelected(int position) {
            // Log.d("CHANGE","onPageSelected");
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            // Log.d("SCROLLCHANGE","onPageScrollStateChanged");
        }
    }
}