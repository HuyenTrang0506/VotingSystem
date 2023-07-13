package Models;
public class Choice {
    private int id;
    private int poll_id;
    private String content;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Choice() {
		
	}
	public Choice(int id, int poll_id, String content) {
		
		this.id = id;
		this.poll_id = poll_id;
		this.content = content;
	}
    public Choice( int poll_id, String content) {
		
		
		this.poll_id = poll_id;
		this.content = content;
	}

   
   
}