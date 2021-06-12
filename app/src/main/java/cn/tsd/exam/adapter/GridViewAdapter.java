package cn.tsd.exam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cn.tsd.exam.ExamPaper;
import cn.tsd.exam.R;
import cn.tsd.exam.base.TestQuestions;

public class GridViewAdapter extends BaseAdapter {
    //题目集合
    private ArrayList<TestQuestions> arrayList;
    private ExamPaper examPaper;
    private Set<String> ex_correct ;  //统计对题id
    private Set<String> ex_mistake  ; //统计错题id
    public GridViewAdapter(ArrayList<TestQuestions> arrayList, ExamPaper examPaper,Set<String> ex_correct,Set<String> ex_mistake ){
        this.ex_correct = ex_correct;
        this.ex_mistake = ex_mistake;
        this.arrayList = arrayList;
        this.examPaper =examPaper;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            //调用视图填充器
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_view_itme, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_number = convertView.findViewById(R.id.tv_number);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //迭代遍历 已经答对了的题目 答题卡样式设置为蓝色
        Iterator<String> iterator = ex_correct.iterator();
        while (iterator.hasNext()){
            if (arrayList.get(position).getId().equals(iterator.next())){
                viewHolder.tv_number.setBackgroundResource(R.drawable.bg_circle_blue);
            }
        }
        //迭代遍历 答错 答题卡样式设置为红色
        Iterator<String> iterator2 = ex_mistake.iterator();
        while (iterator2.hasNext()){
            if (arrayList.get(position).getId().equals(iterator2.next())){
                viewHolder.tv_number.setBackgroundResource(R.drawable.bg_circle_red);
            }
        }
        viewHolder.tv_number.setText(String.valueOf(position+1));
        viewHolder.tv_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                examPaper.dialog.dismiss();
                examPaper.setPage(position);
            }
        });
        return convertView;
    }
    static class ViewHolder {
        private TextView tv_number;

    }
}
