package UIelems;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GamePanel extends Application {

    final int width = 1000;
    final int height = 700;
    final int padding = 10;
    final private Pane primary_pane = new Pane();
    private OutputBox out;
    private InputBox in;
    private View view;
    private Arrows arrows;

    public void init() {

    }

    public void start(Stage primaryStage) {

        primaryStage.setTitle("Adventure Game");
        primaryStage.setResizable(false);

        out = new OutputBox(width, height, padding);
        view = new View(width, height, padding);
        arrows = new Arrows(view);
        in = new InputBox(width,height,padding,out,view,arrows);

        primary_pane.getChildren().addAll(in,out,view,arrows.up,arrows.down,arrows.left,arrows.right);

        Scene scene = new Scene(primary_pane, width,height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void stop() {

    }

    public void launch_game(){
        launch();
    }

}
