package UIelems;

import Main.InteractionManager;
import Main.Locations;
import Main.Player;
import Main.SubMaps;
import Utility.SQLiteJDBC;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class InputBox extends TextField{

    public int pos_width;
    public int pos_height;

    public int width;
    public int height;
    private final OutputBox out;
    private final InteractionManager im;
    private final Player p;
    private final SubMaps sm;
    private boolean first_input = true;
    private Locations locations;



    public InputBox(int screen_width, int screen_height, int padding, OutputBox out, View view, Arrows arrows, LocationLabels ll) {
        width = screen_width - 2*padding;
        height = screen_height/20;
        pos_width = 10;
        pos_height = screen_height - height - padding;
        this.out = out;
        SQLiteJDBC db = new SQLiteJDBC();
        locations = new Locations(db);
        p = new Player();
        sm = new SubMaps(view, db,arrows,ll,locations);
        im = new InteractionManager(p, sm, db,locations,ll);
        sm.set_up();
        set_up();
    }

    private String get_text(){
        return this.getText();
    }

    private void set_text(String text) {
        this.setText(text);
    }

    private void set_up(){
        this.relocate(pos_width,pos_height);
        this.setPrefSize(width,height);

        this.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if(ke.getCode().equals(KeyCode.ENTER) && !get_text().isEmpty()) {
                    String text = get_text();
                    if (first_input){
                        out.set_text("");
                        first_input = false;
                    }
                    if(text.equalsIgnoreCase("I")){
                        out.append_text("Player inventory:");
                        out.append_text(p.inven_to_string());
                    }
                    else if(sm.check_map_avaible(text.toUpperCase())){
                        out.append_text(sm.switch_map(text.toUpperCase()));
                    }
                    else {
                        out.append_text(text);
                        String return_text = im.request_interaction(text);
                        out.append_text(return_text);
                    }
                    set_text("");
                }
            }
        });
    }

}
