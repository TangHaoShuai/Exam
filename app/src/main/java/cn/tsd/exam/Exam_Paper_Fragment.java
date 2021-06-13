package cn.tsd.exam;


import android.graphics.Color;
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
import java.util.Iterator;
import java.util.List;

import cn.tsd.exam.base.TestQuestions;
import cn.tsd.exam.base.TqType;
import cn.tsd.exam.utils.Utility;

//答题界面
public class Exam_Paper_Fragment extends Fragment {
    private TextView count;
    private TextView type;
    public  String title,ex_count,ex_type;
    private TextView textView,tv_describe;
    private LinearLayout linearLayout;
    public TestQuestions testQuestions; //试题
    public static final String[] letters = new String[]{"A","B","C","D","E","F","G","H","I",
            "J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private int label = 0; //用来记录正确答案所在的选项
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,   Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_exam__pape_, container, false);
        init(view);
        if (testQuestions!=null){
            if (testQuestions.getTqType().equals(TqType.SINGLE_CHOICE)){
                RadioGroup group = new RadioGroup(getActivity());
                //封装了View的位置、高、宽等信息
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0,0,0,100);
                group.setLayoutParams(params);
                List<RadioButton> radioButtons = new ArrayList<>();
                for (int i = 0; i < testQuestions.getOptions().size(); i++) {
                    String tq = testQuestions.getOptions().get(i); //选项
                    if (tq.equals(testQuestions.getResult().get(0))){ //当前选项和正确答案一样 就标记一下他
                        label = i;
                    }
                    RadioButton radioButton = new RadioButton(getActivity());
                    radioButton.setText(letters[i]+"  "+tq);
                    radioButton.setId(i);
                    radioButtons.add(radioButton);
                    group.addView(radioButton);
                }
                linearLayout.addView(group);
                for (RadioButton radioButton:radioButtons) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)group.getLayoutParams();
                    layoutParams.setMargins(20,0,0,20);
                    radioButton.setLayoutParams(layoutParams);
                }
                ExamPaper examPaper = (ExamPaper) getActivity(); //实例化 考试页面 方便对正确和错误题目数量的操作

                //点击选项监听
                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        for (int i = 0; i < testQuestions.getOptions().size(); i++) {
                          if ( radioButtons.get(i).getId() == checkedId ){
                              //如果选中的答案和正确答案一样
                              if (radioButtons.get(i).getText().toString().substring(1).trim().equals(testQuestions.getResult().get(0).toString().trim())){
                                  Utility.setRadioButtonsStyle(radioButtons.get(i),radioButtons,Color.parseColor("#00FF00"));
                                  tv_describe.setText("");
                                  examPaper.ex_correct.add(testQuestions.getId());
                                  Iterator<String> iterator = examPaper.ex_mistake.iterator();
                                  while (iterator.hasNext()){
                                      if (testQuestions.getId().equals(iterator.next())){
                                          iterator.remove();
                                      }
                                  }
                              }else {
                                  Utility.setRadioButtonsStyle(radioButtons.get(i),radioButtons,Color.parseColor("#D83232"));
                                  tv_describe.setText("您选择了:"+radioButtons.get(i).getText()+"\n" +
                                          "正确答案是:"+letters[label]+"  "+testQuestions.getResult().get(0)+"\n"+"解释:"+testQuestions.getAnalysis());
                                  examPaper.ex_mistake.add(testQuestions.getId());
                                  Iterator<String> iterator = examPaper.ex_correct.iterator();
                                  while (iterator.hasNext()){
                                      if (testQuestions.getId().equals(iterator.next())){
                                          iterator.remove();
                                      }
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
        tv_describe =(TextView) view.findViewById(R.id.tv_describe);
    }
}