package Basic;

public class Play {
	private int     play_id;           
	private int     play_type;          
	private int     play_language;       
	private String  play_name;           
	private String  play_introduction;    
	private String  play_image;          
	private int     play_duration;      
	private int     play_status;
	
	public Play(){}
	public Play(int play_type,int play_language,String play_name,String play_introduction,int play_duration){
		this.play_type = play_type;
		this.play_language = play_language;
		this.play_name = play_name;
		this.play_introduction = play_introduction;
//		this.play_image = play_image;
		this.play_duration = play_duration;
	}

	public int getPlay_id() {
		return play_id;
	}
	public void setPlay_id(int play_id) {
		this.play_id = play_id;
	}
	public int getPlay_type() {
		return play_type;
	}
	public void setPlay_type(int play_type) {
		this.play_type = play_type;
	}
	public int getPlay_language() {
		return play_language;
	}
	public void setPlay_language(int play_language) {
		this.play_language = play_language;
	}
	public String getPlay_name() {
		return play_name;
	}
	public void setPlay_name(String play_name) {
		this.play_name = play_name;
	}
	public String getPlay_introduction() {
		return play_introduction;
	}
	public void setPlay_introduction(String play_introduction) {
		this.play_introduction = play_introduction;
	}
	public String getPlay_image() {
		return play_image;
	}
	public void setPlay_image(String play_image) {
		this.play_image = play_image;
	}
	public int getPlay_duration() {
		return play_duration;
	}
	public void setPlay_duration(int play_duration) {
		this.play_duration = play_duration;
	}
	public int getPlay_status() {
		return play_status;
	}
	public void setPlay_status(int play_status) {
		this.play_status = play_status;
	}         
}
