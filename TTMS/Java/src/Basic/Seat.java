package Basic;

public class Seat {
	private int id;
	private int studio_id;//座位所在的演出厅id
	private int row;//座位所在行数
	private int col;//座位所在列数
	private int state;//座位状态(0正常 -1坏掉)
	
	public Seat () {}
	public Seat (int studio_id,int row,int col,int state) {
		this.setId(studio_id);
		this.setCol(col);
		this.setRow(row);
		this.setState(state);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getStudio_id() {
		return studio_id;
	}
	public void setStudio_id(int studio_id) {
		this.studio_id = studio_id;
	}
	
	
	
}
