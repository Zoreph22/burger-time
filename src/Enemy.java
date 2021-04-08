public class Enemy
{
    private char enemyChar;
    private String type;
    private int posX;
    private int posY;

    public Enemy(String type, int x, int y)
    {
        initEnemy(type, x, y);
        
    }
    

    private void initEnemy(String type, int X, int Y)
    {
        setType(type);
        setCharEnemy(type);
        setPosXY(X, Y);
    }


    private void setType(String type) {this.type = type;}

    public void setPosX(int X) {this.posX = X;}
    public void setPosY(int Y) {this.posY = Y;}
    public void setPosXY(int X, int Y) {this.posX = X; this.posY = Y;}

    public int getPosX() {return this.posX;}
    public int getPosY() {return this.posY;}

    private void setCharEnemy(String type) 
    {
        if(type.equals("egg"))
            this.enemyChar = 'E';
        else if(type.equals("sauc"))
            this.enemyChar = 'S';

    }

    public int distMin()
    {
        
        return 0;
    }

    public boolean IA(int posPlayerX, int posPlayerY, Level l)
    {
        if(posPlayerX+1 == this.getPosX() || posPlayerX-1 == this.getPosX() || posPlayerY+1 == this.getPosY() || posPlayerY-1 == this.getPosY())
            return true;
        else
            return false;
        
    }









}
