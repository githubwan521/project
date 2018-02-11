package Dao;

import IDao.SeatImpl;
import Model.Seat;
import Util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SeatDAO implements SeatImpl
{
    public static final int PAGE_SIZE = 5; // 每页显示条数
    private int allCount; // 数据库中条数
    private int allPageCount; // 总页数
    private int currentPage; // 当前页

    public int getAllCount()
    {
        return allCount;
    }

    public int getAllPageCount()
    {
        return allPageCount;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    /**
     * 存储用户信息
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public ArrayList<Seat> findSeatByPage(int cPage, String seat_row)
    {
        currentPage = cPage;
        ArrayList<Seat> list = new ArrayList<Seat>();

        // 若未指定查找某人，则默认全查
        if(null == seat_row || seat_row.equals("null"))
        {
            seat_row = "";
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取记录总数 as AllRecord重命名

            String sql1 = "select count(studio_id) as AllRecord from Seat where seat_row like ?";
            conn = ConnectionManager.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1, "%" + seat_row + "%");
            rs = pstmt.executeQuery();
            // allCount; 数据库中条数
            if(rs.next())
                allCount = rs.getInt("AllRecord");
            rs.close();
            pstmt.close();

            // 记算总页数 PAGE_SIZE = 5; // 每页显示条数
            allPageCount = (allCount + PAGE_SIZE - 1) / PAGE_SIZE;

            // 如果当前页数大于总页数，则赋值为总页数
            if(allPageCount > 0 && currentPage > allPageCount)
                currentPage = allPageCount;

            // 获取第currentPage页数据
            String sql2 = "select * from Seat where seat_row like ? limit ?,?";
            // select * from tablename limit 开始位置,每页行数
            pstmt = conn.prepareStatement(sql2);
            pstmt.setString(1, "%" + seat_row + "%");
            pstmt.setInt(2, PAGE_SIZE * (currentPage - 1));
            pstmt.setInt(3, PAGE_SIZE);
            rs = pstmt.executeQuery();
            Seat seat = null;
            while(rs.next())
            {
                seat = new Seat();
                seat.setSeat_id(rs.getInt("seat_id"));
                seat.setStudio_id(rs.getInt("studio_id"));
                seat.setSeat_row(rs.getInt("seat_row"));
                seat.setSeat_column(rs.getInt("seat_column"));
                seat.setSeat_status(rs.getInt("seat_status"));

                // 将该用户信息插入列表
                list.add(seat);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, conn);
            return list;
        }
    }

    @SuppressWarnings("finally")
    public boolean insert(Seat seat)
    {
        boolean result = false;
        if(seat == null)
            return result;

        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "insert into seat(studio_id, seat_row, seat_column, seat_status, emp_email) values(?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, seat.getStudio_id());
            pstmt.setInt(2, seat.getSeat_row());
            pstmt.setInt(3, seat.getSeat_column());
            pstmt.setInt(4, seat.getSeat_status());
            pstmt.executeUpdate();
            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 删除用户(通过seatId)
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean delete(int seatId)
    {
        boolean result = false;
        if(seatId == 0)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            // 删除子某个用户
            String sql = "delete from seat where seat_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, seatId);
            pstmt.executeUpdate();
            ConnectionManager.close(null, pstmt, con);

            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
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
    public boolean update(Seat seat)
    {
        boolean result = false;
        if(seat == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "update seat set studio_id=?,seat_row=?,seat_column=?,seat_status=? where seat_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, seat.getStudio_id());
            pstmt.setInt(2, seat.getSeat_row());
            pstmt.setInt(3, seat.getSeat_column());
            pstmt.setInt(4, seat.getSeat_status());
            pstmt.setInt(5, seat.getSeat_id());

            pstmt.executeUpdate();
            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 获取所有用户信息(一般用于和界面交互)
     * @return Seat列表
     */
    @SuppressWarnings("finally")
    public ArrayList<Seat> findSeatAll()
    {
        ArrayList<Seat> list = new ArrayList<Seat>();
        Seat info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from seat");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new Seat();

                info.setSeat_id(rs.getInt("seat_id"));
                info.setStudio_id(rs.getInt("studio_id"));
                info.setSeat_row(rs.getInt("seat_row"));
                info.setSeat_column(rs.getInt("seat_column"));
                info.setSeat_status(rs.getInt("seat_status"));

                // 加入列表
                list.add(info);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }

    /**
     * 根据seat_id获取用户信息(一般用于数据内部关联操作)
     * @return 用户
     */
    @SuppressWarnings("finally")
    public Seat findSeatById(int seatId)
    {
        Seat info = null;
        if(seatId == 0)
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据

            pstmt = con.prepareStatement("select * from seat where seat_id=?");
            pstmt.setInt(1, seatId);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                // 如果有值的话再实例化
                info = new Seat();
                info.setSeat_id(seatId);
                info.setStudio_id(rs.getInt("studio_id"));
                info.setSeat_row(rs.getInt("seat_row"));
                info.setSeat_column(rs.getInt("seat_column"));
                info.setSeat_status(rs.getInt("seat_status"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }

    public ArrayList<Seat> selectByStudio(int studioid)
    {
        // TODO 自动生成的方法存根
        ArrayList<Seat> studioList = new ArrayList<Seat>();
        Seat info = null;
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from seat where studio_id=?");
            pstmt.setInt(1, studioid);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new Seat();

                info.setSeat_id(rs.getInt("seat_id"));
                info.setStudio_id(rs.getInt("studio_id"));
                info.setSeat_row(rs.getInt("seat_row"));
                info.setSeat_column(rs.getInt("seat_column"));
                info.setSeat_status(rs.getInt("seat_status"));

                // 加入列表
                studioList.add(info);
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return studioList;
        }
    }

    public Seat selectByMany(int studioid, int seat_row, int seat_column)
    {
        Seat info = null;
        if(studioid == 0 && seat_row == 0 && seat_column == 0)
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据

            pstmt = con.prepareStatement("select * from seat where studio_id=? AND seat_row=? AND seat_column=?");
            pstmt.setInt(1, studioid);
            pstmt.setInt(2, seat_row);
            pstmt.setInt(3, seat_column);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                // 如果有值的话再实例化
                info = new Seat();
                info.setSeat_id(rs.getInt("seat_id"));
                info.setStudio_id(rs.getInt("studio_id"));
                info.setSeat_row(rs.getInt("seat_row"));
                info.setSeat_column(rs.getInt("seat_column"));
                info.setSeat_status(rs.getInt("seat_status"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    }

}
