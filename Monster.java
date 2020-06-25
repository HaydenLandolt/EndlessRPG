
public class Monster extends GameCharacter
{  

    /**
     * Constructor for objects of class Monster
     */
    public Monster(CharacterSize size, String name, int health, int defence, int xp, int medLow, int medHigh, int largeLow, int largeHigh)
    {
        super(size, name, health);
        setDefence(defence);
        addXP(xp);
        setMedAttack(medLow, medHigh);
        setLargeAttack(largeLow, largeHigh);
    }


}
