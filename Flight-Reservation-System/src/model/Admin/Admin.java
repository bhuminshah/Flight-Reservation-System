package model.Admin;

public class Admin {

	private String User_name;
	public String getUser_name() {
		return User_name;
	}
	public void setUser_name(String user_name) {
		User_name = user_name;
	}
	public String getEmail_Id() {
		return Email_Id;
	}
	public void setEmail_Id(String email_Id) {
		Email_Id = email_Id;
	}
	public int getPhone() {
		return Phone;
	}
	public void setPhone(int phone) {
		Phone = phone;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public int getUser_ID() {
		return User_ID;
	}
	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	private String Email_Id;
	private int Phone;
	private String Street;
	private int User_ID;
	private String Password;
	private String Type;
	@Override
	public String toString() {
		return "Admin [User_name=" + User_name + ", Email_Id=" + Email_Id + ", Phone=" + Phone + ", Street=" + Street
				+ ", User_ID=" + User_ID + ", Password=" + Password + ", Type=" + Type + "]";
	}
	
	
}
