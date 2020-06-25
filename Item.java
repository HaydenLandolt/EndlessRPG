
public class Item
{
    private String name;    
    private int healthBonus;
    private int weight;
    private int value;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, int health, int weight, int value)
    {
        this.name = name;
        healthBonus = health;    
        this.weight = weight;
        this.value = value;
    }

    public String getName(){
        return name;
    }
    
    public int getHealth(){
     return healthBonus;   
    }
    
    public int getWeight(){
     return weight;   
    }
    
    public int getValue(){
        return value;
    }
}
