package Utility;

import java.sql.*;

public class SQLiteJDBC {

    private Connection c;

    public SQLiteJDBC() {
        c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        } catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database!");
    }

    public ResultSet get_database(String database){
        ResultSet res = null;
        try {
            res = c.createStatement().executeQuery("select * from " + database);
        }
        catch (Exception e) {
            System.err.println(e);
        }
        return res;
    }

}
