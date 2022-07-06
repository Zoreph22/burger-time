package logic;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class Enemys {

    // Attributs
    private HashMap<UUID, Enemy> enemys;

    // Get
    public Collection<Enemy> getEnemys() {
        return this.enemys.values();
    }

    public Enemy getEnemy(UUID id) {
        return this.enemys.get(id);
    }

    // Set
    public void setEnemys(Enemy enemy) {
        this.enemys.put(enemy.getUuid(), enemy);
    }

    // Constructeurs
    public Enemys() {
        this.enemys = new HashMap<>();
    }

    /**
     * Démarrer les IA des ennemis
     */
    public void startIAs() {
        for (Enemy enemy : this.enemys.values()) {
            enemy.getIa().start();
        }
    }

    /**
     * Stopper les IA des ennemis
     */
    public void stopIAs() {
        for (Enemy enemy : this.enemys.values()) {
            enemy.getIa().arreter();
        }
    }
}
