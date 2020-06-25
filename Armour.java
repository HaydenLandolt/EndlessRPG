
public class Armour extends Item
{
    private int defence;
    private ArmourLocation armourLocation;

    /**
     * Constructor for objects of class Armour
     */
    public Armour(String name, int health,int weight,int value, int defence, ArmourLocation armourLoaction)
    {
        super(name, health, weight, value);
        this.defence = defence;
        this.armourLocation = armourLocation;
    }

    public int getDefence(){
        return defence;
    }
    
    public ArmourLocation getArmourLocation(){
        return armourLocation;
    }
}
