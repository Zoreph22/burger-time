package logic;

import java.io.IOException;

import client.RawConsoleInput;

public class BurgerTime {
    public static void main(String[] args) {
        Game game = new Game();

        //27 = Echap Code qui permet de ne plus se soucier de la touche entrer.
        while (true) {
            try {
                int x = RawConsoleInput.read(true);
                System.out.println(x);
                if (x == 27) {
                    System.exit(0);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

}