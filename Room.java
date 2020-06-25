import java.util.Vector;

public class Room extends Location
{
    private boolean isExit;
    private Vector<Item> items;
    private Vector<GameCharacter> enemies;
    
    public void lootRoom(){
        items.clear();
    }
    
    public void killEnemies(){
        enemies.clear();
    }
    
    /**
     * Constructor for objects of class Room
     */
    public Room(boolean exit, Vector<Item> items, Vector<GameCharacter> enemies)
    {
        super(TerrainType.DUNGEON);
        isExit = exit;
        this.items = items;
        this.enemies = enemies;
    }
    
    public boolean getIsExit(){
     return isExit;   
    }
    
    public Vector<Item> getItems(){
        return items;
    }
    
    public Vector<GameCharacter> getEnemies(){
     return enemies;  
    }
}
