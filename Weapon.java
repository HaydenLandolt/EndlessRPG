
public class Weapon extends Item
{
    private int medAttackLow;
    private int medAttackHigh;
    private int largeAttackLow;
    private int largeAttackHigh;
    private boolean isDoubleHanded;
    private boolean isEquipped;
    
    /**
     * Constructor for objects of class Weapon
     */
    public Weapon(String name, int weight, int value, int medLow, int medHigh, int largeLow, int largeHigh, boolean isDoubleHanded)
    {
        super(name, 0, weight, value);
        medAttackLow = medLow;
        medAttackHigh = medHigh;
        largeAttackLow = largeLow;
        largeAttackHigh = largeHigh;
        this.isDoubleHanded = isDoubleHanded;
    }
    
    public int getMedAttackLow(){
     return medAttackLow;   
    }
    
    public int getMedAttackHigh(){
     return medAttackHigh;   
    }
    
    public int getLargeAttackLow(){
     return largeAttackLow;   
    }
    
    public int getLargeAttackHigh(){
     return largeAttackHigh;   
    }
    
    public boolean getIsDoubleHanded(){
     return isDoubleHanded;   
    }
    
    public boolean getIsEquipped(){
      return isEquipped;
    }
    
    public void equipWeapon(){
        isEquipped = true; 
    }
    
    public void unequipWeapon(){
        isEquipped = false; 
    }
    
    @Override
    public String toString(){
        String toReturn = getName(); 
        if(isEquipped)
        toReturn += " -- Equipped";
        
        toReturn +="\nWeight: " + getWeight() + " lbs\nValue: " + getValue() + 
                        " coins\nSmall/Medium Damage: " + medAttackLow + "-" + medAttackHigh + 
                        "\nLarge Damage: " + largeAttackLow + "-" + largeAttackHigh + "\n";
                        
        if (!isDoubleHanded)
            toReturn += "Single-handed weapon";
        else
           toReturn += "Double-handed weapon";   
           
           return toReturn;
    }
}
