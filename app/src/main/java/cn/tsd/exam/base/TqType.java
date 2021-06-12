package cn.tsd.exam.base;

//题目类型
public enum TqType {
    SINGLE_CHOICE ("单选题", 0),MULTIPLE_CHOICE ("多选题", 1),TRUE_OR_FALSE_QUESTIONS ("判断题", 2),
    COMPLETION ("填空题", 3),SHORT_ANSWER_QUESTION  ("简答题", 4);
    private String name;
    private int index;

    private TqType(String name ,int index ){
        this.name = name;
        this.index = index;
    }
    // 普通方法
    public static String getName(int index) {
        for (TqType c : TqType.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
