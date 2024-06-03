package model;

public class Student {
    private int id;
    private String password;
    private String username;
    private int borrow;
    
    public Student() {
        super();
    }

    public Student(int id, String password) {
        super();
        this.id = id;
        this.password = password;
    }

    public Student(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




	public int getBorrow() {
		// TODO 自动生成的方法存根
		return this.borrow;
	}

	public void setBorrow(int i) {
		// TODO 自动生成的方法存根
		this.borrow=i;
	}
}
