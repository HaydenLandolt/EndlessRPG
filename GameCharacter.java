import java.util.Random;
public class GameCharacter
{
    private Random random = new Random();
    private String name;
    private int xp = 0;
    private int curHealth;
    private int maxHealth;
    private int defence;
    private int medAttackLow;
    private int medAttackHigh;
    private int largeAttackLow;
    private int largeAttackHigh;
    private CharacterSize size;

    public void takeDamage(int damage){
        curHealth -= damage;
    }
    
    public boolean checkIsDead(){
        return curHealth <= 0;
    }
    
    public boolean checkIfHit(int toHit){
        return toHit > defence;
    }

    public void heal(int amount){
        curHealth = Math.min(maxHealth, curHealth + amount);
    }
    
    public void healAll(){
        curHealth = maxHealth;
    }
    
    public void increaseHealthStat(int amount){
        if(curHealth == maxHealth){
            maxHealth += amount;
            curHealth += amount;
        }
        else{
         maxHealth += amount;   
        }
    }
    
    public int attackMed(){
        int damageRange = medAttackHigh - medAttackLow + 1;
        damageRange = Math.abs(damageRange);
        return random.nextInt(damageRange) + medAttackLow;
    }
    
    public int attackLarge(){
        int damageRange = largeAttackHigh - largeAttackLow + 1;
        damageRange = Math.abs(damageRange);
        return random.nextInt(damageRange) + largeAttackLow;
    }
    
    /**
     * Constructor for objects of class GameCharacter
     */
    public GameCharacter(CharacterSize size, String name, int health)
    {
        this.size = size;
        this.name = name;
        curHealth = health;
        maxHealth = health;
    }
    
    public String getName(){
        return name;
    }
    
    public int getCurHealth(){
        return curHealth;
    }
    
    public int getMaxHealth(){
        return maxHealth;
    }
    
    public void setDefence(int defence){
        this.defence = defence;
    }
    
    public int getXP(){
        return xp;
    }
    
    public void addXP(int amount){
        xp += amount;
    }
    
    public int getDefence(){
     return defence;   
    }
   
    public void addDefence(int defence){
        this.defence += defence;
    }
    
    public CharacterSize getCharacterSize(){
     return size;   
    }
    
    public void setMedAttack(int low, int high){
        medAttackLow = low;
        medAttackHigh = high;
    }
    
    public void setLargeAttack(int low, int high){
        largeAttackLow = low;
        largeAttackHigh = high;
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
}
