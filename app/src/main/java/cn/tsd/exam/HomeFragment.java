package cn.tsd.exam;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.Banner;
import com.youth.banner.adapter.BannerImageAdapter;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

import cn.tsd.exam.adapter.ExamAdapter;
import cn.tsd.exam.base.Academy;
import cn.tsd.exam.base.TestPaper;
import cn.tsd.exam.base.TestQuestions;
import cn.tsd.exam.base.TqType;
import cn.tsd.exam.base.User;
import cn.tsd.exam.utils.Utility;

//主页
public class HomeFragment extends Fragment {
    private ArrayList<TestPaper> papers;
    private ExamAdapter examAdapter;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        SearchView searchView = view.findViewById(R.id.sv_cus);
        listView = view.findViewById(R.id.listview);
        Banner banner = view.findViewById(R.id.banner);

        //模拟图片集合
        List<String> stringList = new ArrayList<>();
        stringList.add("https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/a6efce1b9d16fdfabf36882ab08f8c5495ee7b9f.jpg");
        stringList.add("https://ss3.baidu.com/9fo3dSag_xI4khGko9WTAnF6hhy/zhidao/pic/item/0824ab18972bd40797d8db1179899e510fb3093a.jpg");
        stringList.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2647888545,3751969263&fm=224&gp=0.jpg");

        //轮播图
        banner.setAdapter(new BannerImageAdapter<String>(stringList) {
            @Override
            public void onBindView(BannerImageHolder holder, String data, int position, int size) {
                Glide.with(holder.itemView).load(data).apply(RequestOptions.bitmapTransform(new RoundedCorners(30))).into(holder.imageView);
            }
        });
        /**
         * 轮播图画廊效果
         */
        banner.setIndicator(new CircleIndicator(getActivity()));
        //添加画廊效果
        banner.setBannerGalleryEffect(50, 10);
        banner.setIndicator(new CircleIndicator(getContext()));


        //设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //当点击搜索按钮时触发
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return false;
            }

            //当搜索内容改变时触发
            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("MMM", "内容改变" + newText);

                return false;
            }
        });

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
                TestPaper testPaper = new TestPaper("123" + i, "123" + i + 1, "123", testQuestions,
                        Academy.ELECTRONIC_INFORMATION, "Java测试题目", "123",
                        new User("123", "唐好帅", "移动2", Academy.ADMINISTERED)); //试卷
                // 模拟10张试卷
                papers.add(testPaper);
            }
        }

        examAdapter = new ExamAdapter(papers, getActivity());
        listView.setAdapter(examAdapter);
        //ListView与ScrollView 解决冲突
        Utility.setListViewHeightBasedOnChildren(listView);
        examAdapter.notifyDataSetChanged();


        return view;
    }
}