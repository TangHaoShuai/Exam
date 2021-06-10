package cn.tsd.exam;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import cn.tsd.exam.adapter.ExamAdapter;
import cn.tsd.exam.adapter.ExamTwoAdapter;
import cn.tsd.exam.base.Academy;
import cn.tsd.exam.base.TestPaper;
import cn.tsd.exam.base.TestQuestions;
import cn.tsd.exam.base.User;
import cn.tsd.exam.utils.Utility;

//我的题库
public class MyQuestionBankFragment extends Fragment {
    private ArrayList<TestPaper> papers;
    private ExamTwoAdapter adapter;
    private ListView listView;
    private TextView selfBuiltTestPaper;
    private TextView collectionPapers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myquestion, container, false);
        init(view);

        //模拟10条试题
        ArrayList<TestQuestions> testQuestions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestQuestions testQuestions1 = new TestQuestions("123", "123", "testTitle", "un1", "un2", "un3", "un4", 2);
            testQuestions.add(testQuestions1);
        }

        if (papers == null) {
            papers = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                // 模拟10张试卷
                TestPaper testPaper = new TestPaper("123" + i, "123" + i + 1, "123", testQuestions,
                        Academy.ELECTRONIC_INFORMATION, "Java测试题目", "123",
                        new User("123", "唐好帅", "移动2", Academy.ADMINISTERED)); //试卷
                papers.add(testPaper);
            }
        }

        adapter = new ExamTwoAdapter(papers, getActivity()); //初始化适配器
        listView.setAdapter(adapter); //绑定适配器
        adapter.notifyDataSetChanged(); // 刷新数据

        //设置点击样式
        selfBuiltTestPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selfBuiltTestPaper.setTextColor(Color.WHITE);
                selfBuiltTestPaper.setBackgroundColor(Color.parseColor("#FFBB86FC"));
                collectionPapers.setTextColor(Color.BLACK);
                collectionPapers.setBackgroundColor(Color.WHITE);
            }
        });
        collectionPapers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectionPapers.setTextColor(Color.WHITE);
                collectionPapers.setBackgroundColor(Color.parseColor("#FFBB86FC"));
                selfBuiltTestPaper.setTextColor(Color.BLACK);
                selfBuiltTestPaper.setBackgroundColor(Color.WHITE);
            }
        });




        return view;
    }

    private void init(View view) {
        selfBuiltTestPaper = (TextView) view.findViewById(R.id.self_built_test_paper);
        collectionPapers = (TextView) view.findViewById(R.id.collection_papers);
        listView = view.findViewById(R.id.listview);

    }
}