package logic;

public class Game {
    Level level = new Level(0);
    Assiettes assiette = new Assiettes(4);
    // Player player = new Player(0,0);

    public Game() {
        level.print();
        assiette.print();

    }
}