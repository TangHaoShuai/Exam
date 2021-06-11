package cn.tsd.exam;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

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
    private List<TextView> list;
    private FloatingActionButton fab;
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

        //设置默认样式
        selfBuiltTestPaper.setBackgroundColor(Color.BLUE);
        selfBuiltTestPaper.setTextColor(Color.WHITE);
        list = new ArrayList<>();
        list.add(selfBuiltTestPaper);
        list.add(collectionPapers);
        //设置点击样式
        selfBuiltTestPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.setStyle(selfBuiltTestPaper, list);
            }
        });
        collectionPapers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.setStyle(collectionPapers, list);
            }
        });

        //小圆圈
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"1`12`1",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void init(View view) {
        selfBuiltTestPaper = (TextView) view.findViewById(R.id.self_built_test_paper);
        collectionPapers = (TextView) view.findViewById(R.id.collection_papers);
        listView = view.findViewById(R.id.listview);
        fab = view.findViewById(R.id.fab);

    }
}