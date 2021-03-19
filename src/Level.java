import java.io.*;
import java.util.*;



public class Level {
    
    private char ladder = '#';
    private char air = ' ';
    private char bread = 'B';
    private char salad = 'S';
    private char meat = 'M';
    private char floor = '_';
    private char pepper = 'P';
    private int width = 5;
    private int height = 3;
    private char levels[][][];
    private char nbLevels;
    private int nbStage = 0;
    private int levelCourant;
    Scanner sc;
    PrintWriter saveWriter;
    PrintWriter settingsWriter;
    File settings;
    Scanner settingsReader;


    public Level(int level)
    {
        //initSave();
        

        if(level == 0)
            levelCourant = 0;

        setLevels();
        
    }

    private void initSave()
    {
        File data = new File("data");
        File[] f = data.listFiles();
        for(int i = 0; i < f.length-1; i++)
            if(f[i].isFile())
                nbLevels++;

        sc = new Scanner(System.in);
        
        try {
            File settings = new File("data/settings");
            settingsReader = new Scanner(settings);
            nbStage = Integer.parseInt(settingsReader.nextLine());
            nbStage++;
            
            nbStage--;
            File save2 = new File("data/save"+nbStage);
            
            settingsWriter = new PrintWriter(settings);
            
            settingsWriter.println(nbStage);
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
   
    public void changechar(int X, int Y, char c)
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
            {floor,floor,floor,floor,floor,bread,bread,bread,floor,bread,bread,bread,floor,bread,bread,bread,floor},
            {ladder,air,air,air,ladder,air,ladder,air,ladder,air,air,air,ladder,air,air,air,ladder},
            {ladder,air,air,air,ladder,air,ladder,air,ladder,air,air,air,ladder,air,air,air,ladder},
            {floor,bread,bread,bread,floor,air,ladder,air,floor,salad,salad,salad,floor,salad,salad,salad,floor},
            {air,air,ladder,air,ladder,air,ladder,air,ladder,air,ladder,air,ladder,air,air,air,ladder},
            {air,air,ladder,air,floor,floor,floor,floor,floor,air,ladder,air,ladder,air,air,air,ladder},
            {air,air,ladder,air,ladder,air,air,air,ladder,air,ladder,air,ladder,air,air,air,ladder},
            {floor,salad,salad,salad,floor,air,air,air,ladder,air,ladder,air,floor,meat,meat,meat,floor},
            {ladder,air,ladder,air,ladder,salad,salad,salad,floor,meat,meat,meat,floor,air,ladder,air,air},
            {ladder,air,ladder,air,ladder,air,air,air,ladder,air,air,air,ladder,air,ladder,air,air},
            {ladder,air,ladder,air,ladder,air,air,air,ladder,air,air,air,floor,bread,bread,bread,floor},
            {ladder,air,ladder,air,ladder,air,air,air,ladder,air,air,air,ladder,air,ladder,air,ladder},
            {floor,meat,meat,meat,floor,meat,meat,meat,floor,floor,floor,floor,floor,air,ladder,air,ladder,air,ladder},
            {ladder,air,air,air,ladder,air,air,air,ladder,air,air,air,ladder,air,ladder,air,ladder,air,ladder},
            {floor,bread,bread,bread,floor,bread,bread,bread,floor,bread,bread,bread,floor,floor,floor,floor,floor},
        };
    }  
}
