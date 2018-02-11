package Model;

import java.io.Serializable;

/**
 * 登陆用户模型
 */
public class User implements Serializable {
    private String user_no;//用户账号
    private String user_pass;//密码
    private String head_path;//密码
    private int user_type;//类型   1管理员  2普通员工
    private int user_state;//登陆状态

    public  int getUser_state() {
        return user_state;
    }

    public void setUser_state(int user_state) {
        this.user_state = user_state;
    }

    public String getUser_no() {
        return user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }
    public String getHead_path() {
        return head_path;
    }

    public void setHead_path(String head_path) {
        this.head_path = head_path;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }


}
