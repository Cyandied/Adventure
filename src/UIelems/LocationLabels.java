package UIelems;

import Main.Locations;
import Utility.Location;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.HashMap;

public class LocationLabels extends StackPane {

    private int x_grid;
    private int y_grid;

    public LocationLabels(View view){
        this.relocate(view.pos_width, view.pos_height);
        this.setPrefSize(view.width, view.height);
        this.setAlignment(Pos.BASELINE_LEFT);
        x_grid = view.width / 10;
        y_grid = view.height / 10;
    }

    private void prime(){
        this.getChildren().removeAll();
    }

    public void draw_locations (HashMap<String, Location> locations, String[] ids){
        prime();
        for(String id : ids){
            try {
                Location location = locations.get(id);
                Text label = new Text(location.id);
                int x = Integer.parseInt(location.pos_xy[0]);
                int y = Integer.parseInt(location.pos_xy[1]);
                if(location.special){
                    label.setText(location.special_location_ref);
                }
                label.setTextAlignment(TextAlignment.CENTER);
                label.setTextOrigin(VPos.CENTER);
                label.setFont(new Font("Verdana bold",30));
                label.setFill(Paint.valueOf("black"));
                label.setStroke(Paint.valueOf("white"));
                label.setStrokeWidth(3);
                label.setStrokeType(StrokeType.OUTSIDE);
                this.getChildren().add(label);
                this.setMargin(label, new Insets(y*y_grid,0,0,x*x_grid));
            }
            catch (Exception e){
                System.err.println(e);
            }
        }
    }


}
