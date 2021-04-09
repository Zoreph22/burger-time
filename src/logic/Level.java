package logic;

public class Level {

	private char ladder = '▒';
	private char air = ' ';
	private char floor = '▬';
	private char levels[][];
	private int levelCourant;

	// private Player player;
	// private Enemy enemy;
	private Assiettes assiette;

	public Level(int level) {
		levelCourant = level;
		initLevels();
	}

	public char getChar(int i, int j) {
		return levels[i][j];
	}

	public void setChar(int i, int j, char c) {
		levels[i][j] = c;
	}

	public int heightLenghtLevel() {
		return levels.length;
	}

	public int widthLenghtLevel() {
		return levels[0].length;
	}

	public void print() {
		for (int i = 0; i < heightLenghtLevel() + 3; i++) {
			System.out.print("▄");
		}
		System.out.println();
		for (int i = 0; i < heightLenghtLevel(); i++) {
			System.out.print("▌");

			for (int j = 0; j < widthLenghtLevel(); j++)
				System.out.print(getChar(i, j));

			System.out.print("▐");
			System.out.println();
		}
		for (int i = 0; i < heightLenghtLevel() + 3; i++) {
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
