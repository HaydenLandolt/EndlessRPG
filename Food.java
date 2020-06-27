
public class Food extends Item
{
    
    /**
     * Constructor for objects of class Food
     */
    public Food(String name, int health, int wieght, int value)
    {
        super(name, health, wieght, value);
    }

    @Override
    public String toString(){
        return getName() + "\nWeight: " + getWeight() 
                        + " lbs\nValue: " + getValue() + "coins" + 
                        "\nHeals " + getHealth() + " points of health";
                        
    }
}
