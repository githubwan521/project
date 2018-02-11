package Util;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * 数据库连接操作类
 */
public final class ConnectionManager {
    private static ConnectionManager instance;
    private static ComboPooledDataSource ds;

    // 初始化,只执行一次
    static {
        ResourceBundle rb = ResourceBundle.getBundle("c3p0");
        ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass(rb.getString("driver"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ds.setJdbcUrl(rb.getString("url"));
        ds.setUser(rb.getString("username"));
        ds.setPassword(rb.getString("password"));
    }

    /**
     * 获取数据库实例
     *
     * @return 连接对象ConnectionManager
     */
    public synchronized static final ConnectionManager getInstance() {
        if (instance == null) {
            try {
                instance = new ConnectionManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * 获取数据库连接
     *
     * @return 数据库连接对象Connection
     */
    public synchronized final Connection getConnection() {
        try {
            // 查看活动链接数
            // System.out.println("------->busy connections: " + ds.getNumBusyConnections());
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭数据库连接
     *
     * @return void
     */
    public static void close(ResultSet rs, Statement stmt, Connection con) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放数据库资源
     *
     * @return void
     * <p>
     * 收尾(finalize)机制
     * 垃圾回收器准备释放内存的时候，会先调用finalize()。
     * 垃圾回收只与内存有关。
     * 垃圾回收和finalize()都是靠不住的，只要JVM还没有快到耗尽内存的地步，它是不会浪费时间进行垃圾回收的。
     */
    @Override
    protected void finalize() throws Throwable {
        // 关闭datasource
        DataSources.destroy(ds);
        super.finalize();
    }

}
