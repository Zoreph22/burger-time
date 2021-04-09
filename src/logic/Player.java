package logic;

import java.io.*;
import java.awt.*;

public class Player extends Thread
{
    
    private char cook = 'c';
    private int posX;
    private int posY;


    public Player(int x, int y)
    {
        setPosXY(x, y);
    }

    public void run()
    {
        
        playerMove();
    }


    public char getCharPlayer() {return this.cook;}


    public int playerMove()
    {
        int tmp = -1;
        try{
            tmp =  System.in.read();
        }
        catch (IOException e)
        {
            System.out.println("Fait un effort");
        }
        
        return tmp;
    }


    public void setPosX(int X) {this.posX = X;}
    public void setPosY(int Y) {this.posY = Y;}
    public void setPosXY(int X, int Y) {this.posX = X; this.posY = Y;}

    public int getPosX() {return this.posX;}
    public int getPosY() {return this.posY;}


}
