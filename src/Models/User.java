package Models;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private int decentralize;//admin la 1, normal la 2
	private String isActive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getDecentralize() {
		return decentralize;
	}
	public void setDecentralize(int decentralize) {
		this.decentralize = decentralize;
	}
	
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public User(int id, String username, String password, String email, String phone,int decentralize,String isActive) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.decentralize= decentralize;
		this.isActive = isActive;
	}
	
	public User() {
		
	}
	
}
