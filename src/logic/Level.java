package logic;

public class Level {

	// Attributs Levels
	private char ladder = '▒';
	private char air = ' ';
	private char floor = '▬';
	private char levels[][];
	private int levelCourant;

	// Instances
	Assiettes assiette;
	Players players;
	Enemys enemys;

	// Constructeurs
	public Level(int level, int nbassiette) {
		levelCourant = level;
		assiette = new Assiettes(nbassiette);
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

	// Set
	public void setChar(int i, int j, char c) {
		levels[i][j] = c;
	}

	// Méthode d'affichage du level.
	public void print() {
		for (int i = 0; i < getHeight() + 3; i++) {
			System.out.print("▄");
		}
		System.out.println();
		for (int i = 0; i < getHeight(); i++) {
			System.out.print("▌");

			for (int j = 0; j < getWidth(); j++)
				System.out.print(getChar(i, j));

			System.out.print("▐");
			System.out.println();
		}
		for (int i = 0; i < getHeight() + 3; i++) {
			System.out.print("▀");
		}
		System.out.println();
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
			break;
		}

	}
}
