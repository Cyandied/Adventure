package UIelems;

import javafx.scene.control.TextArea;

public class OutputBox extends TextArea {

    public int pos_width;
    public int pos_height;

    public int width;
    public int height;
    public String tutoroll = "View inventory using (i) or (I) \nInteract with world using the letters and numbers displayed in the scene. \nCombine items and location by inputting the location id (ex 100) with the item id (ex 01) with the item first (ex 01100). \nCombine two items (ex 01 and 02) by combining the two ids, the smallest number first (ex 0102).\nMaps (capital letter ids) cannot be combined with any items or locations.";

    public OutputBox(int screen_width, int screen_height, int padding) {

        width = screen_width - 2*padding;
        height = screen_height/20 * 3;
        pos_width = 10;
        pos_height = screen_height - screen_height/20 - height - 2*padding;

        this.setEditable(false);
        this.setWrapText(true);
        this.setText(tutoroll);

        set_up();
    }

    private void set_up(){
        this.relocate(pos_width,pos_height);
        this.setPrefSize(width,height);
    }

    public void set_text(String text){
        this.setText(text);
    }

    private String get_text() {
        return this.getText();
    }

    public void append_text(String text) {
        this.appendText("\n" + text);
    }

}
