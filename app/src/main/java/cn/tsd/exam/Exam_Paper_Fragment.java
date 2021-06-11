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


public class Exam_Paper_Fragment extends Fragment {
    public String title;
    private TextView textView;
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,   Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_exam__pape_, container, false);
        init(view);

        RadioGroup group = new RadioGroup(getActivity());
        //封装了View的位置、高、宽等信息
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(100,100,100,300);
        group.setLayoutParams(params);


        RadioButton radioButton = new RadioButton(getActivity());
        radioButton.setText("选项A");

        RadioButton radioButtonB = new RadioButton(getActivity());
        radioButtonB.setText("选项B");

        RadioButton radioButtonC = new RadioButton(getActivity());
        radioButtonC.setText("选项C");

        group.addView(radioButton);
        group.addView(radioButtonB);
        group.addView(radioButtonC);

        linearLayout.addView(group);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)radioButton.getLayoutParams();
        layoutParams.setMargins(100,100,100,100);
        radioButton.setLayoutParams(layoutParams);

        textView.setText(title);
        return view;
    }

    private void init(View view) {
        textView = view.findViewById(R.id.titleName);
        linearLayout = view.findViewById(R.id.box);
    }
}