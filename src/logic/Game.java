package logic;

import java.util.Scanner;

import client.RawConsoleInput;

public class Game {
    Level level = new Level(0,4);
    // Player player = new Player(0,0);

    public Game() {
        int i=0;
        while(i != 200)
        {           
            level.print();
            level.getAssiettes().print();
            Scanner s = new Scanner(System.in);
            String x = s.nextLine();
            switch (x) {
                case "z":
                    level.getPlayers().getPlayers(0).up(level.getCellules());
                    break;
                case "q":
                    level.getPlayers().getPlayers(0).left(level.getCellules());
                    break;
                case "s":
                    level.getPlayers().getPlayers(0).down(level.getCellules());
                    break;
                case "d":
                    level.getPlayers().getPlayers(0).right(level.getCellules());
                    break;
            }
            i++;
            RawConsoleInput.clear();
        }
    }
}