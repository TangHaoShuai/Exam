package cn.tsd.exam.utils;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.List;

import cn.tsd.exam.R;

public class Utility {
    //ListView与ScrollView 解决冲突
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

    //设置样式
    public static void setRadioButtonsStyle(RadioButton radioButton, List<RadioButton> list ,int color  ) {
        for (RadioButton t : list) {
            if (t.getId() == radioButton.getId()) {
                t.setBackgroundColor(color);
            } else {
                t.setBackgroundColor(Color.WHITE);
            }
        }
    }

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}


