package logic;

import serveur.ServeurSocket;
import serveur.phases.PhaseGagner;
import serveur.phases.PhasePartie;
import serveur.phases.PhasePerdre;
import utils.RawConsoleInput;

public class Enemy extends Entity {

    // Attributs
    private Ia ia;
    private Level level;

    // Constructeurs
    public Enemy(int posi, int posj, String symbol, Cellule[][] cellules, Level level) {
        super(posi, posj, cellules, level);
        setSymbol(symbol);
        this.level = level;
        this.ia = new Ia(this, level);
    }

    // Get
    public Ia getIa() {
        return this.ia;
    }

    // Set
    public void setIa(Ia ia) {
        this.ia = ia;
    }

    @Override
    public void collisionEffect(Entity entity) {
        if (entity.getType().equals("Player")) {

            entity.setVie(entity.getVie() - 1);

            if (entity.getVie() <= 0) {
                entity.setMort(true);

                Position pos = entity.getPosition();
                entity.getCellules()[pos.getPosi()][pos.getPosj()].setEntity(null);

                if (ServeurSocket.isServeur()) {
                    // Si tous les joueurs sont mort, écran de fin
                    boolean tousMorts = true;

                    for (Player player : level.getPlayers().getPlayers()) {
                        if (!player.getMort()) {
                            tousMorts = false;
                            break;
                        }
                    }

                    if (tousMorts) {
                        ((PhasePartie) ServeurSocket.getInstance().getGame().getPhaseCourante()).getLevel().getEnemys().stopIAs();
                        ServeurSocket.getInstance().getGame().setPhaseCourante(new PhasePerdre());
                        ServeurSocket.getInstance().broadcast("SERVER_LEVEL_LOSE");
                    } else {
                        ServeurSocket.getInstance().broadcast("SERVER_PLAYER_DIED|" + entity.getUuid());
                    }
                }

            }
        }
    }

    @Override
    public String getType() {
        return "Enemy";
    }

}
