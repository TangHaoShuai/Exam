package cn.tsd.exam;

import android.graphics.Color;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import cn.tsd.exam.adapter.ExamThreeAdapter;
import cn.tsd.exam.adapter.ExamTwoAdapter;
import cn.tsd.exam.base.Academy;
import cn.tsd.exam.base.TestPaper;
import cn.tsd.exam.base.TestQuestions;
import cn.tsd.exam.base.TqType;
import cn.tsd.exam.base.User;
import cn.tsd.exam.utils.Utility;

/**
 * 资料库
 */
public class MaterialFragment extends Fragment implements View.OnClickListener {
    private TextView textFree;
    private TextView textPay;
    private TextView textOfficial;
    private List<TextView> list;
    private ListView listView;
    private ExamThreeAdapter adapter;
    private ArrayList<TestPaper> papers; //试卷
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_material, container, false);
        init(view);
        list = new ArrayList<>();
        list.add(textFree);
        list.add(textPay);
        list.add(textOfficial);
        //设置默认样式
        textFree.setBackgroundColor(Color.BLUE);
        textFree.setTextColor(Color.WHITE);

        SearchView searchView = view.findViewById(R.id.sv_cus);
        //设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //当点击搜索按钮时触发
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("MMM", "内容改变" + query);
                return false;
            }
            //当搜索内容改变时触发
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("MMM", "内容改变" + newText);
                return false;
            }
        });

        //模拟10条试题
        ArrayList<TestQuestions> testQuestions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            //      模拟10条试题
            List<String> options= new ArrayList<String>();
            options.add("选项A");options.add("选项B");
            TestQuestions testQuestions1 = new TestQuestions("123", "123", "testTitle", TqType.SINGLE_CHOICE, "un2", options, options);
            testQuestions.add(testQuestions1);
        }

        if (papers == null) {
            papers = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                // 模拟10张试卷
                TestPaper testPaper = new TestPaper("123" + i, "123" + i + 1, "123", testQuestions,
                        Academy.ELECTRONIC_INFORMATION, "Java测试题目", "123",
                        new User("123", "唐好帅", "移动2", Academy.ADMINISTERED,"15077588679","2312313")); //试卷
                papers.add(testPaper);
            }
        }
        adapter = new ExamThreeAdapter(papers, getActivity()); //初始化适配器
        listView.setAdapter(adapter); //绑定适配器
        adapter.notifyDataSetChanged(); // 刷新数据


        return view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_free:
                Utility.setStyle(textFree,list);
                break;
            case R.id.text_pay:
                Utility.setStyle(textPay,list);
                break;
            case R.id.text_official:
                Utility.setStyle(textOfficial,list);
                break;
        }
    }
    private void init(View view) {
        textFree = (TextView) view.findViewById(R.id.text_free);
        textPay = (TextView)  view.findViewById(R.id.text_pay);
        textOfficial = (TextView) view.findViewById(R.id.text_official);
        textFree.setOnClickListener(this);
        textPay.setOnClickListener(this);
        textOfficial.setOnClickListener(this);
        listView = view.findViewById(R.id.listview);
    }




}