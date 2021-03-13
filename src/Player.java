import java.util.*;

public class Player implements Runnable
{
    private int idPlayer;
    private int playerHP;
    private int playerScore;
    private int nbPepper;
    private char cook = 'C';
    
    Scanner sc;

    Player()
    {
        setPlayerHP(4);
    }

    public void run()
    {
        //Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        setNbPepper(4);
        sc = new Scanner(System.in);
    }




    public boolean testMove(int dir)
    {
        
        return true;
    }

    public int getIdPlayer() {return this.idPlayer;}
    public int getPlayerHP() {return this.playerHP;}
    public int getPlayerScore() {return this.playerScore;}
    public int getNbPepper() {return this.nbPepper;}

    public void setIdPlayer(int id) {this.idPlayer = id;}
    public void setPlayerHP(int hp) {this.playerHP = hp;}
    public void setPlayerScore(int score) {this.playerScore = score;}
    public void setNbPepper(int pepper) {this.nbPepper = pepper;}



}
