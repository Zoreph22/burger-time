package logic;

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
        players = new Players(1);
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

    // Set
    public void setChar(int i, int j, char c) {
        levels[i][j] = c;
    }

    // Méthode d'affichage du level.
    public void print() {
        RawConsoleInput.clear();
        for (int i = 0; i < getHeight() + 2; i++) {
            for (int j = 0; j < getWidth() + 2; j++) {
                System.out.print(cellules[i][j]);
            }
            System.out.println();
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
                    {floor, floor, floor, floor, floor, air, air, air, ladder, air, ladder, air, floor, floor, floor,
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

                this.getPlayers().setPlayers(0, new Player(this.getHeight() - 1, 9, "C", cellules));
                cellules[this.getHeight() - 1][9].setEntity(this.getPlayers().getPlayers(0));

                Ingredient burger1[] = {new Ingredient("P1", 4, 3, cellules), new Ingredient("S", 8, 3, cellules),
                    new Ingredient("V", 13, 3, cellules), new Ingredient("P2", this.getHeight() - 1, 3, cellules)};

                Ingredient burger2[] = {new Ingredient("P1", 6, 7, cellules), new Ingredient("S", 9, 7, cellules),
                    new Ingredient("V", 13, 7, cellules), new Ingredient("P2", this.getHeight() - 1, 7, cellules)};

                Ingredient burger3[] = {new Ingredient("P1", 4, 11, cellules), new Ingredient("S", 9, 11, cellules),
                    new Ingredient("V", 13, 11, cellules), new Ingredient("P2", this.getHeight() - 1, 11, cellules)};

                Ingredient burger4[] = {new Ingredient("P1", 4, 15, cellules), new Ingredient("S", 8, 15, cellules),
                    new Ingredient("V", 13, 15, cellules), new Ingredient("P2", this.getHeight() - 1, 15, cellules)};

                Burger assiettes[] = {new Burger(burger1), new Burger(burger2), new Burger(burger3), new Burger(burger4)};

                // this.assiettes = new Assiettes(4);
                this.assiettes = new Assiettes(4, assiettes);
                // this.getAssiettes().setAssiette(0, new Burger(burger1));
                break;
        }

    }
}
