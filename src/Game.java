import java.util.*;
public class Game
{


    Scanner sc = new Scanner(System.in);
    Player player;

    //Thread player;
    
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
        //player = new Thread(new Player(level.widthLenghtLevel()-2, level.heightLenghtLevel()));
        //player.start();
        player = new Player(level.widthLenghtLevel()-3, level.heightLenghtLevel());
        level.changechar(player.getIdPlayerX(), player.getIdPlayerY()-1, player.getCharPlayer());
    }

    private void runGame()
    {
        testGame = true;
        System.out.print(level.widthLenghtLevel()+"        "+ (level.heightLenghtLevel()));
        System.out.print(level.getChar(player.getIdPlayerX(), player.getIdPlayerY()-2));
        while(testGame)
        {
            level.affiche();
            String test = sc.nextLine();
            if(test.equals("z") && level.getChar(player.getIdPlayerX(), player.getIdPlayerY()-2) == '#')
            {
                level.changechar(player.getIdPlayerX(), player.getIdPlayerY()-1, '_');
                level.changechar(player.getIdPlayerX(), player.getIdPlayerY()-2, player.getCharPlayer());
            }

            level.affiche();
            

            testGame = false;
        }
        
    }


}