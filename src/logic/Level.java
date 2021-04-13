package logic;

import java.util.Random;
import java.util.UUID;
import utils.RawConsoleInput;

public class Level {

    // Attributs Levels
    private char levels[][];
    private int levelCourant;

    // Attributs de symboles
    private char ladder = '▒';
    private char air = ' ';
    private char floor = '▬';
    private char bord_haut = '▄';
    private char bord_bas = '▀';
    private char bord_droit = '▐';
    private char bord_gauche = '▌';

    // Instances
    private Cellule cellules[][];
    private Assiettes assiettes;
    private Players players;
    private Enemys enemys;

    // Constructeurs
    public Level(int level) {
        levelCourant = level;
        players = new Players();
        enemys = new Enemys();
        initLevels();
    }

    // Get
    public char getChar(int i, int j) {
        return levels[i][j];
    }

    public int getHeight() {
        return levels.length;
    }

    public int getWidth() {
        return levels[0].length;
    }

    public Assiettes getAssiettes() {
        return this.assiettes;
    }

    public Players getPlayers() {
        return this.players;
    }

    public Enemys getEnemys() {
        return this.enemys;
    }

    public Cellule[][] getCellules() {
        return this.cellules;
    }

    // Symbol Get
    public char getLadder() {
        return this.ladder;
    }

    public char getAir() {
        return this.air;
    }

    public char getFloor() {
        return this.floor;
    }

    public char getBordHaut() {
        return this.bord_haut;
    }

    public char getBordBas() {
        return this.bord_bas;
    }

    public char getBordDroite() {
        return this.bord_droit;
    }

    public char getBordGauche() {
        return this.bord_gauche;
    }

    // Set
    public void setChar(int i, int j, char c) {
        levels[i][j] = c;
    }

    // Méthode d'affichage du level.
    public void print() {
        RawConsoleInput.clear();
        for (int i = 0; i < getHeight() + 2; i++) {
            for (int j = 0; j < getWidth() + 2; j++) {
                RawConsoleInput.print("" + cellules[i][j]);
            }
            RawConsoleInput.println();
        }
    }

