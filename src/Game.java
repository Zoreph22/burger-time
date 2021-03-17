import java.util.*;

import jdk.jshell.execution.Util;

public class Game implements Runnable
{


    Scanner sc = new Scanner(System.in);


    Thread player = new Thread(new Player());
    
    Thread enemies[];
    Enemy tmpenemy[];

    Level level = new Level(-1);
    
  //  private Thread utility = new Thread(new Utility());

    private boolean testGame;



    public Game()
    {
        initGame();
        
    }

    
    

    private void initGame()
    {
       /* tmpenemy = new Enemy[5];
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
*/

        player.start();

    }

    public void run()
    {
        testGame = true;
        int tmp = 10;
        /*while(tmp >= 0)
        {   
            synchronized(this)
            {
                level.affiche();
                notify();
            }
            System.out.println(player.getId());

            
            
            
            tmp--;
        }*/
        level.createLevel();
    }

}