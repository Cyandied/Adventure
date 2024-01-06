package Main;

import Utility.Item;
import Utility.SQLiteJDBC;

import java.io.File;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Scanner;

public class Items {

    public HashMap<String, Item> items;

    public Items(SQLiteJDBC db) {
        items = new HashMap<String, Item>();
        populate(db);
    }

    public void populate(SQLiteJDBC db){
        ResultSet res = db.get_database("items");
        try {
            while (res.next()){
                String id = res.getString("id");
                items.put(id, new Item(id,res.getString("name"), res.getString("desc")));
            }
        } catch (Exception e){
            System.out.println("Items not found!");
            System.err.println(e);
        }
    }

    public void populate(){
        try{
            Scanner sc = new Scanner(new File("src/res/databases/items.csv"));
            sc.useDelimiter("_");
            String id = "";
            String name = "";
            String desc = "";
            int i = 1;
            while (sc.hasNext()){
                String val = sc.next();
                if(!val.isEmpty()) {
                    switch (i) {
                        case 1:
                            id = val;
                            i++;
                            break;
                        case 2:
                            name = val;
                            i++;
                            break;
                        case 3:
                            desc = val;
                            i = 1;
                            items.put(id, new Item(id, name, desc));
                            break;
                    }
                }
            }
            sc.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
