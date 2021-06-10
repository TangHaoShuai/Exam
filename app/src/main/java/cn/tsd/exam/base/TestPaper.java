package cn.tsd.exam.base;

//试卷

import java.util.ArrayList;

public class TestPaper {
    private String id; //id
    private String name; //试卷名字
    private String testQuestionsID; //试题ID
    private ArrayList<TestQuestions> testQuestions; // 对应的试题
    private Academy academy; //试卷所属学院
    private String describe; // 描述
    private String authorId; // 试卷作者ID
    private User user; //创建的试卷的用户

    public  TestPaper(){

    }


    public TestPaper(String id, String name, String testQuestionsID, ArrayList<TestQuestions> testQuestions, Academy academy, String describe, String authorId, User user) {
        this.id = id;
        this.name = name;
        this.testQuestionsID = testQuestionsID;
        this.testQuestions = testQuestions;
        this.academy = academy;
        this.describe = describe;
        this.authorId = authorId;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTestQuestionsID() {
        return testQuestionsID;
    }

    public void setTestQuestionsID(String testQuestionsID) {
        this.testQuestionsID = testQuestionsID;
    }

    public ArrayList<TestQuestions> getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(ArrayList<TestQuestions> testQuestions) {
        this.testQuestions = testQuestions;
    }

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
