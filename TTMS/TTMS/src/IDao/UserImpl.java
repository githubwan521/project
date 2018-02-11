package IDao;

import Model.User;

import java.util.List;


/**
 * 用户登录表的增删改查接口
 */
public interface UserImpl {

    // 增
    public boolean insert(User user);

    // 删
    public boolean delete(String employeeNo);

    // 改
    public boolean update(User user);

    public List<User> findUserAll();

    public User findUserByNo(String employeeNo);

    public int getAllCount();

    public int getAllPageCount();

    public int getCurrentPage();

    public List<User> findUserByPage(int currentPage, String employeeNo);


}
