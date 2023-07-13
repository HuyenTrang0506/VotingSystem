package Models;

import java.util.Date;

public class Vote {
	private int id; 
	private int poll_id;
	private int user_id;
	private int choice_id;
	private Date createdAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPoll_id() {
		return poll_id;
	}
	public void setPoll_id(int poll_id) {
		this.poll_id = poll_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getChoice_id() {
		return choice_id;
	}
	public void setChoice_id(int choice_id) {
		this.choice_id = choice_id;
	}
	public Date getCreated_at() {
		return createdAt;
	}
	public void setCreated_at(Date created_at) {
		this.createdAt = created_at;
	}
	public Vote() {
		
	}
	public Vote( int poll_id, int user_id, int choice_id, Date created_at) {
		
	
		this.poll_id = poll_id;
		this.user_id = user_id;
		this.choice_id = choice_id;
		this.createdAt = created_at;
	}
	
	public Vote( int id,int poll_id, int user_id, int choice_id, Date created_at) {
		this.id=id;
		
		this.poll_id = poll_id;
		this.user_id = user_id;
		this.choice_id = choice_id;
		this.createdAt = created_at;
	}
	
}
