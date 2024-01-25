package Main;

import UIelems.LocationLabels;
import Utility.Interaction;
import Utility.SQLiteJDBC;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;

public class InteractionManager {

    private HashMap<String, Interaction> interactions;
    private Player p;
    private Items items;
    public Locations locations;
    private SubMaps submaps;
    private LocationLabels location_labels;

    public InteractionManager(Player p, SubMaps submaps, SQLiteJDBC db, Locations locations, LocationLabels ll){
        interactions = new HashMap<String, Interaction>();
        this.p = p;
        this.submaps = submaps;
        items = new Items(db);
        this.locations = locations;
        this.location_labels = ll;
        populate(db);
    }

    private boolean interaction_exist(String id){
        return (interactions.containsKey(id));
    }

    public String request_interaction(String id){
        if(interaction_exist(id)) {
            Interaction interaction = interactions.get(id);
            if (interaction.location_id.equals("0")) {
                for (String check_id : interaction.parent_item_ids) {
                    if (!p.inventory.containsKey(check_id)) {
                        return "You do not have the items required for this";
                    }
                }
                interaction.activate();
                String display_text = interaction.text();
                interaction.interaction_has_played = true;
                return display_text;
            }
            else if(Arrays.asList(submaps.active.location_ids).contains(id)){
                interaction.activate();
                String display_text = interaction.text();
                interaction.interaction_has_played = true;
                return display_text;
            }
            else {
                return "You cannot access this location from here";
            }
        }
        return "No entry";
    }

    private void populate(SQLiteJDBC db){
        ResultSet res = db.get_database("interactions");
        try {
            while (res.next()){
                interactions.put(res.getString("id"), new Interaction(
                        res.getString("flavour"),
                        res.getString("give_item_ids").split(":"),
                        res.getString("parent_item_ids").split(":"),
                        res.getBoolean("destroy_parents"),
                        res.getString("submap_ids").split(":"),
                        res.getString("location_id"),
                        res.getString("set_location_clear"),
                        res.getString("set_location_special"),
                        p,
                        items,
                        locations,
                        submaps,
                        location_labels
                ));
            }
        }
        catch (Exception e){
            System.out.println("Interactions not found");
            System.err.println(e);
        }

    }


}
