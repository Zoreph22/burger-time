public class Level {
    
    private char ladder = '#';
    private char air = ' ';
    private char bread = 'B';
    private char salad = 'S';
    private char meat = 'M';
    private char flor = '_';
    private char pepper = 'P';

    private char level[][];

    public Level(int level)
    {
        if(level == 0)
            setLevel1();
    }

    public char read(int i, int j) {return level[i][j];}
    public int widthLenghtLevel() {return level[0].length;}
    public int heightLenghtLevel() {return level.length;}

    public void affiche()
    {
        for(int i = 0; i < heightLenghtLevel(); i++)
        {
            for(int j = 0; j < widthLenghtLevel(); j++)
                System.out.print(read(i,j));
                System.out.println();
        }
    }

    private void setLevel1()
    {
        level = new char[][]
        {
            {air,air,air,air,air},
            {flor,flor,flor,flor,flor},
            {air,air,ladder,air,air},
        };
    }



    



}
