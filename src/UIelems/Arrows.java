package UIelems;

import javafx.geometry.VPos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;

import java.awt.*;
import java.io.FileInputStream;

public class Arrows {
    public int screen_w;
    public int screen_h;

    public int width;
    public int height;
    private int padding;

    private View view;

    StackPane up = new StackPane();
    StackPane down = new StackPane();
    StackPane left = new StackPane();
    StackPane right = new StackPane();

    public ImageView up_img = new ImageView();
    private Text up_label = new Text("A");

    public ImageView down_img = new ImageView();
    private Text down_label = new Text("A");
    public ImageView left_img = new ImageView();
    private Text left_label = new Text("A");
    public ImageView right_img = new ImageView();
    private Text right_label = new Text("A");

    public Arrows(View view) {

        width = view.width/7;
        height = width/2;
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
        set_up_pane("right",right);
    }

    private void set_up_pane(String direction, StackPane arrow){
        int arr_pos_w = 0;
        int arr_pos_h = 0;
        switch (direction){
            case "up":
                arr_pos_w = (screen_w - width)/2;
                arr_pos_h = view.pos_height;
                arrow.getChildren().addAll(up_img,up_label);
                arrow.resize(width,height);
                break;
            case "down":
                arr_pos_w = (screen_w - width)/2;
                arr_pos_h = view.pos_height + screen_h - height;
                arrow.getChildren().addAll(down_img,down_label);
                arrow.resize(width,height);
                break;
            case "left":
                arr_pos_w = padding;
                arr_pos_h = (screen_h - width)/2 + view.pos_height;
                arrow.getChildren().addAll(left_img,left_label);
                arrow.resize(height,width);
                break;
            case "right":
                arr_pos_w = view.pos_width + screen_w - height;
                arr_pos_h = (screen_h - width)/2 + view.pos_height;
                arrow.getChildren().addAll(right_img,right_label);
                arrow.resize(height,width);
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
        set_up_img(up_img, false);
        set_up_img(down_img, false);
        set_up_img(left_img, true);
        set_up_img(right_img,true);

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

    public void set_up_img(ImageView arrow, Boolean flip){
        if(flip){
            arrow.setFitWidth(height);
            arrow.setFitHeight(width);
        }
        else {
            arrow.setFitWidth(width);
            arrow.setFitHeight(height);
        }
    }

    public  void set_up_text(Text txt){
        txt.setFont(new Font("Corbel bold",30));
        txt.setBoundsType(TextBoundsType.VISUAL);
        txt.setTextAlignment(TextAlignment.CENTER);
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
