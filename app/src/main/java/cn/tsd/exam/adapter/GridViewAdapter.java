package cn.tsd.exam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cn.tsd.exam.ExamPaper;
import cn.tsd.exam.R;

public class GridViewAdapter extends BaseAdapter {
    private ArrayList<String> arrayList;
    private ExamPaper examPaper;
    public GridViewAdapter(ArrayList<String> arrayList, ExamPaper examPaper){
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
        viewHolder.tv_number.setText(arrayList.get(position));
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
