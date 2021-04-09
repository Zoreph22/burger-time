package logic;

public class Enemys {

    // Attributs
    private Enemy[] enemys;

    // Get
    public Enemy[] getEnemys() {
        return this.enemys;
    }

    // Set
    public void setEnemys(int id, Enemy enemy) {
        this.enemys[id] = enemy;
    }

    // Constructeurs
    public Enemys(int size) {
        enemys = new Enemy[size];
    }
}
