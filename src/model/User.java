package model;


/* 用户实体*/
public class User {
	    private int ID;
	    private String Password;
	    private String UserName;

	    public User(String username, String password) {
			super();
			this.Password = password;
			this.UserName = username;
		}

	    




		public User() {
			super();
		}

		public int getId() {
	        return ID;
	    }

	    public String getPassword() {
	        return Password;
	    }

	    public String getUsername() {
	        return UserName;
	    }

	    public void setId(int id) {
	        this.ID = id;
	    }

	    public void setPassword(String password) {
	        this.Password = password;
	    }

	    public void setUsername(String username) {
	        this.UserName = username;
	    }
	
	
	
	
}
