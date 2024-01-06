package Main;

import UIelems.View;
import Utility.Location;
import Utility.SubMap;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SubMaps {

    private HashMap<String, SubMap> submaps;
    private final View view;
    public SubMap active;

    private String starter_map_id = "A";

    public SubMaps(View view){
        submaps = new HashMap<String, SubMap>();
        this.view = view;
        populate();
    }

    public String set_up(){
        SubMap starter_map = submaps.get(starter_map_id);
        switch_map(starter_map);
        return get_text(starter_map);
    }
    private void switch_map(SubMap submap){
        active = submap;
        view.set_scene(submap.background_image);
    }

    private String get_text(SubMap submap){
        return submap.name + "\n" + submap.desc;
    }

    public boolean check_map_avaible(String map_id) {
        return ((submaps.containsKey(map_id) && Arrays.asList(active.submap_ids).contains(map_id)) || map_id.equals(active.id));
    }

    public String switch_map(String map_id) {
        SubMap map = submaps.get(map_id);
        switch_map(map);
        return get_text(map);
    }

    private void populate(){
        try{
            Scanner sc = new Scanner(new File("src/res/databases/submaps.csv"));
            sc.useDelimiter("_");
            String id = "";
            String[] location_ids = {};
            String[] submap_ids = {};
            String name = "";
            String desc = "";
            String background_image = "";
            int i = 1;
            while (sc.hasNext()){
                String val = sc.next();
                    switch (i) {
                        case 1:
                            id = val;
                            i++;
                            break;
                        case 2:
                            location_ids = val.split(":");
                            i++;
                            break;
                        case 3:
                            submap_ids = val.split(":");
                            i++;
                            break;
                        case 4:
                            name = val;
                            i++;
                            break;
                        case 5:
                            desc = val;
                            i++;
                            break;
                        case 6:
                            background_image = val;
                            i = 1;
                            submaps.put(id, new SubMap(id, location_ids, submap_ids, name, desc, background_image));
                            break;
                    }
                }
            sc.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

}
