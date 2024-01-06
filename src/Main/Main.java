package Main;

import Utility.SQLiteJDBC;

public class Main {


    public static void main(String[] args) {

        SQLiteJDBC database = new SQLiteJDBC();

        GameControl gc = new GameControl();
        gc.start();
    }

}
