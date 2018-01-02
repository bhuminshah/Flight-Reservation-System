package model.Admin;

public class Userinfo {

	
	public String getUser_name() {
		return User_name;
	}
	public String getEmail_id() {
		return Email_id;
	}
	public int getPhone() {
		return Phone;
	}
	public String getStreet() {
		return Street;
	}
	public String getUser_Id() {
		return User_Id;
	}
	public String getPassword() {
		return Password;
	}
	private String User_name;
	public void setUser_name(String user_name) {
		User_name = user_name;
	}
	public void setEmail_id(String email_id) {
		Email_id = email_id;
	}
	public void setPhone(int phone) {
		Phone = phone;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public void setPassword(String password) {
		Password = password;
	}
	private String Email_id;
	private int Phone;
	private String Street;
	private String User_Id;
	private String Password;
	
	
	
}
