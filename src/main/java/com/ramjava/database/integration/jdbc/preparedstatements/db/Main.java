package com.ramjava.database.integration.jdbc.preparedstatements.db;

import com.ramjava.database.integration.jdbc.preparedstatements.tables.Tours;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private static final String SQL =
            "SELECT tourId, tourName, price FROM tours";

    public static void main(String[] args) throws Exception {

        ResultSet rs = null;
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                Statement stmt = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
        ) {
            rs = stmt.executeQuery(SQL);
            Tours.displayData(rs);

        } catch (SQLException e) {
            System.err.println(e);
        }
        finally {
            if (rs != null) rs.close();
        }
    }

}
