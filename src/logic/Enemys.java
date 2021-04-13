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

    // Set
    public void setEnemys(Enemy enemy) {
        this.enemys.put(enemy.getUuid(), enemy);
    }

    // Constructeurs
    public Enemys() {
        this.enemys = new HashMap<>();
    }
}
