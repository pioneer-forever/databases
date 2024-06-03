package model;

public class Area {
    private int id;
    private String category;
    private String location;

    public Area() {
        super();
    }

    public Area(String category, String location) {
        super();
        this.category = category;
        this.location = location;
    }

    public Area(int id, String category, String location) {
        super();
        this.id = id;
        this.category = category;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return this.category; // 返回在 JComboBox 中显示的内容
    }
}
