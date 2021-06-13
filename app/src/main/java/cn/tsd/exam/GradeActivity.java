package cn.tsd.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GradeActivity extends AppCompatActivity {
    private TextView mTvTotal;
    private TextView mTvGrade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        ExamPaper.activity.finish(); //结束上一个activity
        init();
        Intent intent = getIntent();
        int count = Integer.parseInt(intent.getStringExtra("count"));
        int ex_correct = Integer.parseInt(intent.getStringExtra("ex_correct"));
        double grade = ((double) ex_correct / (double)count) * 100;
        //利用字符串格式化的方式实现四舍五入,保留1位小数
        String result = String.format("%.1f",grade);
        //1代表小数点后面的位数, 不足补0。f代表数据是浮点类型。保留2位小数就是“%.2f”，依此累推。
        mTvGrade.setText("您的成绩是:"+result+"分");
    }

    private void init() {
        mTvTotal = (TextView) findViewById(R.id.tv_Total);
        mTvGrade = (TextView) findViewById(R.id.tv_grade);
    }
}