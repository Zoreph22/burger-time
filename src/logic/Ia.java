package logic;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;
import utils.RawConsoleInput;

public class Ia extends Thread {

    // Attributs
    private Entity enemy;
    private Level level;

    // Constructeurs
    public Ia(Entity entity, Level level) {
        this.enemy = entity;
        this.level = level;
    }

    @Override
    public void run() {
        while (true) {
            try {
                changerPosition();
                this.level.print();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }

    // Get
    public Entity getEntity() {
        return this.enemy;
    }

    public Level getLevel() {
        return this.level;
    }

    // Set
    public void setEntity(Enemy enemy) {
        this.enemy = enemy;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    // Distance minimal
    public int distMin(Position enemy, Player player) {
        Position id = enemy;
        int nb = 2;
        int retour = 99999;
        int temp[][] = new int[level.getCellules().length][level.getCellules()[0].length];
        for (int i = 0; i < level.getCellules().length; i++) {
            for (int j = 0; j < level.getCellules()[0].length; j++) {
                temp[i][j] = 0;
            }
        }

        temp[id.getPosi()][id.getPosj()] = 1;
        ConcurrentLinkedQueue<Position> file = new ConcurrentLinkedQueue<>();
        file.add(id);
        while (!file.isEmpty()) {
            id = file.poll();

            if (level.peutSeDeplacer(id.getPosi() - 1, id.getPosj()) && temp[id.getPosi() - 1][id.getPosj()] == 0) {
                temp[id.getPosi() - 1][id.getPosj()] = nb;
                file.add(new Position(id.getPosi() - 1, id.getPosj()));
            }
            if (level.peutSeDeplacer(id.getPosi() + 1, id.getPosj()) && temp[id.getPosi() + 1][id.getPosj()] == 0) {
                temp[id.getPosi() + 1][id.getPosj()] = nb;
                file.add(new Position(id.getPosi() + 1, id.getPosj()));
            }
            if (level.peutSeDeplacer(id.getPosi(), id.getPosj() - 1) && temp[id.getPosi()][id.getPosj() - 1] == 0) {
                temp[id.getPosi()][id.getPosj() - 1] = nb;
                file.add(new Position(id.getPosi(), id.getPosj() - 1));
            }
            if (level.peutSeDeplacer(id.getPosi(), id.getPosj() + 1) && temp[id.getPosi()][id.getPosj() + 1] == 0) {
                temp[id.getPosi()][id.getPosj() + 1] = nb;
                file.add(new Position(id.getPosi(), id.getPosj() + 1));
            }

            if (!file.isEmpty() && temp[file.peek().getPosi()][file.peek().getPosj()] == nb) {
                nb++;
            }
        }

        if (level.peutSeDeplacer(player.getPosition().getPosi() - 1, player.getPosition().getPosj()) && temp[player.getPosition().getPosi() - 1][player.getPosition().getPosj()] < retour) {
            retour = temp[player.getPosition().getPosi() - 1][player.getPosition().getPosj()];
        }
        if (level.peutSeDeplacer(player.getPosition().getPosi() + 1, player.getPosition().getPosj()) && temp[player.getPosition().getPosi() + 1][player.getPosition().getPosj()] < retour) {
            retour = temp[player.getPosition().getPosi() + 1][player.getPosition().getPosj()];
        }
        if (level.peutSeDeplacer(player.getPosition().getPosi(), player.getPosition().getPosj() - 1) && temp[player.getPosition().getPosi()][player.getPosition().getPosj() - 1] < retour) {
            retour = temp[player.getPosition().getPosi()][player.getPosition().getPosj() - 1];
        }
        if (level.peutSeDeplacer(player.getPosition().getPosi(), player.getPosition().getPosj() + 1) && temp[player.getPosition().getPosi()][player.getPosition().getPosj() + 1] < retour) {
            retour = temp[player.getPosition().getPosi()][player.getPosition().getPosj() + 1];
        }
        return retour;
    }

    // Déplacement
    public void changerPosition() {
        int dist;
        String direction = "";
        int distanceMin = 999999;
        for (Player player : this.getLevel().getPlayers().getPlayers()) {
            int posi = enemy.getPosition().getPosi();
            int posj = enemy.getPosition().getPosj();

            if (level.peutSeDeplacer(posi - 1, posj)) {
                dist = distMin(new Position(posi - 1, posj), player);
                if (dist < distanceMin) {
                    distanceMin = dist;
                    direction = "up";
                }
            }
            if (level.peutSeDeplacer(posi + 1, posj)) {
                dist = distMin(new Position(posi + 1, posj), player);
                if (dist < distanceMin) {
                    distanceMin = dist;
                    direction = "down";
                }
            }
            if (level.peutSeDeplacer(posi, posj - 1)) {
                dist = distMin(new Position(posi, posj - 1), player);
                if (dist < distanceMin) {
                    distanceMin = dist;
                    direction = "left";
                }
            }
            if (level.peutSeDeplacer(posi, posj + 1)) {
                dist = distMin(new Position(posi, posj + 1), player);
                if (dist < distanceMin) {
                    distanceMin = dist;
                    direction = "right";
                }
            }
        }

        switch (direction) {
            case "up":
                enemy.up(level.getCellules());
                break;
            case "down":
                enemy.down(level.getCellules());
                break;
            case "left":
                enemy.left(level.getCellules());
                break;
            case "right":
                enemy.right(level.getCellules());
                break;
        }
    }
}