    private void initLevels() {

        switch (this.levelCourant) {
            case 0:
                levels = new char[][]{
                    {air, air, air, air, air, air, air, air, air, air, air, air, air, air, air, air, air},
                    {floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor,
                        floor, floor, floor},
                    {ladder, air, air, air, ladder, air, ladder, air, ladder, air, air, air, ladder, air, air, air,
                        ladder},
                    {ladder, air, air, air, ladder, air, ladder, air, ladder, air, air, air, ladder, air, air, air,
                        ladder},
                    {floor, floor, floor, floor, floor, air, ladder, air, floor, floor, floor, floor, floor, floor,
                        floor, floor, floor},
                    {air, air, ladder, air, ladder, air, ladder, air, ladder, air, ladder, air, ladder, air, air, air,
                        ladder},
                    {air, air, ladder, air, floor, floor, floor, floor, floor, air, ladder, air, ladder, air, air, air,
                        ladder},
                    {air, air, ladder, air, ladder, air, air, air, ladder, air, ladder, air, ladder, air, air, air,
                        ladder},
                    {floor, floor, floor, floor, floor, air, air, air, ladder, air, ladder, air, ladder, floor, floor,
                        floor, floor},
                    {ladder, air, ladder, air, ladder, floor, floor, floor, floor, floor, floor, floor, floor, air,
                        ladder, air, air},
                    {ladder, air, ladder, air, ladder, air, air, air, ladder, air, air, air, ladder, air, ladder, air,
                        air},
                    {ladder, air, ladder, air, ladder, air, air, air, ladder, air, air, air, floor, floor, floor,
                        floor, floor},
                    {ladder, air, ladder, air, ladder, air, air, air, ladder, air, air, air, ladder, air, ladder, air,
                        ladder},
                    {floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, air,
                        ladder, air, ladder, air, ladder},
                    {ladder, air, air, air, ladder, air, air, air, ladder, air, air, air, ladder, air, ladder, air,
                        ladder, air, ladder},
                    {floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor,
                        floor, floor, floor},};

                cellules = new Cellule[this.getHeight() + 2][this.getWidth() + 2];
                for (int i = 0; i < this.getHeight() + 2; i++) {
                    for (int j = 0; j < this.getWidth() + 2; j++) {
                        if (i == 0) {
                            cellules[i][j] = new Cellule(bord_haut);
                        }
                        if (i > 0 && j == this.getWidth() + 1) {
                            cellules[i][j] = new Cellule(bord_droit);
                        }
                        if (i > 0 && j == 0) {
                            cellules[i][j] = new Cellule(bord_gauche);
                        }
                        if (i == this.getHeight() + 1) {
                            cellules[i][j] = new Cellule(bord_bas);
                        }
                        if ((i > 0 && i < this.getHeight() + 1) && (j > 0 && j < this.getWidth() + 1)) {
                            cellules[i][j] = new Cellule(levels[i - 1][j - 1]);
                        }
                    }
                }

                /*this.getPlayers().setPlayers(0, new Player(this.getHeight() - 1, 9, "C", cellules, this));
                cellules[this.getHeight() - 1][9].setEntity(this.getPlayers().getPlayers(0));*/
 /*Enemy enemy = new Enemy(4, 13, "E", cellules, this);
                this.getEnemys().setEnemys(enemy);
                cellules[enemy.getPosition().getPosi()][enemy.getPosition().getPosj()].setEntity(enemy);
                
                Enemy enemy2 = new Enemy(1, 1, "E", cellules, this);
                this.getEnemys().setEnemys(enemy2);
                cellules[enemy2.getPosition().getPosi()][enemy2.getPosition().getPosj()].setEntity(enemy2);*/
                this.assiettes = new Assiettes(4);

                Ingredient burger1[] = new Ingredient[4];
                Ingredient burger2[] = new Ingredient[4];
                Ingredient burger3[] = new Ingredient[4];
                Ingredient burger4[] = new Ingredient[4];

                Burger b1 = new Burger(burger1);
                Burger b2 = new Burger(burger2);
                Burger b3 = new Burger(burger3);
                Burger b4 = new Burger(burger4);

                Burger assiettes[] = {b1, b2, b3, b4};

                burger1[0] = new Ingredient("P1", 4, 3, cellules, b1, 4, getAssiettes());
                burger1[1] = new Ingredient("S", 8, 3, cellules, b1, 3, getAssiettes());
                burger1[2] = new Ingredient("V", 13, 3, cellules, b1, 2, getAssiettes());
                burger1[3] = new Ingredient("P2", this.getHeight() - 1, 3, cellules, b1, 1, getAssiettes());

                burger2[0] = new Ingredient("P1", 6, 7, cellules, b2, 4, getAssiettes());
                burger2[1] = new Ingredient("S", 9, 7, cellules, b2, 3, getAssiettes());
                burger2[2] = new Ingredient("V", 13, 7, cellules, b2, 2, getAssiettes());
                burger2[3] = new Ingredient("P2", this.getHeight() - 1, 7, cellules, b2, 1, getAssiettes());

                burger3[0] = new Ingredient("P1", 4, 11, cellules, b3, 4, getAssiettes());
                burger3[1] = new Ingredient("S", 9, 11, cellules, b3, 3, getAssiettes());
                burger3[2] = new Ingredient("V", 13, 11, cellules, b3, 2, getAssiettes());
                burger3[3] = new Ingredient("P2", this.getHeight() - 1, 11, cellules, b3, 1, getAssiettes());

                burger4[0] = new Ingredient("P1", 4, 15, cellules, b4, 4, getAssiettes());
                burger4[1] = new Ingredient("S", 8, 15, cellules, b4, 3, getAssiettes());
                burger4[2] = new Ingredient("V", 11, 15, cellules, b4, 2, getAssiettes());
                burger4[3] = new Ingredient("P2", this.getHeight() - 1, 15, cellules, b4, 1, getAssiettes());

                // this.assiettes = new Assiettes(4);
                // this.getAssiettes().setAssiette(0, new Burger(burger1));
                this.assiettes.setAssiette(0, b1);
                this.assiettes.setAssiette(1, b2);
                this.assiettes.setAssiette(2, b3);
                this.assiettes.setAssiette(3, b4);
                break;
        }
    }

