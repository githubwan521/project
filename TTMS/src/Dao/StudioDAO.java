package Dao;

import IDao.StudioImpl;
import Model.Studio;
import Util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class StudioDAO implements StudioImpl {
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
     * 存储演出厅信息
     *
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean insert(Studio studio) {
        boolean result = false;
        if (studio == null) {
            return result;
        }

        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "insert into Studio(studio_name,studio_row_count,studio_col_count,studio_introduction,studio_flag) values(?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, studio.getStudio_name());
            pstmt.setInt(2, studio.getStudio_row_count());
            pstmt.setInt(3, studio.getStudio_col_count());
            pstmt.setString(4, studio.getStudio_introduction());
            pstmt.setInt(5, studio.getStudio_flag());

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
     * 删除演出厅(通过studioId)
     *
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean delete(int studioId) {
        boolean result = false;
        if (studioId == 0) {
            return result;
        }

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            // 删除子某个演出厅
            String sql = "delete from studio where studio_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, studioId);
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
     * 修改演出厅信息
     *
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean update(Studio studio) {
        boolean result = false;
        if (studio == null) {
            return result;
        }

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try {
            String sql = "update studio set studio_name=?,studio_row_count=?,studio_col_count=?,studio_introduction=?,studio_flag=? where studio_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, studio.getStudio_name());
            pstmt.setInt(2, studio.getStudio_row_count());
            pstmt.setInt(3, studio.getStudio_col_count());
            pstmt.setString(4, studio.getStudio_introduction());
            pstmt.setInt(5, studio.getStudio_flag());
            pstmt.setInt(6, studio.getStudio_id());

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
     * @return Studio列表
     */
    @SuppressWarnings("finally")
    public ArrayList<Studio> findStudioAll() {
        ArrayList<Studio> list = new ArrayList<Studio>();
        Studio info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from studio");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                info = new Studio();

                info.setStudio_id(rs.getInt("studio_id"));
                info.setStudio_name(rs.getString("studio_name"));
                info.setStudio_row_count(rs.getInt("studio_row_count"));
                info.setStudio_col_count(rs.getInt("studio_col_count"));
                info.setStudio_introduction(rs.getString("studio_introduction"));
                info.setStudio_flag(rs.getInt("studio_flag"));
                // 加入列表
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
     * @return Studio列表
     */
    @SuppressWarnings("finally")
    public ArrayList<Studio> findStudioByName(String studioName) {
        if (studioName == null || studioName.equals("")) {
            return null;
        }

        ArrayList<Studio> list = new ArrayList<Studio>();
        Studio info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取所有演出厅数据:模糊查询
            pstmt = con.prepareStatement("select * from studio where studio_name like ?");
            pstmt.setString(1, "%" + studioName + "%");// 拼接模糊查询串
            rs = pstmt.executeQuery();
            while (rs.next()) {
                // 如果有值的话再实例化
                info = new Studio();
                info.setStudio_id(rs.getInt("studio_id"));
                info.setStudio_name(rs.getString("studio_name"));
                info.setStudio_row_count(rs.getInt("studio_row_count"));
                info.setStudio_col_count(rs.getInt("studio_col_count"));
                info.setStudio_introduction(rs.getString("studio_introduction"));
                info.setStudio_flag(rs.getInt("studio_flag"));
                // 加入列表
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
     * 根据studio_id获取演出厅信息(一般用于数据内部关联操作)
     *
     * @return 演出厅
     */
    @SuppressWarnings("finally")
    public Studio findStudioById(int studioId) {
        Studio info = null;
        if (studioId == 0) {
            return info;
        }

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from studio where studio_id=?");
            pstmt.setInt(1, studioId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // 如果有值的话再实例化
                info = new Studio();
                info.setStudio_id(rs.getInt("studio_id"));
                info.setStudio_name(rs.getString("studio_name"));
                info.setStudio_row_count(rs.getInt("studio_row_count"));
                info.setStudio_col_count(rs.getInt("studio_col_count"));
                info.setStudio_introduction(rs.getString("studio_introduction"));
                info.setStudio_flag(rs.getInt("studio_flag"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }

    @SuppressWarnings("finally")
    public ArrayList<Studio> findStudioByPage(int cPage, String studioName) {
        currentPage = cPage;
        ArrayList<Studio> list = new ArrayList<Studio>();

        // 若未指定查找某人，则默认全查
        if (null == studioName || studioName.equals("null")) {
            studioName = "";
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 获取记录总数
            String sql1 = "select count(studio_name) as AllRecord from studio where studio_name like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + studioName + "%");
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
            String sql2 = "select * from studio where studio_name like ? limit ?,?";
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + studioName + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            System.out.println(pstmt);
            rs = pstmt.executeQuery();
            Studio studio = null;
            while (rs.next()) {
                studio = new Studio();
                studio.setStudio_id(rs.getInt("studio_id"));
                studio.setStudio_name(rs.getString("studio_name"));
                studio.setStudio_row_count(rs.getInt("studio_row_count"));
                studio.setStudio_col_count(rs.getInt("studio_col_count"));
                studio.setStudio_flag(rs.getInt("studio_flag"));

                // 将该用户信息插入列表
                list.add(studio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(rs, pstmt, conn);
            return list;
        }
    }

    public static void main(String[] args) {

    }
}
