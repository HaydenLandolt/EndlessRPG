
public class Town extends Location
{
    private boolean hasGeneralMerchant;
    private boolean hasBlacksmith;
    private boolean hasArmourer;
    private boolean hasAlchemist;
    private boolean hasTavern;
    
    /**
     * Constructor for objects of class Town
     */
    public Town(boolean merchant, boolean blacksmith, boolean armourer, boolean alchemist, boolean tavern)
    {
        super(TerrainType.TOWN);
        hasGeneralMerchant = merchant;
        hasBlacksmith = blacksmith;
        hasArmourer = armourer;
        hasAlchemist = alchemist;
        hasTavern = tavern;
    }
    
    public boolean getHasGeneralMerchant(){
        return hasGeneralMerchant;
    }
    
    public boolean gethasBlacksmith(){
     return hasBlacksmith;   
    }
    
    public boolean getHasArmourer(){
        return hasArmourer;
    }
    
    public boolean gethasTavern(){
        return hasTavern;
    }

}
