import java.util.Vector;

public class Player extends GameCharacter
{
    private Location currentLocation;
    private Vector<Item> inventory;
    private int currentWeight;
    private int maxWeight;
    private int coins;
    
    private int level = 1;
    
    Weapon activeWeapon;
    
    private CharacterClass characterClass;
    
    private Armour headgear;
    private Armour body;
    private Armour sheild;
    
    private int arrows;
    private int bolts;
    
    public void lootArea(){
        if(currentLocation instanceof Room || currentLocation instanceof World){
        Vector<Item> loot;
        if(currentLocation instanceof World){
        loot = ((World)currentLocation).getItems();
        for(int i = 0; i < loot.size(); i++){
         addItem(loot.get(i));   
        }
        ((World)currentLocation).lootRoom();
    }
        else{
        loot = ((Room)currentLocation).getItems();
        for(int i = 0; i < loot.size(); i++){
         addItem(loot.get(i));   
        }
        ((Room)currentLocation).lootRoom();
    }
        
    }
    }
    
    public void addItem(Item item){
        if(item.getWeight() + getCurrentWeight() <= getMaxWeight()){
        inventory.add(item);
        addWeight(item.getWeight());
    }
    }
    
    public void addWeight(int weight){
        currentWeight += weight;
    }
    
    public void removeWeight(int weight){
        currentWeight -= weight;
    }
    
    public void equipWeapon(Weapon weapon){
        if((weapon.getIsDoubleHanded() && sheild == null) || !weapon.getIsDoubleHanded()){
        
        if(activeWeapon != null){
         unequipWeapon();   
        }
        activeWeapon = weapon;
        weapon.equipWeapon();
        setMedAttack(weapon.getMedAttackLow(), weapon.getMedAttackHigh());
        setLargeAttack(weapon.getLargeAttackLow(), weapon.getLargeAttackHigh());
    }
    }
    
    public void unequipWeapon(){
        if(activeWeapon != null){
         activeWeapon.unequipWeapon();
         activeWeapon = null;        
        }
        setMedAttack(1,2);
        setLargeAttack(1,2);
    }
    
    public void equipArmour(Armour armour){
        
    }
    
    public void move(boolean enterDungeon){
        currentLocation = LocationGenerator.nextLocation(currentLocation, enterDungeon);
    }
    
    public void getMoney(int value){
        coins += value;
    }
    
    public void spendMoney(int value){
        coins -= value;
    }
    
    /**
     * Constructor for objects of class Player
     */
    public Player(CharacterSize size, String name, CharacterClass charClass, int health, int encumbrance)
    {
        super(size, name, health);  
        currentWeight = 0;
        maxWeight = encumbrance;
        characterClass = charClass;
        setMedAttack(1,2);
        setLargeAttack(1,2);
        setDefence(6);
        
        inventory = new Vector<Item>();
        
        //set up player starting area
        Vector<Item> areaItems = new Vector<Item>();
        areaItems.add(WeaponDatabase.getWeaponFromDatabase(5));
        Vector<GameCharacter> areaCharacters = new Vector<GameCharacter>();
        currentLocation = new Room(false, areaItems, areaCharacters);
        
        //actual starting location --> currentLocation = new World(TerrainType.GRASSLANDS, false, areaItems, areaCharacters);
    }

    @Override
    public void addXP(int amount){
        super.addXP(amount);
        if(getXP() % 100 > level && getXP() > 100){
         level++;
         increaseHealthStat(6);
         healAll();
        }
    }
    
    public Location getCurrentLocation(){
        return currentLocation;
    }
    
    public Vector<Item> getInventory(){
     return inventory;   
    }
    
    public CharacterClass getCharacterClass(){
        return characterClass;
    }
    
    public int getCurrentWeight(){
     return currentWeight;   
    }
    
    public int getMaxWeight(){
     return maxWeight;   
    }
    
    public int getCoins(){
        return coins;
    }
    
    public int getLevel(){
        return level;
    }
    
    public void showInventory(){
        for (int i = 0; i < inventory.size(); i++){
         System.out.print((i+1) + ". " + inventory.get(i).getName());
         if(inventory.get(i) instanceof Weapon && ((Weapon)inventory.get(i)).getIsEquipped()){
             System.out.print(" -- Equipped");
            }
         System.out.print("\n");
        }
    }
    
    @Override
    public String toString(){
     return "--------------------\n" + getName() + " Lvl." + level + " " + characterClass + "\n--------------------"
                        + "\nLocation: " + getCurrentLocation().getTerrain() + "\nXP: " + getXP() +
                        "\nCoins: " + getCoins() + "\nHealth: " + getCurHealth() + "/" + getMaxHealth() + 
                        "\nDefence: " + getDefence() + "    Damage: " + getMedAttackLow() + "-" + getMedAttackHigh() + "(S/M) " +
                        getLargeAttackLow() + "-" + getLargeAttackHigh() + "(L)";                        
    }
}
