package logic;

public class Game {
    Level level = new Level(0,4);
    // Player player = new Player(0,0);

    public Game() {
        level.print();
        level.getAssiettes().print();
    }
}