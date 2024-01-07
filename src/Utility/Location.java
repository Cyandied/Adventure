package Utility;

public class Location {

    final private String id;
    public String name;
    public String desc;
    public boolean cleared;
    public boolean special;
    public String special_location_ref;

    public Location(String id, String name, String desc,boolean cleared, boolean special, String special_location_ref) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.cleared = cleared;
        this.special = special;
        this.special_location_ref = special_location_ref;
    }

}
