package Service;

import Dao.FactoryDao;
import Model.Employee;

import java.util.ArrayList;

public class EmployeeService {
    public static boolean add(Employee employee){
        return FactoryDao.creatEmployeeDAO().insert(employee);
    }
    public static boolean delete(int emp_id){
        return FactoryDao.creatEmployeeDAO().delete(emp_id);
    }
    public static boolean update(Employee employee){
        return FactoryDao.creatEmployeeDAO().update(employee);
    }
    public static ArrayList<Employee> findEmployeeByPage(int cPage, String emp_name){//查询指定页数
        return FactoryDao.creatEmployeeDAO().findEmployeeByPage(cPage,emp_name);
    }
}
