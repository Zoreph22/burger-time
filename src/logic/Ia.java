package logic;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Ia extends Thread {

    // Attributs
    private Entity enemy;
    private Level level;

    // Constructeurs
    public Ia(Entity entity, Level level) {
        this.enemy = entity;
        this.level = level;
    }

    public void run(){
        while(true){
            try {          
                changerPosition();
                Thread.sleep(10000); 
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
    public int distMin(Enemy enemy, Player player) {
        Position id = enemy.getPosition();
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

            if (peutSeDeplacer(id.getPosi() - 1, id.getPosj())) {
                temp[id.getPosi() - 1][id.getPosj()] = nb;
                file.add(new Position(id.getPosi() - 1, id.getPosj()));
            }
            if (peutSeDeplacer(id.getPosi() + 1, id.getPosj())) {
                temp[id.getPosi() + 1][id.getPosj()] = nb;
                file.add(new Position(id.getPosi() + 1, id.getPosj()));
            }
            if (peutSeDeplacer(id.getPosi(), id.getPosj() - 1)) {
                temp[id.getPosi()][id.getPosj() - 1] = nb;
                file.add(new Position(id.getPosi(), id.getPosj() - 1));
            }
            if (peutSeDeplacer(id.getPosi(), id.getPosj() + 1)) {
                temp[id.getPosi()][id.getPosj() + 1] = nb;
                file.add(new Position(id.getPosi(), id.getPosj() + 1));
            }

            if (!file.isEmpty() && temp[file.peek().getPosi()][file.peek().getPosj()] == nb) {
                nb++;
            }
        }

        if (peutSeDeplacer(id.getPosi() - 1, id.getPosj()) && temp[id.getPosi() - 1][id.getPosj()] < retour) {
            retour = temp[id.getPosi() - 1][id.getPosj()];
        }
        if (peutSeDeplacer(id.getPosi() + 1, id.getPosj()) && temp[id.getPosi() + 1][id.getPosj()] < retour) {
            retour = temp[id.getPosi() + 1][id.getPosj()];
        }
        if (peutSeDeplacer(id.getPosi(), id.getPosj() - 1) && temp[id.getPosi()][id.getPosj() - 1] < retour) {
            retour = temp[id.getPosi()][id.getPosj() - 1];
        }
        if (peutSeDeplacer(id.getPosi(), id.getPosj() + 1) && temp[id.getPosi()][id.getPosj() + 1] < retour) {
            retour = temp[id.getPosi()][id.getPosj() + 1];
        }
        return retour;
    }

    public boolean peutSeDeplacer(int i, int j) {
        if (!level.getCellules()[i][j].isBord()) {
            return true;
        }
        if (level.getCellules()[i][j].isLadder() && !level.getCellules()[i][j].isBord()) {
            return true;
        }
        if (!level.getCellules()[i][j].isBord() && level.getCellules()[i+1][j].isSol()) {
            return true;
        }
        return false;
    }

    // Déplacement
    public void changerPosition() {
        int dist;
        String direction = "";
        int distanceMin = 999999;
        for(int i=0;i<level.getPlayers().playersSize();i++){
            if(peutSeDeplacer(enemy.getPosition().getPosi()-1, enemy.getPosition().getPosj())){
                dist = distMin((Enemy) enemy, level.getPlayers().getPlayers(i));
                if(dist < distanceMin){
                    direction = "up";
                }
            }
            if(peutSeDeplacer(enemy.getPosition().getPosi()+1, enemy.getPosition().getPosj())){
                dist = distMin((Enemy) enemy, level.getPlayers().getPlayers(i));
                if(dist < distanceMin){
                    direction = "down";
                }
            }
            if(peutSeDeplacer(enemy.getPosition().getPosi(), enemy.getPosition().getPosj()-1)){
                dist = distMin((Enemy) enemy, level.getPlayers().getPlayers(i));
                if(dist < distanceMin){
                    direction = "left";
                }
            }
            if(peutSeDeplacer(enemy.getPosition().getPosi(), enemy.getPosition().getPosj()+1)){
                dist = distMin((Enemy) enemy, level.getPlayers().getPlayers(i));
                if(dist < distanceMin){
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
