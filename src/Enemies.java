public class Enemies extends Entities
{
    private char enemyChar;

    public Enemies(String type, int x, int y)
    {
        super(type, x, y);
        setCharEnemy(type);
    }

    private void setCharEnemy(String type) 
    {
        if(type.equals("egg"))
            this.enemyChar = 'E';
        else if(type.equals("sauc"))
            this.enemyChar = 'S';

    }

    public int distMin(char[][] grille, int posPlayerX, int posPlayerY)
    {
        
        return 0;
    }

    public boolean IA(int posPlayerX, int posPlayerY)
    {
        if(posPlayerX+1 == this.getPosX() || posPlayerX-1 == this.getPosX() || posPlayerY+1 == this.getPosY() || posPlayerY-1 == this.getPosY())
            return true;
        else
            return false;
        
    }









}
