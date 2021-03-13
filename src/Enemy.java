import java.util.*;

public class Enemy implements Runnable
{
    private String enemyType; //egg, sausage et plus si affinité 


    Enemy(String enemyType)
    {
        setEnemyType(enemyType);
    }

    public void run()
    {
        setEnemyType(enemyType);
    }

    private void setEnemyType(String type)
    {
        this.enemyType = type;
    }



}