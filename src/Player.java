import java.util.*;

public class Player implements Runnable
{
    private int idPlayerX, idPlayerY;
    private int playerHP;
    private int playerScore;
    private int nbPepper;
    private char cook = 'C';
    
    Scanner sc;

    Player(int X, int Y)
    { 
        setIdPlayer(X, Y);
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

    public int getIdPlayerX() {return this.idPlayerX;}
    public int getIdPlayerY() {return this.idPlayerY;}
    public int getPlayerHP() {return this.playerHP;}
    public int getPlayerScore() {return this.playerScore;}
    public int getNbPepper() {return this.nbPepper;}
    public char getCharPlayer() {return this.cook;}

    public void setIdPlayer(int idX, int idY) {this.idPlayerX = idX; this.idPlayerY = idY;}
    public void setPlayerHP(int hp) {this.playerHP = hp;}
    public void setPlayerScore(int score) {this.playerScore = score;}
    public void setNbPepper(int pepper) {this.nbPepper = pepper;}



}
