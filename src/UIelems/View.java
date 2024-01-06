package UIelems;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;

public class View extends ImageView {

    public int pos_width;
    public int pos_height;

    public int width;
    public int height;

    public View(int screen_width, int screen_height, int padding) {

        width = screen_width - 2*padding;
        height = screen_height/20 * 15;
        pos_width = 10;
        pos_height = screen_height - screen_height/20 * 4 - height - 2*padding;

        set_up();
    }

    private void set_up(){
        this.relocate(pos_width,pos_height);
        this.setFitWidth(width);
        this.setFitHeight(height);
    }

    public void set_scene(String background_image){
        try {
            String bg_src = "src/res/backgrounds/";
            FileInputStream in_stream = new FileInputStream(bg_src + background_image);
            Image image = new Image(in_stream);

            this.setImage(image);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }



}
