package Basic;

//ÑÝ³ö¼Æ»®
public class Schedule {
	private int schedule_id;
	private int studio_id;
	private int play_id;
	private String schedule_time;
	private String schedule_price;
	private int schedule_status;
	
	public Schedule(){}
	public Schedule(int schedule_id,int studio_id,int play_id,String schedule_time,String schedule_price){
		this.play_id = play_id;
		this.schedule_id = schedule_id;
		this.studio_id = studio_id;
		this.play_id = play_id;
		this.schedule_time = schedule_time;
		this.schedule_price = schedule_price;
	}


	public int getSchedule_id() {
		return schedule_id;
	}
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}
	public int getStudio_id() {
		return studio_id;
	}
	public void setStudio_id(int studio_id) {
		this.studio_id = studio_id;
	}
	public int getPlay_id() {
		return play_id;
	}
	public void setPlay_id(int play_id) {
		this.play_id = play_id;
	}
	public String getSchedule_time() {
		return schedule_time;
	}
	public void setSchedule_time(String schedule_time) {
		this.schedule_time = schedule_time;
	}
	public String getSchedule_price() {
		return schedule_price;
	}
	public void setSchedule_price(String schedule_price) {
		this.schedule_price = schedule_price;
	}
	public int getSchedule_status() {
		return schedule_status;
	}
	public void setSchedule_status(int schedule_status) {
		this.schedule_status = schedule_status;
	}

	
}
