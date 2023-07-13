package Models;

import java.util.Date;

public class Poll {
	private int id;
	private String title;
	private String description;
	private Date startTime;
	private Date endTime;
	private int maxChoices;
	private int maxVotes;
	
	public int getMaxVotes() {
		return maxVotes;
	}
	public void setMaxVotes(int maxVotes) {
		this.maxVotes = maxVotes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getMaxChoices() {
		return maxChoices;
	}
	public void setMaxChoices(int maxChoices) {
		this.maxChoices = maxChoices;
	}
	public Poll() {
		
	}
	public Poll(int id, String title, String description, Date startTime, Date endTime, int maxChoices, int maxVotes) {
	
		this.id = id;
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.maxChoices = maxChoices;
		this.maxVotes = maxVotes;
	}
	
	
}
