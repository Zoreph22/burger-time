public class Level {
    
    private char ladder = '#';
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

    private void setLevel1()
    {
        System.out.println("x");
    }



}
