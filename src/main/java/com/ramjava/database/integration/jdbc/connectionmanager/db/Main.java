package com.ramjava.database.integration.jdbc.connectionmanager.db;

import com.ramjava.database.integration.jdbc.connectionmanager.beans.Admin;
import com.ramjava.database.integration.jdbc.connectionmanager.tables.AdminManager;
import com.ramjava.database.integration.jdbc.connectionmanager.util.InputHelper;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Starting application");

        AdminManager.displayAllRows();

        int adminId = InputHelper.getIntegerInput("Select a row to update: ");

        Admin bean = AdminManager.getRow(adminId);
        if (bean == null) {
            System.err.println("Row not found");
            return;
        }

        String password = InputHelper.getInput("Enter new password: ");
        bean.setPassword(password);

        if (AdminManager.update(bean)) {
            System.out.println("Success!");
        } else
        {
            System.err.println("whoops!");
        }

    }
}