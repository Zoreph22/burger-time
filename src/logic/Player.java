package logic;

import java.io.*;
import java.awt.*;

public class Player extends Entity {

    // Attributs

    // Constructeurs
    public Player(int posx, int posy, char symbol) {
        super(posx, posx);
        setSymbol(symbol);
    }

    // Méthode run de Player
    public void run() {

    }

    //Collision Ingredient
    /*public Ingredient ingredientCollision(Ingredient ingredient){
        if(isColliding(ingredient.getPosX(), ingredient.getPosY());){
            return ingredient;
        }
        return null;
    }*/


    

    /*
     * public int playerMove() { int tmp = -1; try { tmp = System.in.read(); } catch
     * (IOException e) { System.out.println("Fait un effort"); }
     * 
     * return tmp; }
     */
}
