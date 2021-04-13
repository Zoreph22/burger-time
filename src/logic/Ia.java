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

            if (peutSeDeplacer(id.getPosi() - 1, id.getPosj()) && temp[id.getPosi() - 1][id.getPosj()] == 0) {
                temp[id.getPosi() - 1][id.getPosj()] = nb;
                file.add(new Position(id.getPosi() - 1, id.getPosj()));
            }
            if (peutSeDeplacer(id.getPosi() + 1, id.getPosj()) && temp[id.getPosi() + 1][id.getPosj()] == 0) {
                temp[id.getPosi() + 1][id.getPosj()] = nb;
                file.add(new Position(id.getPosi() + 1, id.getPosj()));
            }
            if (peutSeDeplacer(id.getPosi(), id.getPosj() - 1) && temp[id.getPosi()][id.getPosj() - 1] == 0) {
                temp[id.getPosi()][id.getPosj() - 1] = nb;
                file.add(new Position(id.getPosi(), id.getPosj() - 1));
            }
            if (peutSeDeplacer(id.getPosi(), id.getPosj() + 1) && temp[id.getPosi()][id.getPosj() + 1] == 0) {
                temp[id.getPosi()][id.getPosj() + 1] = nb;
                file.add(new Position(id.getPosi(), id.getPosj() + 1));
            }

            if (!file.isEmpty() && temp[file.peek().getPosi()][file.peek().getPosj()] == nb) {
                nb++;
            }
        }

        /*for (int i = 0; i < temp.length; i++) {
            RawConsoleInput.println();

            for (int j = 0; j < temp[0].length; j++) {
                RawConsoleInput.print("-" + temp[i][j]);
            }
        }

        RawConsoleInput.println("-------------------------------------------");*/

        if (peutSeDeplacer(player.getPosition().getPosi() - 1, player.getPosition().getPosj()) && temp[player.getPosition().getPosi() - 1][player.getPosition().getPosj()] < retour) {
            retour = temp[player.getPosition().getPosi() - 1][player.getPosition().getPosj()];
        }
        if (peutSeDeplacer(player.getPosition().getPosi() + 1, player.getPosition().getPosj()) && temp[player.getPosition().getPosi() + 1][player.getPosition().getPosj()] < retour) {
            retour = temp[player.getPosition().getPosi() + 1][player.getPosition().getPosj()];
        }
        if (peutSeDeplacer(player.getPosition().getPosi(), player.getPosition().getPosj() - 1) && temp[player.getPosition().getPosi()][player.getPosition().getPosj() - 1] < retour) {
            retour = temp[player.getPosition().getPosi()][player.getPosition().getPosj() - 1];
        }
        if (peutSeDeplacer(player.getPosition().getPosi(), player.getPosition().getPosj() + 1) && temp[player.getPosition().getPosi()][player.getPosition().getPosj() + 1] < retour) {
            retour = temp[player.getPosition().getPosi()][player.getPosition().getPosj() + 1];
        }
        return retour;
    }

    public boolean peutSeDeplacer(int i, int j) {
        if (level.getCellules()[i][j].isSol() && level.getCellules()[i + 1][j].isLadder()) {
            return true;
        }

        if (level.getCellules()[i][j].isSol()
                && (level.getCellules()[i + 1][j].isLadder())
                && (level.getCellules()[i - 1][j].isLadder() || level.getCellules()[i - 1][j].isAir())) {
            return true;
        }
        
        if (level.getCellules()[i][j].isAir() && level.getCellules()[i+1][j].isSol()) {
            return true;
        }

        if (level.getCellules()[i][j].isLadder()) {
            return true;
        }

        if (level.getCellules()[i][j].isBord()) {
            return false;
        }

        return false;
    }

    // Déplacement
    public void changerPosition() {
        int dist;
        String direction = "";
        int distanceMin = 999999;
        for (int i = 0; i < level.getPlayers().playersSize(); i++) {
            int posi = enemy.getPosition().getPosi();
            int posj = enemy.getPosition().getPosj();
            
            if (peutSeDeplacer(posi-1, posj)) {
                dist = distMin(new Position(posi-1, posj), level.getPlayers().getPlayers(i));
                if (dist < distanceMin) {
                    distanceMin = dist;
                    direction = "up";
                }
            }
            if (peutSeDeplacer(posi+1, posj)) {
                dist = distMin(new Position(posi+1, posj), level.getPlayers().getPlayers(i));
                if (dist < distanceMin) {
                    distanceMin = dist;
                    direction = "down";
                }
            }
            if (peutSeDeplacer(posi, posj-1)) {
                dist = distMin(new Position(posi, posj-1), level.getPlayers().getPlayers(i));
                if (dist < distanceMin) {
                    distanceMin = dist;
                    direction = "left";
                }
            }
            if (peutSeDeplacer(posi, posj+1)) {
                dist = distMin(new Position(posi, posj+1), level.getPlayers().getPlayers(i));
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
