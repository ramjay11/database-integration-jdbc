package com.ramjava.database.integration.jdbc.connectionmanager.tables;

import com.ramjava.database.integration.jdbc.connectionmanager.db.DBType;
import com.ramjava.database.integration.jdbc.connectionmanager.db.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;

public class ToursManager {

    public static void displayAllRows() throws SQLException {

        String sql = "SELECT adminId, userName, password FROM admin";
        try (
                Connection conn = DBUtil.getConnection(DBType.MYSQL);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();

                buffer.append("Tour " + rs.getInt("tourId") + ": ");
                buffer.append(rs.getString("tourName"));

                double price = rs.getDouble("price");
                NumberFormat nf = NumberFormat.getCurrencyInstance();
                String formattedPrice = nf.format(price);
                buffer.append(" (" + formattedPrice +")");

                System.out.println(buffer.toString());
            }
        }
    }
}
