package com.itheima.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtil {
  static String driverClass = null;
  static String url = null;
  static String user = null;
  static String password = null;
  static {
    try {
      Properties properties = new Properties();
//      properties.load(JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
      properties.load(new FileInputStream("jdbc.properties"));
      driverClass = properties.getProperty("driverClass");
      url = properties.getProperty("url");
      user = properties.getProperty("user");
      password = properties.getProperty("password");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Connection getConn() {
    Connection conn = null;
    try {
      Class.forName(driverClass);
      conn = DriverManager.getConnection(url, user, password);
    } catch (ClassNotFoundException | SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return conn;
  }

  public static void release(ResultSet rs, Statement st, Connection conn) {
    closeRs(rs);
    closeSt(st);
    closeConn(conn);
  }

  private static void closeRs(ResultSet rs) {
    try {
      if (rs != null) {
        rs.close();
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      rs = null;
    }
  }

  private static void closeSt(Statement st) {
    try {
      if (st != null) {
        st.close();
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      st = null;
    }
  }

  private static void closeConn(Connection conn) {
    try {
      if (conn != null) {
        conn.close();
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      conn = null;
    }
  }
}
