package Service;

import Dao.FactoryDao;
import IDao.UserImpl;
import Model.User;

import java.util.List;

public class UserService {
    private UserImpl userDAO = FactoryDao.creatUserDAO();

    public boolean add(User user) {
        return userDAO.insert(user);
    }

    public boolean delete(String employeeNo) {
        return userDAO.delete(employeeNo);
    }

    public boolean modify(User user) {
        return userDAO.update(user);
    }

    public List<User> findUserAll() {
        return userDAO.findUserAll();
    }

    public User findUserByNo(String employeeNo) {
        return userDAO.findUserByNo(employeeNo);
    }

    public int getAllCount() {
        return userDAO.getAllCount();
    }

    public int getAllPageCount() {
        return userDAO.getAllPageCount();
    }

    public int getCurrentPage() {
        return userDAO.getCurrentPage();
    }

    public List<User> findUserByPage(int currentPage, String employeeNo) {
        return userDAO.findUserByPage(currentPage, employeeNo);
    }
}
