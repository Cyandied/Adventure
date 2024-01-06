package Main;

import Utility.Item;

import java.util.HashMap;

public class Player {

    public int injury = 0;
    public String name;
    public HashMap<String, Item> inventory;
    public HashMap<String, String> checks;

    public Player(){
        name = "player";
        inventory = new HashMap<String, Item>();
        checks = new HashMap<String, String>();
    }

    public String inven_to_string() {
        if(inventory.isEmpty()){
            return "No items in inventory";
        }
        StringBuilder text = new StringBuilder();
        Item[] items = inventory.values().toArray(new Item[inventory.size()]);
        for(int i = 0; i < items.length; i++){
            Item item = items[i];
            text.append(item.id).append(" ").append(item.name).append(" | ").append(item.desc);
            if(i + 1 < items.length){
                text.append("\n");
            }
        }
        return text.toString();
    }

}
