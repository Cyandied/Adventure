package Main;

import UIelems.GamePanel;

public class GameControl {

    final private GamePanel gp;

    public GameControl() {
        gp = new GamePanel();
    }

    public void start() {
        gp.launch_game();
    }

}
