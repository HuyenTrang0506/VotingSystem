package Models;

public class Voters extends User{
	
	private int user_id;
	private int poll_id;
	private String votername;
	
	public String getUsername() {
		return votername;
	}
	public void setUsername(String username) {
		this.votername = username;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPoll_id() {
		return poll_id;
	}
	public void setPoll_id(int poll_id) {
		this.poll_id = poll_id;
	}
	public Voters() {
	}
	public Voters(int user_id, int poll_id,String username) {
		
		this.user_id = user_id;
		this.poll_id = poll_id;
		this.votername=username;
	}
	
}
