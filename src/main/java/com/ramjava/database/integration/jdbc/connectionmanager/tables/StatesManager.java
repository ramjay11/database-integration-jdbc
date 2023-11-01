package com.ramjava.database.integration.jdbc.connectionmanager.tables;

import com.ramjava.database.integration.jdbc.connectionmanager.db.DBType;
import com.ramjava.database.integration.jdbc.connectionmanager.db.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatesManager {

    public static void displayAllRows() throws SQLException {

        String sql = "SELECT adminId, userName, password FROM admin";
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {

            while(rs.next()) {
                String stateFullName =
                        rs.getString("stateId") + ": " + rs.getString("stateName");
                System.out.println(stateFullName);
            }

        }
    }
}