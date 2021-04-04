public class Level {
    
    private char ladder = '#';
    private char air = ' ';
    private char floor = '_';
    private char levels[][][];
    private int levelCourant;

    public Level(int level)
    {
        if(level == 0)
            levelCourant = 0;
        setLevels();
    }

    public char read(int i, int j) {return levels[levelCourant][i][j];}
    public int widthLenghtLevel() {return levels[levelCourant].length;}
    public int heightLenghtLevel() {return levels[levelCourant][0].length;}

    public void affiche()
    {
        for(int i = 0; i < heightLenghtLevel(); i++)
        {
            for(int j = 0; j < widthLenghtLevel(); j++)
                System.out.print(read(i,j));
            System.out.println();
        }
    }

    public char getChar(int X, int Y)
    {
        return levels[0][Y][X];
    }
   
    public void setChar(int X, int Y, char c)
    {
        levels[0][Y][X] = c;
    }


    private void setLevels()
    {
        levels = new char[1][][];

        levels[0] = new char[][]
        {
            {air,air,air,air,air,air,air,air,air,air,air,air,air,air,air,air,air},
            {air,air,air,air,air,air,air,air,air,air,air,air,air,air,air,air,air},
            {floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor},
            {ladder,air,air,air,ladder,air,ladder,air,ladder,air,air,air,ladder,air,air,air,ladder},
            {ladder,air,air,air,ladder,air,ladder,air,ladder,air,air,air,ladder,air,air,air,ladder},
            {floor,floor,floor,floor,floor,air,ladder,air,floor,floor,floor,floor,floor,floor,floor,floor,floor},
            {air,air,ladder,air,ladder,air,ladder,air,ladder,air,ladder,air,ladder,air,air,air,ladder},
            {air,air,ladder,air,floor,floor,floor,floor,floor,air,ladder,air,ladder,air,air,air,ladder},
            {air,air,ladder,air,ladder,air,air,air,ladder,air,ladder,air,ladder,air,air,air,ladder},
            {floor,floor,floor,floor,floor,air,air,air,ladder,air,ladder,air,floor,floor,floor,floor,floor},
            {ladder,air,ladder,air,ladder,floor,floor,floor,floor,floor,floor,floor,floor,air,ladder,air,air},
            {ladder,air,ladder,air,ladder,air,air,air,ladder,air,air,air,ladder,air,ladder,air,air},
            {ladder,air,ladder,air,ladder,air,air,air,ladder,air,air,air,floor,floor,floor,floor,floor},
            {ladder,air,ladder,air,ladder,air,air,air,ladder,air,air,air,ladder,air,ladder,air,ladder},
            {floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,air,ladder,air,ladder,air,ladder},
            {ladder,air,air,air,ladder,air,air,air,ladder,air,air,air,ladder,air,ladder,air,ladder,air,ladder},
            {floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor,floor},
        };
    }  
}
