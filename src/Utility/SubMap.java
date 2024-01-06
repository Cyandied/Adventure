package Utility;

import Utility.Location;

import java.util.Arrays;

public class SubMap {

    public String id;
    public String[] location_ids;
    public String [] submap_ids;
    public String name;
    public String desc;
    public String background_image;

    public SubMap(String id, String[] location_ids, String[] submap_ids, String name, String desc, String background_image) {
        this.id = id;
        this.location_ids = location_ids;
        this.submap_ids = submap_ids;
        this.name = name;
        this.desc = desc;
        this.background_image = background_image;
    }

    public String to_string(){
        return id + "\n" + Arrays.toString(location_ids) + "\n" + Arrays.toString(submap_ids) + "\n" +name + "\n" +desc + "\n" +background_image;
    }
}
