package cn.tsd.exam.base;

public class User {
    private String userId; //学号
    private String name; //名字
    private String uClass; //班级
    private Academy academy; // 学院

    public User(String userId, String name, String uClass, Academy academy) {
        this.userId = userId;
        this.name = name;
        this.uClass = uClass;
        this.academy = academy;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuClass() {
        return uClass;
    }

    public void setuClass(String uClass) {
        this.uClass = uClass;
    }

    public Academy getAcademy() {
        return academy;
    }

    public void setAcademy(Academy academy) {
        this.academy = academy;
    }
}
