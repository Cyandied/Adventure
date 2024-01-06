package UIelems;

import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.io.FileInputStream;

public class Arrows {
    public int screen_w;
    public int screen_h;

    public int width;
    private int padding;

    private View view;

    Pane up = new Pane();
    Pane down = new Pane();
    Pane left = new Pane();
    Pane right = new Pane();

    public ImageView up_img = new ImageView();
    private Text up_label = new Text("A");

    public ImageView down_img = new ImageView();
    private Text down_label = new Text("A");
    public ImageView left_img = new ImageView();
    private Text left_label = new Text("A");
    public ImageView right_img = new ImageView();
    private Text right_label = new Text("A");

    public Arrows(View view) {

        width = view.width/10;
        screen_w = view.width;
        screen_h = view.height;
        this.view = view;
        this.padding = 10;

        set_up_panes();
    }

    private void set_up_panes(){
        set_up_img_txt();
        set_up_pane("up",up);
        set_up_pane("down",down);
        set_up_pane("left",left);
        set_up_pane("right",up);
    }

    private void set_up_pane(String direction, Pane arrow){
        int arr_pos_w = 0;
        int arr_pos_h = 0;
        switch (direction){
            case "up":
                arr_pos_w = (screen_w - width)/2;
                arr_pos_h = view.pos_height;
                arrow.getChildren().addAll(up_img,up_label);
                break;
            case "down":
                arr_pos_w = (screen_w - width)/2;
                arr_pos_h = view.pos_height + screen_h - width;
                arrow.getChildren().addAll(down_img,down_label);
                break;
            case "left":
                arr_pos_w = padding;
                arr_pos_h = (screen_h - width)/2 + view.pos_height;
                arrow.getChildren().addAll(left_img,left_label);
                break;
            case "right":
                arr_pos_w = view.pos_width + screen_w - width;
                arr_pos_h = (screen_h - width)/2 + view.pos_height;
                arrow.getChildren().addAll(right_img,right_label);
                break;
        }
        arrow.relocate(arr_pos_w,arr_pos_h);
    }

    public void place_arrow(String direction, String label){
        switch (direction){
            case "up":
                up_img.setVisible(true);
                up_label.setText(label);
                break;
            case "down":
                down_img.setVisible(true);
                down_label.setText(label);
                break;
            case "left":
                left_img.setVisible(true);
                left_label.setText(label);
                break;
            case "right":
                right_img.setVisible(true);
                right_label.setText(label);
                break;
        }
    }

    public void prime(){
        up_img.setVisible(false);
        down_img.setVisible(false);
        left_img.setVisible(false);
        right_img.setVisible(false);

        up_label.setText("");
        down_label.setText("");
        left_label.setText("");
        right_label.setText("");
    }

    private void set_up_img_txt(){
        set_up_img(up_img);
        set_up_img(down_img);
        set_up_img(left_img);
        set_up_img(right_img);

        set_up_text(up_label);
        set_up_text(down_label);
        set_up_text(left_label);
        set_up_text(right_label);

        //prime();

        set_img("up", up_img);
        set_img("down", down_img);
        set_img("left", left_img);
        set_img("right", right_img);
    }

    public void set_up_img(ImageView arrow){
        arrow.setFitWidth(width);
        arrow.setFitHeight(width);
    }

    public  void set_up_text(Text txt){
        txt.setTextOrigin(VPos.CENTER);
    }

    public void set_img(String direction, ImageView arrow){
        try {
            String bg_src = "src/res/arrows/";
            FileInputStream in_stream = new FileInputStream(bg_src + direction + ".png");
            Image image = new Image(in_stream);

            arrow.setImage(image);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}
