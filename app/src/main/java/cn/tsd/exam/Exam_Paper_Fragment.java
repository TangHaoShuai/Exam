package cn.tsd.exam;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import cn.tsd.exam.base.TestQuestions;
import cn.tsd.exam.base.TqType;
import cn.tsd.exam.utils.Utility;

//答题界面
public class Exam_Paper_Fragment extends Fragment {
    private TextView count;
    private TextView type;
    public String title, ex_count, ex_type;
    private TextView textView, tv_describe;
    private LinearLayout linearLayout;
    public TestQuestions testQuestions; //试题
    public static final String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int label = 0; //用来记录单选题正确答案所在的选项
    private String resultStr = "";  //多选题正确答案字符串
    private   ExamPaper examPaper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam__pape_, container, false);
        examPaper = (ExamPaper) getActivity(); //实例化 考试页面 方便对正确和错误题目数量的操作
        init(view);
        if (testQuestions != null) {
            //如果是单选题目
            if (testQuestions.getTqType().equals(TqType.SINGLE_CHOICE)) {
                singleChoice();
            } else if (testQuestions.getTqType().equals(TqType.MULTIPLE_CHOICE)) {  //如果是多选题
                multipleChoice();
            } else if (testQuestions.getTqType().equals(TqType.COMPLETION)){//如果是填空题
                completionQuestions();
            }else  if (testQuestions.getTqType().equals(TqType.TRUE_OR_FALSE_QUESTIONS)){ //判断题
                tfQuestions();
            }
        }
        textView.setText(title);
        count.setText(ex_count);
        type.setText("[" + ex_type + "]");
        return view;
    }
    //判断题
    private void tfQuestions(){
        RadioGroup group = new RadioGroup(getActivity());
        //封装了View的位置、高、宽等信息
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 100);
        group.setLayoutParams(params);
        List<RadioButton> radioButtons = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            RadioButton radioButton = new RadioButton(getActivity());
            if (i == 0 ){
                radioButton.setText("正确");
            }else {
                radioButton.setText("错误");
            }
            radioButton.setId(i);
            radioButtons.add(radioButton);
            group.addView(radioButton);
        }
        linearLayout.addView(group);
        for (RadioButton radioButton : radioButtons) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) group.getLayoutParams();
            layoutParams.setMargins(20, 0, 0, 20);
            radioButton.setLayoutParams(layoutParams);
        }

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int tpgA = 0;
                int tpgB = 0;
                if (!testQuestions.getResult().get(0).trim().equals("正确")){
                    //如果正确答案是错误 tpg为1 正确为0
                    tpgA = 1;
                }
                for (RadioButton a:radioButtons){
                    a.setBackgroundColor(Color.WHITE);
                }
                // 如果用户选择了正确的选项 答案也是正确
                if (checkedId == 0 && tpgA == 0){
                    radioButtons.get(checkedId).setBackgroundColor(Color.parseColor("#00FF00"));
                    examPaper.ex_correct.add(testQuestions.getId()); //正确把题目id添加进ex_correct
                    examPaper.ex_mistake.remove(testQuestions.getId()); //把错题的集合里面的删除
                }else {
                    tpgB = 1;
                    radioButtons.get(checkedId).setBackgroundColor(Color.RED);
                    examPaper.ex_mistake.add(testQuestions.getId()); //错误把题目id添加进ex_correct
                    examPaper.ex_correct.remove(testQuestions.getId());
                }
                tv_describe.setText("您的答案是:"+(tpgB==0?"正确":"错误")+"\n正确答案:"+testQuestions.getResult().get(0)+"\n解释:"+testQuestions.getAnalysis());
                examPaper.setStatistics(); //刷新对错题目数
            }
        });

    }

    //填空
    private void completionQuestions(){
        ArrayList<EditText> editTexts = new ArrayList<>();
        for (int i = 0; i < testQuestions.getResult().size(); i++) {
            EditText editText = new EditText(getActivity());
            linearLayout.addView(editText);
            editText.setHint("请输入答案");
            //设置样式
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) editText.getLayoutParams();
            layoutParams.setMargins(100, 0, 100, 20);
            editText.setLayoutParams(layoutParams);
            editTexts.add(editText);
        }
        Button button = new Button(getActivity());
        //封装了View的位置、高、宽等信息
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        button.setGravity(Gravity.CENTER);
        button.setText("确认");
        button.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button.getLayoutParams();
        layoutParams.setMargins(100, 0, 100, 20);
        button.setLayoutParams(layoutParams);
        linearLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.requestFocus();
                int tag = 0 ;
                String answer = "";
                String rightAnswers = "";
                for (int i = 0; i < editTexts.size(); i++) {
                    String value = editTexts.get(i).getText().toString().trim();
                    String value2 = testQuestions.getResult().get(i).trim();
                    answer+=value+"  ";
                    rightAnswers+=value2+"  ";
                    if (value.equals(value2)){
                        editTexts.get(i).setBackgroundColor(Color.parseColor("#00FF00"));
                    }else{
                        tag++;
                        editTexts.get(i).setBackgroundColor(Color.RED);
                    }
                }
                if (tag == 0 ){
                    examPaper.ex_correct.add(testQuestions.getId()); //正确把题目id添加进ex_correct
                    examPaper.ex_mistake.remove(testQuestions.getId()); //把错题的集合里面的删除
                    tv_describe.setText("");
                }else {
                    examPaper.ex_mistake.add(testQuestions.getId()); //错误把题目id添加进ex_correct
                    examPaper.ex_correct.remove(testQuestions.getId());
                }
                tv_describe.setText("您的答案是:"+answer+"\n正确答案:"+rightAnswers+"\n解释:"+testQuestions.getAnalysis());
                examPaper.setStatistics(); //刷新对错题目数
            }
        });

    }
    //多项选择
    private void multipleChoice() {
        ArrayList<CheckBox> checkBoxes = new ArrayList<>(); // checkBoxes集合
        TreeSet <CheckBox> selectedCheckBox = new TreeSet<>(new Comparator<CheckBox>() {
            @Override
            public int compare(CheckBox o1, CheckBox o2) {
                //实现排序
                String c1 = o1.getText().toString().trim();
                String c2 = o2.getText().toString().trim();
               return c1.compareTo(c2);

            }
        });
        //创建CheckBox的集合
        for (int i = 0; i < testQuestions.getOptions().size(); i++) {
            CheckBox checkBox = new CheckBox(getActivity());
            String tq = testQuestions.getOptions().get(i); //选项
            for (String s : testQuestions.getResult()){
                if (tq.equals(s)){
                    resultStr+=letters[i]+":" +tq+" ";
                }
            }
            checkBox.setText(letters[i] + "  " + tq);
            checkBox.setId(i);
            linearLayout.addView(checkBox);
            //设置样式
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) checkBox.getLayoutParams();
            layoutParams.setMargins(20, 0, 0, 20);
            checkBox.setLayoutParams(layoutParams);
            checkBoxes.add(checkBox);
        }

        //选中的选项集合 selectedCheckBox
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        selectedCheckBox.add(checkBox);
                    } else {
                        selectedCheckBox.remove(checkBox);
                    }
                }
            });
        }

        Button button = new Button(getActivity());
        //封装了View的位置、高、宽等信息
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        button.setGravity(Gravity.CENTER);
        button.setText("确认");
        button.setTextColor(Color.BLACK);
        linearLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkedStr = ""; //多选题选中的选项字符串
                if (selectedCheckBox.size() < 2) {
                    Toast.makeText(getActivity(), "最少要选俩个选项", Toast.LENGTH_SHORT).show();
                } else {
                    //背景改成白色
                    for (CheckBox s : checkBoxes) {
                        s.setBackgroundColor(Color.WHITE);
                    }
                    int label_wrong = 0; //记录他错误了多少个选项
                    for (CheckBox s : selectedCheckBox){
                        checkedStr+= s.getText().toString().trim()+"  ";
                        int label = 0;
                        for (String c : testQuestions.getResult()) {
                            String xz = s.getText().toString().substring(1).trim(); //获取选项的值
                            if (xz.equals(c)) { //与正确答案对比
                                label++;
                            }
                        }
                        if (label > 0) {
                            s.setBackgroundColor(Color.parseColor("#00FF00"));

                        } else {
                            s.setBackgroundColor(Color.parseColor("#D83232"));
                            label_wrong++;
                        }
                    }
                    //如果他选中的数量和正确答案数量一样
                    if (selectedCheckBox.size() == testQuestions.getResult().size() ){
                        //在判断他有没有选错
                        if (label_wrong == 0 ){
                            examPaper.ex_correct.add(testQuestions.getId()); //正确把题目id添加进ex_correct
                            examPaper.ex_mistake.remove(testQuestions.getId()); //把错题的集合里面的删除
                            tv_describe.setText("");
                        }else {
                            examPaper.ex_mistake.add(testQuestions.getId()); //错误把题目id添加进ex_correct
                            examPaper.ex_correct.remove(testQuestions.getId());
                            tv_describe.setText("您选择了:" +checkedStr+ "\n" +
                                    "正确答案是:" + resultStr + "\n解释:"+testQuestions.getAnalysis());
                        }
                    }else{
                        examPaper.ex_mistake.add(testQuestions.getId()); //错误把题目id添加进ex_correct
                        examPaper.ex_correct.remove(testQuestions.getId());
                        tv_describe.setText("您选择了:" +checkedStr+ "\n" +
                                "正确答案是:" + resultStr + "\n解释:"+testQuestions.getAnalysis());
                    }
                    examPaper.setStatistics(); //刷新对错题目数
                }
            }
        });
    }
    //单选
    private void singleChoice() {
        RadioGroup group = new RadioGroup(getActivity());
        //封装了View的位置、高、宽等信息
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 100);
        group.setLayoutParams(params);
        List<RadioButton> radioButtons = new ArrayList<>();
        for (int i = 0; i < testQuestions.getOptions().size(); i++) {
            String tq = testQuestions.getOptions().get(i); //选项
            if (tq.equals(testQuestions.getResult().get(0))) { //当前选项和正确答案一样 就标记一下他
                label = i;
            }
            RadioButton radioButton = new RadioButton(getActivity());
            radioButton.setText(letters[i] + "  " + tq);
            radioButton.setId(i);
            radioButtons.add(radioButton);
            group.addView(radioButton);
        }
        linearLayout.addView(group);
        for (RadioButton radioButton : radioButtons) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) group.getLayoutParams();
            layoutParams.setMargins(20, 0, 0, 20);
            radioButton.setLayoutParams(layoutParams);
        }


        //点击选项监听
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < testQuestions.getOptions().size(); i++) {
                    if (radioButtons.get(i).getId() == checkedId) {
                        //如果选中的答案和正确答案一样
                        if (radioButtons.get(i).getText().toString().substring(1).trim().equals(testQuestions.getResult().get(0).toString().trim())) {
                            Utility.setRadioButtonsStyle(radioButtons.get(i), radioButtons, Color.parseColor("#00FF00"));
                            tv_describe.setText("");
                            examPaper.ex_correct.add(testQuestions.getId());
                            Iterator<String> iterator = examPaper.ex_mistake.iterator();
                            while (iterator.hasNext()) {
                                if (testQuestions.getId().equals(iterator.next())) {
                                    iterator.remove();
                                }
                            }
                        } else {
                            Utility.setRadioButtonsStyle(radioButtons.get(i), radioButtons, Color.parseColor("#D83232"));
                            tv_describe.setText("您选择了:" + radioButtons.get(i).getText() + "\n" +
                                    "正确答案是:" + letters[label] + "  " + testQuestions.getResult().get(0) + "\n" + "解释:" + testQuestions.getAnalysis());
                            examPaper.ex_mistake.add(testQuestions.getId());
                            Iterator<String> iterator = examPaper.ex_correct.iterator();
                            while (iterator.hasNext()) {
                                if (testQuestions.getId().equals(iterator.next())) {
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

    private void init(View view) {
        textView = view.findViewById(R.id.titleName);
        linearLayout = view.findViewById(R.id.box);
        count = (TextView) view.findViewById(R.id.count);
        type = (TextView) view.findViewById(R.id.type);
        tv_describe = (TextView) view.findViewById(R.id.tv_describe);
    }
}