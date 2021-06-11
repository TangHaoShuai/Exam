package cn.tsd.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

//试卷详情
public class ExamPaperDescribe extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout sequence_exercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_paper_describe);
        init();
    }

    private void init() {
        sequence_exercise = findViewById(R.id.sequence_exercise);
        sequence_exercise.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sequence_exercise:
                Intent intent = new Intent(ExamPaperDescribe.this,ExamPaper.class);
                startActivity(intent);
                break;
        }
    }
}