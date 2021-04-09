package logic;

public abstract class Entity extends Thread {

    // Attributs de class
    private int posx, posy;
    private char symbol;

    // Constructeurs
    public Entity(int posx, int posy) {
        setPosX(posx);
        setPosY(posy);
    }

    // Méthode run de Entity extends Thread
    public void run() {

    }

    // Get Attributs
    public int getPosX() {
        return this.posx;
    }

    public int getPosY() {
        return this.posy;
    }

    public char getSymbol() {
        return this.symbol;
    }

    // Set Attributs
    public void setPosX(int posx) {
        this.posx = posx;
    }

    public void setPosY(int posy) {
        this.posy = posy;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    // Méthode de déplacement
    public void up() {
        setPosY(this.getPosY()+1);
    }

    public void down() {
        setPosY(this.getPosY()-1);
    }

    public void right() {
        setPosY(this.getPosX()+1);
    }

    public void left() {
        setPosY(this.getPosX()-1);
    }

    //Collision Ennemy
    public boolean isColliding(int posx, int posy){
        if(this.getPosX() == posx && this.getPosY() == posy){
            return true;
        }
        return false;
    } 

    public Entity entityCollision(Entity entity){
        if(isColliding(entity.getPosX(), entity.getPosY())){
            return entity;
        }
        return null;
    }
}
