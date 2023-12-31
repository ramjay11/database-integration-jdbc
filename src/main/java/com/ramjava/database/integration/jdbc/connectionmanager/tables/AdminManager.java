package com.ramjava.database.integration.jdbc.connectionmanager.tables;

import com.ramjava.database.integration.jdbc.connectionmanager.beans.Admin;
import com.ramjava.database.integration.jdbc.connectionmanager.db.DBType;
import com.ramjava.database.integration.jdbc.connectionmanager.db.DBUtil;

import java.sql.*;


public class AdminManager {

    public static void displayAllRows() throws SQLException {

        String sql = "SELECT adminId, userName, password FROM admin";
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {

            System.out.println("Admin Table:");
            while (rs.next()) {
                StringBuffer bf = new StringBuffer();
                bf.append(rs.getInt("adminId") + ": ");
                bf.append(rs.getString("userName") +", ");
                bf.append(rs.getString("password"));
                System.out.println(bf.toString());
            }
        }
    }

    public static Admin getRow(int adminId) throws SQLException {

        String sql = "SELECT * FROM admin WHERE adminId = ?";
        ResultSet rs = null;

        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, adminId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Admin bean = new Admin();
                bean.setAdminId(adminId);
                bean.setUserName(rs.getString("userName"));
                bean.setPassword(rs.getString("password"));
                return bean;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }

    }

    public static boolean insert(Admin bean) throws Exception {

        String sql = "INSERT into admin (userName, password) " +
                "VALUES (?, ?)";
        ResultSet keys = null;
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            stmt.setString(1, bean.getUserName());
            stmt.setString(2, bean.getPassword());
            int affected = stmt.executeUpdate();

            if (affected == 1) {
                keys = stmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                bean.setAdminId(newKey);
            } else {
                System.err.println("No rows affected");
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally{
            if (keys != null) keys.close();
        }
        return true;
    }

    public static boolean update(Admin bean) throws Exception {

        String sql =
                "UPDATE admin SET " +
                        "userName = ?, password = ? " +
                        "WHERE adminId = ?";
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                PreparedStatement stmt = conn.prepareStatement(sql);
        ){

            stmt.setString(1, bean.getUserName());
            stmt.setString(2, bean.getPassword());
            stmt.setInt(3, bean.getAdminId());

            int affected = stmt.executeUpdate();
            if (affected == 1) {
                return true;
            } else {
                return false;
            }

        }
        catch(SQLException e) {
            System.err.println(e);
            return false;
        }

    }

}
