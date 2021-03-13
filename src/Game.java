import java.util.*;

public class Game
{


    Scanner sc = new Scanner(System.in);


    Thread player = new Thread(new Player());
    

    Level level = new Level(0);
    Enemy enemies[];

    private boolean testGame;



    public Game()
    {
        initGame();
        runGame();
        
    }

    

    private void initGame()
    {
       enemies = new Enemy[5];
       player.start();
    }

    private void runGame()
    {
        testGame = true;
     
        while(testGame)
        {
            level.affiche();

            testGame = false;
        }
        
    }


}