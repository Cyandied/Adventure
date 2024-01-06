package Utility;

import Main.Items;
import Main.Locations;
import Main.Player;

import java.util.Arrays;
import java.util.HashMap;

public class Interaction {

    final public String flavour;
    final private String[] give_item_ids;
    final public String[] parent_item_ids;
    final private boolean destroy_parents;
    final private String[] check_ids;
    final public String location_id;
    public boolean interaction_has_played = false;
    final private Player player;
    final private HashMap<String, Item> items;
    final private HashMap<String, Location> locations;

    public Interaction(String flavour, String[] give_item_ids, String[] parent_item_ids, boolean destroy_parents, String[] check_ids,String location_id, Player player, Items items, Locations locations) {
        this.flavour = flavour;
        this.give_item_ids = give_item_ids;
        this.parent_item_ids = parent_item_ids;
        this.destroy_parents = destroy_parents;
        this.check_ids = check_ids;
        this.location_id = location_id;
        this.player = player;
        this.items = items.items;
        this.locations = locations.locations;
    }

    public void activate(){
        if(!interaction_has_played){
            destroy_parents();
            give_items();
        }
    }

    public String text() {
        String text = "";
        if(!location_id.equals("0")){
            text = locations.get(location_id).desc + "\n";
        }
        if(!interaction_has_played){
            return text + this.flavour;
        }
        return text + "You feel a sense of deja vu.";
    }

    private void give_items() {
        if(give_item_ids.length > 0){
            for(String id : give_item_ids){
                player.inventory.put(id, items.get(id));
            }
        }
    }

    public String to_string(){
        return flavour + " , " + Arrays.toString(give_item_ids) + " , " + Arrays.toString(parent_item_ids) + " , " + destroy_parents + " , " + Arrays.toString(check_ids) + " , " + location_id;
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
