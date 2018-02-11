package Basic;

public class User {
	private int id;
	private String identity;
	private String name;
	private String pass;
	private String tel;
	private String addr;
	private String email;
	private int status;
	
	public User(){}
	public User(String identity,String name,String pass,String tel){
		this.setIdentity(identity);
		this.setName(name);
		this.setPass(pass);
		this.tel = tel;

	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
