package Utility;

public class Location {

    final public String id;
    public String name;
    public String desc;
    public boolean cleared;
    public boolean special;
    public String[] pos_xy;
    public String special_location_ref;

    public Location(String id, String name, String desc,boolean cleared, boolean special, String special_location_ref, String[] pos_xy) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.cleared = cleared;
        this.special = special;
        this.special_location_ref = special_location_ref;
        this.pos_xy = pos_xy;
    }

}
