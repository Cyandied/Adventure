package Utility;

import Main.Items;
import Main.Locations;
import Main.Player;
import Main.SubMaps;
import UIelems.LocationLabels;

import java.util.Arrays;
import java.util.HashMap;

public class Interaction {

    final public String flavour;
    final private String[] give_item_ids;
    final public String[] parent_item_ids;
    final private boolean destroy_parents;
    final private String[] unlock_submaps;
    final public String location_id;
    public boolean interaction_has_played = false;

    public String set_location_special;
    public String set_location_clear;
    final private Player player;
    final private HashMap<String, Item> items;
    final private HashMap<String, Location> locations;
    final private SubMaps submaps;
    final private LocationLabels location_labels;


    public Interaction(String flavour, String[] give_item_ids, String[] parent_item_ids, boolean destroy_parents, String[] check_ids, String location_id, String set_location_cleared, String set_location_special, Player player, Items items, Locations locations, SubMaps submaps, LocationLabels location_labels) {
        this.flavour = flavour;
        this.give_item_ids = give_item_ids;
        this.parent_item_ids = parent_item_ids;
        this.destroy_parents = destroy_parents;
        this.unlock_submaps = check_ids;
        this.location_id = location_id;
        this.set_location_clear = set_location_cleared;
        this.set_location_special = set_location_special;

        this.player = player;
        this.items = items.items;
        this.locations = locations.locations;
        this.submaps = submaps;
        this.location_labels = location_labels;
    }

    public void activate(){
        if(!interaction_has_played){
            destroy_parents();
            give_items();
            unlock_submaps();
        }
    }

    public String text() {
        String text = "";
        if(!location_id.equals("0")){
            String use_id = location_id;
            if(locations.get(location_id).cleared){
                return locations.get("000").desc;
            }
            else if(locations.get(location_id).special){
                text = "Use new id for this location: " + locations.get(location_id).special_location_ref + "\n";
                use_id = locations.get(location_id).special_location_ref;
            }
            text = locations.get(use_id).desc + "\n";
        }
        if(!interaction_has_played){
            set_special_clear();
            return text + this.flavour;
        }
        return text + "You feel a sense of deja vu.";
    }

    private void set_special_clear(){
        if(!set_location_special.equals("0") && locations.containsKey(set_location_special)){
            locations.get(set_location_special).special = true;
            for(int i = 0 ; i < submaps.active.location_ids.length;i++){
                String id = submaps.active.location_ids[i];
                if(id.equals(set_location_special)){
                    submaps.active.location_ids[i] = locations.get(set_location_special).special_location_ref;
                }
            }
            location_labels.draw_locations(locations, submaps.active.location_ids);
        }
        if(!set_location_clear.equals("0") && locations.containsKey(set_location_clear)){
            locations.get(set_location_clear).cleared = true;
        }
    }

    private void give_items() {
        if(give_item_ids.length > 0 && !give_item_ids[0].isEmpty()){
            for(String id : give_item_ids){
                player.inventory.put(id, items.get(id));
            }
        }
    }

    private void unlock_submaps() {
        if(unlock_submaps.length > 0 && !unlock_submaps[0].isEmpty()) {
            for (String id : unlock_submaps){
                submaps.submaps.get(id).unlocked = !submaps.submaps.get(id).unlocked;
            }
            submaps.switch_map(submaps.active.id);
        }
    }

    public String to_string(){
        return flavour + " , " + Arrays.toString(give_item_ids) + " , " + Arrays.toString(parent_item_ids) + " , " + destroy_parents + " , " + Arrays.toString(unlock_submaps) + " , " + location_id;
    }

    private void destroy_parents() {
        if(destroy_parents) {
            for(String id : parent_item_ids){
                try {
                    player.inventory.remove(id);
                }
                catch (Exception e){
                    System.out.println(e);
                }

            }
        }
    }


}
