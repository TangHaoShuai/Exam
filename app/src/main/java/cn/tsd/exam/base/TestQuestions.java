package cn.tsd.exam.base;

import java.util.List;

//试题
public class TestQuestions {
    private String id; //试题id
    private String testPaperID; //试卷ID
    private String titleName; // 题目名称
    private TqType tqType; // 试题类型
    private String analysis; // 题目解析
    private List<String> options; // 题目选项
    private List<String> result; // 答案

    public TestQuestions(String id, String testPaperID, String titleName, TqType tqType, String analysis, List<String> options, List<String> result) {
        this.id = id;
        this.testPaperID = testPaperID;
        this.titleName = titleName;
        this.tqType = tqType;
        this.analysis = analysis;
        this.options = options;
        this.result = result;
    }

    public TestQuestions() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestPaperID() {
        return testPaperID;
    }

    public void setTestPaperID(String testPaperID) {
        this.testPaperID = testPaperID;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public TqType getTqType() {
        return tqType;
    }

    public void setTqType(TqType tqType) {
        this.tqType = tqType;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}
