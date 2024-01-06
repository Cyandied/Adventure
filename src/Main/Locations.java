package Main;

import Utility.Item;
import Utility.Location;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class Locations {

    public HashMap<String, Location> locations;

    public Locations() {
        locations = new HashMap<String, Location>();
        populate();
    }

    public void populate(){
        try{
            Scanner sc = new Scanner(new File("src/res/databases/locations.csv"));
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
                            locations.put(id, new Location(id, name, desc));
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
