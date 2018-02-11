package Basic;

//订单类
public class Sale {
	private int sale_id;
	private int user_id;
	private String sale_payment;
	private String sale_refund;
	private int sale_status;//销售订单状态0销售1退
	
	public Sale(){}
	public Sale(int user_id,String sale_payment,String sale_refund,int sale_status){
		this.user_id = user_id;
		this.sale_payment = sale_payment;
		this.sale_refund = sale_refund;
		this.sale_status = sale_status;
	}
	public int getSale_id() {
		return sale_id;
	}
	public void setSale_id(int sale_id) {
		this.sale_id = sale_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getSale_payment() {
		return sale_payment;
	}
	public void setSale_payment(String sale_payment) {
		this.sale_payment = sale_payment;
	}
	public String getSale_refund() {
		return sale_refund;
	}
	public void setSale_refund(String sale_refund) {
		this.sale_refund = sale_refund;
	}
	public int getSale_status() {
		return sale_status;
	}
	public void setSale_status(int sale_status) {
		this.sale_status = sale_status;
	}
	

}
