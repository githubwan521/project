package UI;

import javax.swing.JLabel;

public class myJLabel extends JLabel{
	private Boolean flag=false;
	
	public myJLabel(String string, int center) {
		super(string,center);
	}
	public boolean getFlag(){
		return flag;
	}
	public void setFlag(Boolean flag){
		this.flag=flag;
	}
}
