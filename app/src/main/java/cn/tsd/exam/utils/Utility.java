package cn.tsd.exam.utils;


import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Utility {
        public static void setListViewHeightBasedOnChildren(ListView listView) {
            ListAdapter listAdapter = listView.getAdapter();
            if (listAdapter == null) {
                return;
            }
            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
            listView.setLayoutParams(params);
        }
    //设置样式
    public static void setStyle(TextView textView, List<TextView> list) {
        for (TextView t : list) {
            if (t.getId() == textView.getId()) {
                t.setBackgroundColor(Color.BLUE);
                t.setTextColor(Color.WHITE);
            } else {
                t.setBackgroundColor(Color.WHITE);
                t.setTextColor(Color.BLACK);
            }
        }
    }
    }


