package cn.tsd.exam.base;

//学院枚举
public enum Academy {
    ELECTRONIC_INFORMATION("电子信息工程学院", 1), OCEAN("海洋学院", 2),
    SEA_TRANSPORTATION ("海运学院", 3),
    CULTURAL("人文学院", 4),
    ADMINISTERED("经管学院", 5),
    CERAMICS("陶瓷学院", 6),
    EDUCATION("教育学院", 7),
    MACHINERY_AND_SHIP("机船学院", 8),
    SCIENCE("理学院", 9),
    FOREIGN_LANGUAGES("外语学院", 10);
    private String name;
    private int index;
    private Academy(String name , int index){
        this.index = index;
        this.name = name;
    }
    // 普通方法
    public static String getName(int index) {
        for (Academy c : Academy.values()) {
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
