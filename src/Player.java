public class Player extends Entities
{
    
    private char cook = 'c';

    public Player(String type, int x, int y)
    {
        super(type,x,y);
    }


    public char getCharPlayer() {return this.cook;}



}
