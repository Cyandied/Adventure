package Utility;

import java.sql.*;

public class SQLiteJDBC {

    public SQLiteJDBC() {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        } catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database!");
    }

}
