import java.util.Vector;

public class World extends Location
{
    private boolean hasDungeonEnterance;
    private Vector<GameCharacter> enemies;
    private Vector<Item> items;

    /**
     * Constructor for objects of class World
     */
    public World(TerrainType terrain, boolean enterance, Vector<Item> items, Vector<GameCharacter> enemies)
    {
        super(terrain);
        hasDungeonEnterance = enterance;
        this.enemies = enemies;
        this.items = items;
    }

    public void lootRoom(){
        items.clear();
    }
    
    public void dropItem(Item item){
        items.add(item);
    }
    
    public void killEnemies(){
        enemies.clear();
    }
    
    public boolean getHasDungeonEnterance(){
        return hasDungeonEnterance;
    }
    
    public Vector<GameCharacter> getEnemies(){
     return enemies;   
    }
    
    public Vector<Item> getItems(){
     return items;   
    }
}
