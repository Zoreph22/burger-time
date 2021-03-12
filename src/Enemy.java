public class Enemy extends Thread
{
    private String enemyType; //egg, sausage et plus si affinité 


    public Enemy(String enemyType)
    {
        setEnemyType(enemyType);
    }

    private void setEnemyType(String type)
    {
        this.enemyType = type;
    }



}