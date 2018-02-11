package Dao;

import IDao.EmployeeImpl;
import Model.Employee;
import Util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDao implements EmployeeImpl {
    public static final int PAGE_SIZE = 5; // 每页显示条数
    private int allCount; // 数据库中条数
    private int allPageCount; // 总页数
    private int currentPage; // 当前页

    public int getAllCount() {
        return allCount;
    }

    public int getAllPageCount() {
        return allPageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }


    /**
     * 存储用户信息
     *
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean insert(Employee employee) {
        boolean result = false;
        if (employee == null) {
            return result;
        }
        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "INSERT INTO Employee(emp_no, emp_name, emp_tel_num, emp_addr, emp_email) VALUES(?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, employee.getEmp_no());
            pstmt.setString(2, employee.getEmp_name());
            pstmt.setString(3, employee.getEmp_tel_num());
            pstmt.setString(4, employee.getEmp_addr());
            pstmt.setString(5, employee.getEmp_email());

            pstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 删除用户(通过employeeId)
     *
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean delete(int employeeId) {
        boolean result = false;
        if (employeeId == 0) {
            return result;
        }

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            // 删除某个用户
            String sql = "DELETE FROM employee WHERE emp_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();
            ConnectionManager.close(null, pstmt, con);

            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 修改用户信息
     *
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean update(Employee employee) {
        boolean result = false;
        if (employee == null) {
            return result;
        }

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "UPDATE employee SET emp_no=?,emp_name=?,emp_tel_num=?,emp_addr=?,emp_email=? WHERE emp_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, employee.getEmp_no());
            pstmt.setString(2, employee.getEmp_name());
            pstmt.setString(3, employee.getEmp_tel_num());
            pstmt.setString(4, employee.getEmp_addr());
            pstmt.setString(5, employee.getEmp_email());
            pstmt.setString(6, employee.getEmp_id());

            pstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 获取所有用户信息(一般用于和界面交互)
     *
     * @return Employee列表
     */
    @SuppressWarnings("finally")
    public ArrayList<Employee> findEmployeeAll() {
        ArrayList<Employee> list = new ArrayList<Employee>();
        Employee info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取所有用户数据
            pstmt = con.prepareStatement("SELECT * FROM employee");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                info = new Employee();

                info.setEmp_id(rs.getString("emp_id"));
                info.setEmp_no(rs.getString("emp_no"));
                info.setEmp_name(rs.getString("emp_name"));
                info.setEmp_tel_num(rs.getString("emp_tel_num"));
                info.setEmp_addr(rs.getString("emp_addr"));
                info.setEmp_email(rs.getString("emp_email"));

                list.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }

    /**
     * 根据用户名获取用户信息(一般用于和界面交互)
     *
     * @return Employee列表
     */
    @SuppressWarnings("finally")
    public ArrayList<Employee> findEmployeeByName(String employeeName) {
        if (employeeName == null || employeeName.equals("")) {
            return null;
        }

        ArrayList<Employee> list = new ArrayList<Employee>();
        Employee info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取所有用户数据:模糊查询
            pstmt = con.prepareStatement("SELECT * FROM employee WHERE emp_name LIKE ?");
//            System.out.println(employeeName+"传参");  System.out.println(employeeName+"传参");
            pstmt.setString(1, "%" + employeeName + "%");// 拼接模糊查询串
            rs = pstmt.executeQuery();
            while (rs.next()) {
                info = new Employee();
                info.setEmp_id(rs.getString("emp_id"));
                info.setEmp_no(rs.getString("emp_no"));
                info.setEmp_name(rs.getString("emp_name"));
                info.setEmp_tel_num(rs.getString("emp_tel_num"));
                info.setEmp_addr(rs.getString("emp_addr"));
                info.setEmp_email(rs.getString("emp_email"));
                list.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }

    /**
     * 根据employee_id获取用户信息(一般用于数据内部关联操作)
     *
     * @return 用户
     */
    @SuppressWarnings("finally")
    public Employee findEmployeeById(String  employeeId) {
        Employee info = null;
        if (employeeId.equals("") || employeeId==null) {
            return info;
        }
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取所有用户数据
            pstmt = con.prepareStatement("SELECT * FROM employee WHERE emp_id=?");
            pstmt.setString(1, employeeId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                info = new Employee();
                info.setEmp_id(employeeId);
                info.setEmp_no(rs.getString("emp_no"));
                info.setEmp_name(rs.getString("emp_name"));
                info.setEmp_tel_num(rs.getString("emp_tel_num"));
                info.setEmp_addr(rs.getString("emp_addr"));
                info.setEmp_email(rs.getString("emp_email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }
    public ArrayList<Employee> findEmployeeByPage(int cPage, String emp_name) {
        currentPage = cPage;
        ArrayList<Employee> list = new ArrayList<Employee>();
        // 若未指定查找某人，则默认全查
        if (null == emp_name || emp_name.equals("null")) {
            emp_name = "";
        }
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取记录总数
            String sql1 = "select count(emp_no) as AllRecord from Employee where  emp_name like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + emp_name + "%");
            rs = pstmt.executeQuery();
            if (rs.next()) {
                allCount = rs.getInt("AllRecord");
            }
            rs.close();
            pstmt.close();

            // 记算总页数
            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;
            // 如果当前页数大于总页数，则赋值为总页数
            if (allPageCount > 0 && currentPage > allPageCount) {
                currentPage = allPageCount;
            }

            // 获取第currentPage页数据
            String sql2 = "select * from Employee where emp_name like ? limit ?,?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + emp_name + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            rs = pstmt.executeQuery();
            Employee employee = null;
            while (rs.next()) {
                employee = new Employee();
                employee.setEmp_id(rs.getString("emp_id"));
                employee.setEmp_no(rs.getString("emp_no"));
                employee.setEmp_name(rs.getString("emp_name"));
                employee.setEmp_tel_num(rs.getString("emp_tel_num"));
                employee.setEmp_addr(rs.getString("emp_addr"));
                employee.setEmp_email(rs.getString("emp_email"));
                // 将该用户信息插入列表
                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, conn);
            return list;
        }
    }

}