    /**
     * Peut-on se déplacer sur une position ?
     *
     * @param i Position i
     * @param j Position j
     * @return true si on peut se déplacer
     */
    public boolean peutSeDeplacer(int i, int j) {
        if (this.cellules[i][j].isSol() && this.cellules[i + 1][j].isLadder()) {
            return true;
        }

        if (this.cellules[i][j].isSol()
                && (this.cellules[i + 1][j].isLadder())
                && (this.cellules[i - 1][j].isLadder() || this.cellules[i - 1][j].isAir())) {
            return true;
        }

        if (this.cellules[i][j].isAir() && this.cellules[i + 1][j].isSol()) {
            return true;
        }

        if (this.cellules[i][j].isLadder()) {
            return true;
        }

        if (this.cellules[i][j].isBord()) {
            return false;
        }

        return false;
    }

    /**
     * Peut-on spawner sur une position ?
     *
     * @param i Position i
     * @param j Position j
     * @return true si on peut spawner ici
     */
    public boolean peutSpawner(int i, int j) {
        if (this.cellules[i][j].getEntity() != null) {
            return false;
        }

        if (this.cellules[i][j].isAir() && !this.cellules[i + 1][j].isSol()) {
            return false;
        }

        if (this.cellules[i][j].isSol()) {
            return false;
        }

        if (this.cellules[i][j].isBord()) {
            return false;
        }

        return true;
    }

    /**
     * Générer une position spawnable
     *
     * @return Position spawnable
     */
    public Position generateSpawnablePos() {
        Random r = new Random();
        int i;
        int j;

        do {
            i = r.nextInt(this.cellules.length);
            j = r.nextInt(this.cellules[0].length);
        } while (!this.peutSpawner(i, j));

        return new Position(i, j);
    }

    /**
     * [SERVEUR] Spawn un autre joueur sur le niveau
     *
     * @param id Identifiant joueur
     * @param symbole Symbole joueur
     * @return Joueur spawné
     */
    public Player spawnJoueur(UUID id, String symbole) {
        Position pos = this.generateSpawnablePos();
        Player player = new Player(pos.getPosi(), pos.getPosj(), symbole, this.cellules, this);
        this.getPlayers().setPlayer(id, player);
        this.cellules[pos.getPosi()][pos.getPosj()].setEntity(player);
        return player;
    }

    /**
     * [CLIENT] Spawn son joueur
     *
     * @param id Identifiant de son joueur
     * @param pos Position de spawn
     */
    public void spawnMonJoueur(UUID id, Position pos) {
        Player player = new Player(pos.getPosi(), pos.getPosj(), "C", this.cellules, this);
        this.getPlayers().setPlayer(id, player);
        this.cellules[pos.getPosi()][pos.getPosj()].setEntity(player);
        this.print();
        this.getAssiettes().print();
    }

    /**
     * [CLIENT] Spawn un autre joueur
     *
     * @param id Identifiant client
     * @param pos Position de spawn
     * @param symbole Symbole joueur
     */
    public void spawnOtherPlayer(UUID id, Position pos, String symbole) {
        Player player = new Player(pos.getPosi(), pos.getPosj(), symbole, this.cellules, this);
        this.getPlayers().setPlayer(id, player);
        this.cellules[pos.getPosi()][pos.getPosj()].setEntity(player);
        this.print();
        this.getAssiettes().print();
    }

    /**
     * [SERVEUR] Spawn un ennemi
     *
     * @return Ennemi spawné
     */
    public Enemy spawnEnemy() {
        Position pos = this.generateSpawnablePos();
        Enemy enemy = new Enemy(pos.getPosi(), pos.getPosj(), "E", this.cellules, this);
        this.getEnemys().setEnemys(enemy);
        this.cellules[pos.getPosi()][pos.getPosj()].setEntity(enemy);
        return enemy;
    }

    /**
     * [CLIENT] Spawn un ennemi
     *
     * @param id Identifiant de l'ennemi
     * @param pos Position de spawn
     */
    public void spawnEnemyAt(UUID id, Position pos) {
        Enemy enemy = new Enemy(pos.getPosi(), pos.getPosj(), "E", this.cellules, this);
        enemy.setUuid(id);
        this.getEnemys().setEnemys(enemy);
        this.cellules[pos.getPosi()][pos.getPosj()].setEntity(enemy);
        this.print();
        this.getAssiettes().print();
    }
}
