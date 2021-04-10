package logic;

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
	public Level(int level, int nbassiette) {
		levelCourant = level;
		this.assiettes = new Assiettes(nbassiette);
		initLevels();
	}

	// Get
	public char getChar(int y, int x) {
		return levels[y][x];
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

	// Set
	public void setChar(int y, int x, char c) {
		levels[y][x] = c;
	}

	// Méthode d'affichage du level.
	public void print() {
		// for (int i = 0; i < getHeight() + 3; i++) {
		// 	System.out.print("▄");
		// }
		// System.out.println();
		// for (int i = 0; i < getHeight(); i++) {
		// 	System.out.print("▌");

		// 	for (int j = 0; j < getWidth(); j++)
		// 		System.out.print(getChar(i, j));

		// 	System.out.print("▐");
		// 	System.out.println();
		// }
		// for (int i = 0; i < getHeight() + 3; i++) {
		// 	System.out.print("▀");
		// }
		// System.out.println();

		for (int i = 0; i < getHeight()+2; i++) {
			for (int j = 0; j < getWidth()+2; j++){
				System.out.print(cellules[i][j]);
			}
			System.out.println();
		}
	}

	private void initLevels() {

		switch (this.levelCourant) {
		default:
			levels = new char[][] {
					{ air, air, air, air, air, air, air, air, air, air, air, air, air, air, air, air, air },
					{ floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor,
							floor, floor, floor },
					{ ladder, air, air, air, ladder, air, ladder, air, ladder, air, air, air, ladder, air, air, air,
							ladder },
					{ ladder, air, air, air, ladder, air, ladder, air, ladder, air, air, air, ladder, air, air, air,
							ladder },
					{ floor, floor, floor, floor, floor, air, ladder, air, floor, floor, floor, floor, floor, floor,
							floor, floor, floor },
					{ air, air, ladder, air, ladder, air, ladder, air, ladder, air, ladder, air, ladder, air, air, air,
							ladder },
					{ air, air, ladder, air, floor, floor, floor, floor, floor, air, ladder, air, ladder, air, air, air,
							ladder },
					{ air, air, ladder, air, ladder, air, air, air, ladder, air, ladder, air, ladder, air, air, air,
							ladder },
					{ floor, floor, floor, floor, floor, air, air, air, ladder, air, ladder, air, floor, floor, floor,
							floor, floor },
					{ ladder, air, ladder, air, ladder, floor, floor, floor, floor, floor, floor, floor, floor, air,
							ladder, air, air },
					{ ladder, air, ladder, air, ladder, air, air, air, ladder, air, air, air, ladder, air, ladder, air,
							air },
					{ ladder, air, ladder, air, ladder, air, air, air, ladder, air, air, air, floor, floor, floor,
							floor, floor },
					{ ladder, air, ladder, air, ladder, air, air, air, ladder, air, air, air, ladder, air, ladder, air,
							ladder },
					{ floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, air,
							ladder, air, ladder, air, ladder },
					{ ladder, air, air, air, ladder, air, air, air, ladder, air, air, air, ladder, air, ladder, air,
							ladder, air, ladder },
					{ floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor, floor,
							floor, floor, floor }, };

			cellules = new Cellule[this.getHeight()+2][this.getWidth()+2];
			for (int i = 0; i < this.getHeight()+2; i++) {
				for (int j = 0; j < this.getWidth()+2; j++) {
					if(i == 0){
						cellules[i][j] = new Cellule(bord_haut);
					}
					if(i < this.getHeight()+1 && j == this.getWidth()+1){
						cellules[i][j] = new Cellule(bord_droit);
					}
					if(i > 0 && j == 0){
						cellules[i][j] = new Cellule(bord_gauche);
					}
					if(i == this.getHeight()+1){
						cellules[i][j] = new Cellule(bord_bas);
					}
					if((i > 0 && i < this.getHeight()+1) && (j > 0 && j < this.getWidth()+1))
						cellules[i][j] = new Cellule(levels[i-1][j-1]);
				}
			}
			// cellules[0][this.getWidth()+1] = new Cellule(bord_haut);
			// cellules[5][5].setEntity(new Player(5, 5, 'C'));
			break;
		}

	}
}
