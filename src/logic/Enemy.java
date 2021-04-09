package logic;

public class Enemy extends Entity {

    // Attributs

    // Constructeurs
    public Enemy(int posx, int posy, char symbol) {
        super(posx, posx);
        setSymbol(symbol);
    }

    // Méthode run de Enemy
    public void run() {

    }

    /*
     * private void setCharEnemy(String type) { if (type.equals("egg"))
     * this.enemyChar = 'E'; else if (type.equals("sauc")) this.enemyChar = 'S';
     * 
     * }
     * 
     * public int distMin() {
     * 
     * return 0; }
     * 
     * public boolean IA(int posPlayerX, int posPlayerY, Level l) { if (posPlayerX +
     * 1 == this.getPosX() || posPlayerX - 1 == this.getPosX() || posPlayerY + 1 ==
     * this.getPosY() || posPlayerY - 1 == this.getPosY()) return true; else return
     * false;
     * 
     * }
     */
}
