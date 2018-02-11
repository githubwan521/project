package Dao;

import IDao.*;


/*
* DAO工厂模式 ----->定义一个用于创建对象的接口
*/
public class FactoryDao {
    //员工对象
    public static EmployeeImpl creatEmployeeDAO() {
        return new EmployeeDao();
    }

    //登陆用户对象
    public static UserImpl creatUserDAO() {
        return new UserDao();
    }

    public static StudioImpl creatStudioDAO() {
        return new StudioDAO();
    }

    public static SeatImpl creatSeatDAO() {
        return new SeatDAO();
    }

}
