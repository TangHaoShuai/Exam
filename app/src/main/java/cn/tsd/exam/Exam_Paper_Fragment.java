package cn.tsd.exam;

//考试
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.tsd.exam.base.TestQuestions;
import cn.tsd.exam.base.TqType;


public class Exam_Paper_Fragment extends Fragment {
    private TextView count;
    private TextView type;
    public  String title,ex_count,ex_type;
    private TextView textView;
    private LinearLayout linearLayout;
    public TestQuestions testQuestions; //试题
    private Boolean istTheFirstTime  =  true; // 用来判断是不是第一次选择
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,   Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_exam__pape_, container, false);
        init(view);
        if (testQuestions!=null){
            if (testQuestions.getTqType().equals(TqType.SINGLE_CHOICE)){
                RadioGroup group = new RadioGroup(getActivity());
                //封装了View的位置、高、宽等信息
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(100,0,0,100);
                group.setLayoutParams(params);
                List<RadioButton> radioButtons = new ArrayList<>();
                for (int i = 0; i < testQuestions.getOptions().size(); i++) {
                    RadioButton radioButton = new RadioButton(getActivity());
                    radioButton.setText(testQuestions.getOptions().get(i));
                    radioButton.setId(i);
                    radioButtons.add(radioButton);
                    group.addView(radioButton);
                }
                linearLayout.addView(group);
                for (RadioButton radioButton:radioButtons) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)radioButton.getLayoutParams();
                    layoutParams.setMargins(0,0,0,20);
                    radioButton.setLayoutParams(layoutParams);
                }
                ExamPaper examPaper = (ExamPaper) getActivity(); //实例化 考试页面 方便对正确和错误题目数量的操作

                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        for (int i = 0; i < testQuestions.getOptions().size(); i++) {
                          if ( radioButtons.get(i).getId() == checkedId ){
                              //如果选中的答案和正确答案一样
                              if (radioButtons.get(i).getText().toString().trim().equals(testQuestions.getResult().get(0).toString().trim())){
//                                  主要逻辑 判断用户是否是第一次选择 如果是重新选择 选对了 正确的加1 错误的减1
                                  if (istTheFirstTime) {
                                      examPaper.ex_correct++;
                                      istTheFirstTime = false;
                                  }else{
                                      examPaper.ex_correct++;
                                      examPaper.ex_mistake--;
                                  }
                              }else {
                                  if (istTheFirstTime) {
                                      examPaper.ex_mistake++;
                                      istTheFirstTime = false;
                                  }else{
                                      examPaper.ex_mistake++;
                                      examPaper.ex_correct--;
                                  }

                              }
                              System.out.println(radioButtons.get(i).getText());
                          }
                        }
                        examPaper.setStatistics();
                    }
                });
            }
        }

        textView.setText(title);
        count.setText(ex_count);
        type.setText("["+ex_type+"]");
        return view;
    }

    private void init(View view) {
        textView = view.findViewById(R.id.titleName);
        linearLayout = view.findViewById(R.id.box);
        count = (TextView) view.findViewById(R.id.count);
        type = (TextView) view.findViewById(R.id.type);
    }
}