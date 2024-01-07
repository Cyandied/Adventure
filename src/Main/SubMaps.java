package Main;

import UIelems.Arrows;
import UIelems.View;
import Utility.SQLiteJDBC;
import Utility.SubMap;
import javafx.scene.layout.Pane;

import java.io.File;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class SubMaps {

    public HashMap<String, SubMap> submaps;
    private final View view;
    public SubMap active;

    private Arrows arrows;

    private String starter_map_id = "A";

    public SubMaps(View view, SQLiteJDBC db, Arrows arrows){
        submaps = new HashMap<String, SubMap>();
        this.view = view;
        this.arrows = arrows;
        populate(db);
    }

    private void populate(SQLiteJDBC db){
        ResultSet res = db.get_database("submaps");
        try {
            while (res.next()){
                String id = res.getString("id");
                submaps.put(id, new SubMap(id,
                        res.getString("location_ids").split(":"),
                        res.getString("submap_ids").split(":"),
                        res.getString("name"),
                        res.getString("desc"),
                        res.getString("background_image"),
                        res.getBoolean("unlocked")
                        ));
            }
        } catch (Exception e){
            System.out.println("Submaps not found");
            System.err.println(e);
        }
    }

    public String set_up(){
        SubMap starter_map = submaps.get(starter_map_id);
        switch_map(starter_map);
        return get_text(starter_map);
    }
    private void switch_map(SubMap submap){
        active = submap;
        place_arrows();
        view.set_scene(submap.background_image);
    }

    private void place_arrows(){
        arrows.prime();
        for(int i = 0; i<active.submap_ids.length; i++){
            String submap_id = active.submap_ids[i];
            if(!submap_id.isEmpty() && submaps.get(submap_id).unlocked){
                switch (i){
                    case 0:
                        arrows.place_arrow("up", submap_id);
                        break;
                    case 1:
                        arrows.place_arrow("down", submap_id);
                        break;
                    case 2:
                        arrows.place_arrow("left", submap_id);
                        break;
                    case 3:
                        arrows.place_arrow("right", submap_id);
                        break;
                }
            }
        }
    }

    private String get_text(SubMap submap){
        return submap.name + "\n" + submap.desc;
    }

    public boolean check_map_avaible(String map_id) {
        return ((submaps.containsKey(map_id) && Arrays.asList(active.submap_ids).contains(map_id)) || map_id.equals(active.id));
    }

    public String switch_map(String map_id) {
        SubMap map = submaps.get(map_id);
        if(map.unlocked) {
            switch_map(map);
            return get_text(map);
        }
        return "You cannot go here";
    }

}
