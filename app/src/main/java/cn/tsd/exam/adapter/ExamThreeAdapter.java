package cn.tsd.exam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import cn.tsd.exam.R;
import cn.tsd.exam.base.TestPaper;

public class ExamThreeAdapter extends BaseAdapter {
    private ArrayList<TestPaper> testPapers;
    private Context context;

    public ExamThreeAdapter(ArrayList<TestPaper> testPapers, Context context){
        this.testPapers = testPapers;
        this.context = context;
    }
    @Override
    public int getCount() {
        return testPapers.size();
    }

    @Override
    public Object getItem(int position) {
        return testPapers.get(position);
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
            convertView = LayoutInflater.from(this.context).inflate(R.layout.exam_listview_three__item, null);
            viewHolder = new ViewHolder();
            viewHolder.examName = convertView.findViewById(R.id.examName);
            viewHolder.count = convertView.findViewById(R.id.count);
            viewHolder.academy = convertView.findViewById(R.id.academy);
            viewHolder.author = convertView.findViewById(R.id.author);
            viewHolder.collect = convertView.findViewById(R.id.collect);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.examName.setText(testPapers.get(position).getName());
        viewHolder.count.setText("共"+testPapers.get(position).getTestQuestions().size()+"题" );
        viewHolder.academy.setText(testPapers.get(position).getAcademy().getName());
        viewHolder.author.setText(testPapers.get(position).getUser().getName());

        viewHolder.collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击了"+testPapers.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
    static class ViewHolder {
        private TextView examName; //试卷名字
        private TextView count;  //试卷题目数
        private TextView academy; //试卷所属学院
        private TextView author; //试卷所属作者
        private Button collect; //收藏
    }
}
