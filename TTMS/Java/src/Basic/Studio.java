package Basic;

public class Studio {
	private int id;
	private String name;
	private int row;
	private int col;
	private String introduction;//пео╒
	
	public Studio(){}
	public Studio(String name,int row,int col,String introduction){
		this.name = name;
		this.row = row;
		this.col = col;
		this.introduction = introduction;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
}
