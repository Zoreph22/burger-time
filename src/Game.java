import java.util.*;

public class Game
{


    Scanner sc = new Scanner(System.in);


    Thread player = new Thread(new Player());
    
    Thread enemies[];
    Enemy tmpenemy[];

    Level level = new Level(0);
    

    private boolean testGame;



    public Game()
    {
        initGame();
        runGame();
        
    }

    

    private void initGame()
    {
        tmpenemy = new Enemy[5];
        tmpenemy[0] = new Enemy("egg");
        tmpenemy[1] = new Enemy("egg");
        tmpenemy[2] = new Enemy("egg");
        tmpenemy[3] = new Enemy("egg");
        tmpenemy[4] = new Enemy("egg");

        for(int i = 0; i < tmpenemy.length; i++)
        {
            enemies[i] = new Thread(tmpenemy[i]);
            enemies[i].start();
        }


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