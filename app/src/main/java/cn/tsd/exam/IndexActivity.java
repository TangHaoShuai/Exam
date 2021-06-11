package cn.tsd.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import cn.tsd.exam.utils.BottomBar;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        BottomBar bottomBar = findViewById(R.id.bottom_bar);
        bottomBar.setContainer(R.id.fl_container)
                .setTitleBeforeAndAfterColor("#999999", "#ff5d5e")
                .addItem(HomeFragment.class,
                        "首页",
                        R.drawable.souye2,
                        R.drawable.souye)
                .addItem(MyQuestionBankFragment.class,
                        "我的题库",
                        R.drawable.tiku,
                        R.drawable.tiku2)
                .addItem(MaterialFragment.class,
                        "资料库",
                        R.drawable.ziliao,
                        R.drawable.ziliao2)
                .addItem(ExamFragment.class,
                        "我的考试",
                        R.drawable.kaoshi,
                        R.drawable.kaoshi1)
                .addItem(MyFragment.class,
                        "我的",
                        R.drawable.wode,
                        R.drawable.wode2)
                .build();
    }
}