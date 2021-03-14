import java.io.*;
import java.util.*;

import javax.xml.crypto.Data;

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
    Scanner saveReader;

    public Level(int level)
    {
        initSave();

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
            File save = new File("data/save"+nbStage);
            nbStage--;
            File save2 = new File("data/save"+nbStage);
            saveReader = new Scanner(save2);
            saveWriter = new PrintWriter(save);
            settingsWriter = new PrintWriter(settings);
            
            settingsWriter.println(nbStage);
            
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public char read(int i, int j) {return levels[levelCourant][i][j];}
    public int widthLenghtLevel() {return levels[levelCourant][0].length;}
    public int heightLenghtLevel() {return levels[levelCourant].length;}

    public void affiche()
    {
        for(int i = 0; i < heightLenghtLevel(); i++)
        {
            for(int j = 0; j < widthLenghtLevel(); j++)
                System.out.print(read(i,j));
                System.out.println();
        }
    }


    public void createLevel()
    {
        
        levels = new char[levelCourant][height][width];
        for(int i = 0; i<height;i++)
        {
            
            affiche();
            String oui = sc.nextLine(); 
            System.out.println("\n\n\n");   
            for(int j = 0; j < width; j++)
            {
                levels[levelCourant][i][j] = oui.charAt(j);
            }
            
            
        } 
        affiche(); 
        String str;
        for(int i = 0; i < height; i++)
        {
            str = "";
            for(int j = 0; j < width; j++)
            {
                if(levels[levelCourant][i][j] == '_')
                    str += "floor";
                else if(levels[levelCourant][i][j] == '#')
                    str += "ladder";
                else if(levels[levelCourant][i][j] == 'S')
                    str += "salad";
                else if(levels[levelCourant][i][j] == 'M')
                    str += "meat";
                else if(levels[levelCourant][i][j] == 'B')
                    str += "bread";
                else if(levels[levelCourant][i][j] == ' ')
                    str += "air";
                    
                str += ",";
            }
            
            saveWriter.println(str);

        }
        settingsWriter.close();
        saveWriter.close();
        settingsReader.close();
        saveReader.close();
    }

    private void setLevels()
    {
        String str[][] = new String[height][width];
        saveReader.useDelimiter(",");

        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                str[i][j] = saveReader.next();
            }
            saveReader.nextLine();
        }

        levels = new char[nbLevels][height][width];

        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                if(str[i][j].equals("floor"))
                    levels[levelCourant][i][j] = floor;
                else if(str[i][j].equals("ladder"))
                    levels[levelCourant][i][j] = ladder;
                else if(str[i][j].equals("salad"))
                    levels[levelCourant][i][j] = salad;
                else if(str[i][j].equals("meat"))
                    levels[levelCourant][i][j] = meat;
                else if(str[i][j].equals("bread"))
                    levels[levelCourant][i][j] = bread;
                else if(str[i][j].equals("air"))
                    levels[levelCourant][i][j] = air;
            }
        }

        settingsWriter.close();
        saveWriter.close();
        settingsReader.close();
        saveReader.close();
    }  
}
