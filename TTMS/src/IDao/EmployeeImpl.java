package IDao;

import Model.Employee;

import java.util.ArrayList;

/**
 * 员工表的增删改查接口
 */
public interface EmployeeImpl {
    // 增
    boolean insert(Employee employee);

    // 删
    boolean delete(int employeeId);

    // 改
    boolean update(Employee employee);

    // 查所有用户(一般用于和界面交互)
    ArrayList<Employee> findEmployeeAll();

    // 根据用户名查(一般用于和界面交互)
    ArrayList<Employee> findEmployeeByName(String employeeName);

    // 根据用户id查(一般用于数据内部关联操作)
    Employee findEmployeeById(String employeeId);

    // 查询页数  起始页
    ArrayList<Employee> findEmployeeByPage(int cPage, String emp_name);

}
