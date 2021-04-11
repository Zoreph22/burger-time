package logic;

/**
 * Classe représentant le jeu
 */
import java.util.Scanner;
//import java.util.logging.Level;

import serveur.phases.Phase;
import utils.RawConsoleInput;

public class Game {

    // Phase courante du jeu
    private Phase phaseCourante;

    Level level;

    /**
     * Modifier la phase courante
     *
     * @param phase Phase
     */
    public void setPhaseCourante(Phase phase) {
        this.phaseCourante = phase;
    }

    /**
     * Retourner la phase courante
     *
     * @return Phase courante
     */
    public Phase getPhaseCourante() {
        return this.phaseCourante;
    }

    public Game() {
        level = new Level(0);
        int i = 0;
        while (i != 200) {
            level.print();
            level.getAssiettes().print();
            Scanner s = new Scanner(System.in);
            String x = s.nextLine();
            switch (x) {
                case "z":
                    level.getPlayers().getPlayers(0).up(level.getCellules());
                    break;
                case "q":
                    level.getPlayers().getPlayers(0).left(level.getCellules());
                    break;
                case "s":
                    level.getPlayers().getPlayers(0).down(level.getCellules());
                    break;
                case "d":
                    level.getPlayers().getPlayers(0).right(level.getCellules());
                    break;
            }
            i++;
            RawConsoleInput.clear();
        }
    }
}
