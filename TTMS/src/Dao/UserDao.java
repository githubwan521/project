package Dao;


import IDao.UserImpl;
import Model.User;
import Util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDao implements UserImpl {

    public static final int PAGE_SIZE = 6; // 每页显示条数
    private static int allCount; // 数据库中条数
    private static int allPageCount; // 总页数
    private static int currentPage; // 当前页

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
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean insert(User user) {
        boolean result = false;
        if(user == null) {
            return result;
        }

        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into user(emp_no, emp_pass, type, head_path) values(?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUser_no());
            pstmt.setString(2, user.getUser_pass());
            pstmt.setInt(3, user.getUser_type());
            pstmt.setString(4, user.getHead_path());
            System.out.println(sql);
            System.out.println(pstmt);
            pstmt.executeUpdate();
            result = true;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 删除用户(通过employeeNo)
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean delete(String employeeNo) {
        boolean result = false;
        if(employeeNo == null) {
            return result;
        }

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            // 删除子某个用户
            String sql = "delete from user where emp_no=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, employeeNo);
            pstmt.executeUpdate();
            ConnectionManager.close(null, pstmt, con);

            result = true;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 修改用户信息
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean update(User user) {
        boolean result = false;
        if(user == null) {
            return result;
        }

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "update user set emp_pass=?,head_path=? where emp_no=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getUser_pass());
            pstmt.setString(2, user.getHead_path());
            pstmt.setString(3, user.getUser_no());

            pstmt.executeUpdate();
            result = true;
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 获取所有用户信息(一般用于和界面交互)
     * @return User列表
     */
    @SuppressWarnings("finally")
    public ArrayList<User> findUserAll() {
        ArrayList<User> list = new ArrayList<User>();
        User info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from user");
            rs = pstmt.executeQuery();
            while(rs.next()) {
                info = new User();

                info.setUser_no(rs.getString("emp_no"));
                info.setUser_pass(rs.getString("emp_pass"));
                info.setUser_type(rs.getInt("type"));
                info.setHead_path(rs.getString("head_path"));
                // 加入列表
                list.add(info);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }

    @SuppressWarnings("finally")
    public ArrayList<User> findUserByPage(int cPage, String empNo)
    {
        currentPage = cPage;
        ArrayList<User> list = new ArrayList<User>();

        // 若未指定查找某人，则默认全查
        if (null == empNo || empNo.equals("null")) {
            empNo = "";
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取记录总数
            String sql1 = "select count(emp_no) as AllRecord from user where emp_no like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + empNo + "%");
            System.out.println("-->" + pstmt);
            rs = pstmt.executeQuery();
            if (rs.next())
                allCount = rs.getInt("AllRecord");
            rs.close();
            pstmt.close();

            System.out.println(allCount);
            // 记算总页数
            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;

            // 如果当前页数大于总页数，则赋值为总页数
            if (allPageCount > 0 && currentPage > allPageCount)
                currentPage = allPageCount;

            // 获取第currentPage页数据
            String sql2 = "select * from user where emp_no like ? limit ?,?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + empNo + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            System.out.println(pstmt);
            rs = pstmt.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User();
                user.setUser_no(rs.getString("emp_no"));
                user.setUser_pass(rs.getString("emp_pass"));
                user.setUser_type(rs.getInt("type"));
                user.setHead_path(rs.getString("head_path"));

                // 将该用户信息插入列表
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, conn);
            return list;
        }
    }

    /**
     * 根据emp_no获取用户信息(一般用于数据内部关联操作)
     * @return 用户
     */
    @SuppressWarnings("finally")
    public User findUserByNo(String employeeNo) {
        User info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from user where emp_no=?");
            pstmt.setString(1, employeeNo);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                // 如果有值的话再实例化
                info = new User();
                info.setUser_no(rs.getString("emp_no"));
                info.setUser_pass(rs.getString("emp_pass"));
                info.setUser_type(rs.getInt("type"));
                info.setHead_path(rs.getString("head_path"));
                System.out.println(info.getUser_no());
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }

}
