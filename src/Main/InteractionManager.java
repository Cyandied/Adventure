package Main;

import Utility.Interaction;
import Utility.Item;
import Utility.Location;

import java.io.File;
import java.nio.file.attribute.UserPrincipal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class InteractionManager {

    private HashMap<String, Interaction> interactions;
    private Player p;
    private Items items;
    private Locations locations;
    private SubMaps submaps;

    public InteractionManager(Player p, SubMaps submaps){
        interactions = new HashMap<String, Interaction>();
        this.p = p;
        this.submaps = submaps;
        items = new Items();
        locations = new Locations();
        populate();
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
                return "You cannot acess this location from here";
            }
        }
        return "No entry";
    }

    private void populate(){
        try{
            Scanner sc = new Scanner(new File("src/res/databases/interactions.csv"));
            sc.useDelimiter("_");
            String id = "";
            String flavour = "";
            String[] item_ids = {};
            String[] parent_item_ids = {};
            boolean destroy_parents = false;
            String[] check_ids = {};
            String location_id = "";
            int i = 1;
            while (sc.hasNext()){
                String val = sc.next();
                    switch (i) {
                        case 1:
                            id = val;
                            i++;
                            break;
                        case 2:
                            flavour = val;
                            i++;
                            break;
                        case 3:
                            item_ids = arr_from_str(val);
                            i++;
                            break;
                        case 4:
                            parent_item_ids = arr_from_str(val);
                            i++;
                            break;
                        case 5:
                            destroy_parents = Boolean.parseBoolean(val);
                            i++;
                            break;
                        case 6:
                            check_ids = arr_from_str(val);
                            i++;
                            break;
                        case 7:
                            location_id = val;
                            i = 1;
                            interactions.put(id, new Interaction(flavour, item_ids, parent_item_ids, destroy_parents, check_ids, location_id,p,items,locations));
                            break;

                    }
                }
            sc.close();
        }
        catch (Exception e){
            System.out.println("oops");
            System.out.println(e);
        }
    }

    private String[] arr_from_str(String string){
        if(!string.isEmpty()){
            return string.split(":");
        }
        return new String[0];

    }

}
