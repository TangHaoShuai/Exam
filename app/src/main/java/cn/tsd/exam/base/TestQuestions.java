package cn.tsd.exam.base;

//试题
public class TestQuestions {
    private String id; //试题id
    private String testPaperID; //试卷ID
    private String titleName; // 题目名称
    private String number1; //选项1
    private String number2; //选项2
    private String number3; //选项3
    private String number4; //选项4
    private int result; // 答案

    public TestQuestions(String id, String testPaperID, String titleName, String number1, String number2, String number3, String number4, int result) {
        this.id = id;
        this.testPaperID = testPaperID;
        this.titleName = titleName;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.result = result;
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

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getNumber3() {
        return number3;
    }

    public void setNumber3(String number3) {
        this.number3 = number3;
    }

    public String getNumber4() {
        return number4;
    }

    public void setNumber4(String number4) {
        this.number4 = number4;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
