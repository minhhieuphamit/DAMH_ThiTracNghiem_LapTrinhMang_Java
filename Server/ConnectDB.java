/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Pham Minh Hieu
 */
public class ConnectDB {

    public static Connection getConnection() {
        try {
            String dbURL = "jdbc:sqlserver://localhost;databaseName=THITRACNGHIEM;user=sa;password=123";
            Connection conn = DriverManager.getConnection(dbURL);
            System.out.println("Success");
            return conn;
        } catch (SQLException e) {
            System.out.println("Login fail");
            e.printStackTrace();
            return null;
        }
    }

    public static boolean getConnect(String str) {
        boolean check = false;
        Connection conn = null;
        String[] arrStr = str.split("///");
        System.out.println(arrStr[0] + arrStr[1] + arrStr[2]);
        String ServerName = arrStr[0];
        String name = arrStr[1];
        String pass = arrStr[2];
        String url = "jdbc:sqlserver://" + ServerName + ";databaseName=THITRACNGHIEM";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, name, pass);
            if (conn != null) {
                System.out.println("Successful connection to database!!");
                check = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public static boolean insertThongTin(String s, String s1, String s2) {
        boolean check = false;
        Connection conn = getConnection();
        String sql = "INSERT INTO SINHVIEN(MASV,HOTEN,SODIENTHOAI) VALUES('" + s1 + "','" + s + "','" + s2 + "')";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            check = true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public static String getAllCauHoi() {
        List<CauHoi> listCH = new ArrayList<CauHoi>();
        int[] socau = new int[1000];
        Arrays.fill(socau, 0);
        Connection conn = getConnection();
        String sql = "select * from BODE";
        Random rand = new Random();
        int dem = 0;
        while (dem < 10) {
            int k = rand.nextInt(220);
            if (socau[k] != 1) {
                socau[k] = 1;
                dem++;
            }
        }
        dem = -1;
        String str = "";
        try {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();
            while (rs.next()) {
                dem++;
                if (socau[dem] > 0) {
                    str += rs.getString("CAUHOI");
                    str += "///";
                    str += rs.getString("TRINHDO");
                    str += "///";
                    str += rs.getString("NOIDUNG");
                    str += "///";
                    str += rs.getString("A");
                    str += "///";
                    str += rs.getString("B");
                    str += "///";
                    str += rs.getString("C");
                    str += "///";
                    str += rs.getString("D");
                    str += "///";
                    str += rs.getString("DAP_AN");
                    str += "///";
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return str;
    }

    public static boolean updateCore(String mssv, int diem, String ngayThi) {
        boolean check = false;
        Connection conn = getConnection();
        String sql = "UPDATE SINHVIEN SET DIEM = '" + diem + "', NGAYTHI = '" + ngayThi + "' WHERE MASV LIKE '" + mssv + "'";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            check = true;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }
}
