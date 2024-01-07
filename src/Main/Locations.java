package Main;

import Utility.Item;
import Utility.Location;
import Utility.SQLiteJDBC;

import java.io.File;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Scanner;

public class Locations {

    public HashMap<String, Location> locations;

    public Locations(SQLiteJDBC db) {
        locations = new HashMap<String, Location>();
        populate(db);
    }

    public void populate(SQLiteJDBC db){
        ResultSet res = db.get_database("locations");
        try{
            while (res.next()) {
                String id = res.getString("id");
                locations.put(id, new Location(
                        id,
                        res.getString("name"),
                        res.getString("desc"),
                        res.getBoolean("cleared"),
                        res.getBoolean("special"),
                        res.getString("special_location_ref")
                ));
            }
        } catch (Exception e){
            System.out.println("Locations not found");
            System.err.println(e);
        }
    }

}
