import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Vector;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;  
import java.util.Date; 
import javax.swing.JFileChooser; 

/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();
    private static Player player;

    public static void main()
    {
        //welcomeScreen();

        ///*
        Weapon handAxe = WeaponDatabase.getWeaponFromDatabase(3);
        player = new Player(CharacterSize.MEDIUM, "Bruce", CharacterClass.KNIGHT, 30 , 300);
        player.addItem(handAxe);
        player.equipWeapon(handAxe);
       // */
        //handleCharacterSetUp();

        while(true){
            System.out.println("\f" + player + "\n");
            enterLocation(player.getCurrentLocation());
        }
    
    }

    private static void enterLocation(Location location){
        if(location instanceof Room)
            handleRoom((Room)location);
        else if(location instanceof Town)
            handleTown();
        else if(location instanceof World)
            handleWorld((World)location);
    }

    private static void handleRoom(Room location){
        System.out.println("You enter a new room in the dungeon.");
        if(location.getIsExit()){
            System.out.println("You see the dungeon's exit in front of you");
        }

        Vector<GameCharacter> enemies = location.getEnemies();

        boolean hasFled = false;
        boolean hasDied = false;
        if(enemies.size() != 0){
            Monster[] monsters = new Monster[enemies.size()];
            for(int i = 0; i < monsters.length; i++){
                if(enemies.get(i) instanceof Monster)
                    monsters[i] = (Monster)enemies.get(i);
            }

            String encounterText = "Your path is blocked by a " + monsters[0].getName();
            for(int i = 1; i < monsters.length; i++){
                if(i != monsters.length -1)
                    encounterText += ", a " + monsters[i].getName();
                else if (i == monsters.length - 1 && i == 1)
                    encounterText += " and a " + monsters[i].getName();
                else
                    encounterText += ", and a " + monsters[i].getName();
            }
            System.out.println(encounterText + "\n");
            System.out.println("Choose an action:");
            System.out.println("1. Check your equipment");
            System.out.println("2. Open your inventory");
            System.out.println("3. Attack");
            System.out.println("4. Flee");

            String userString;
            while (true){
                userString = input.next();
                if(userString == null){
                    System.out.println("\f" + player + "\n" + encounterText + "\n");
                    System.out.println("Choose an action:");
                    System.out.println("1. Check your equipment");
                    System.out.println("2. Open your inventory");
                    System.out.println("3. Attack");
                    System.out.println("4. Flee");
                }
                else{
                    int userDescision = 0; 
                    try{
                        userDescision = Integer.parseInt(userString);
                    }
                    catch(Exception e){
                        userDescision = -1;
                    }
                    if(userDescision == 2){
                        handleOpenInventory();
                        System.out.println("\f" + player + "\n\n" + encounterText + "\n");
                        System.out.println("Choose an action:");
                        System.out.println("1. Check your equipment");
                        System.out.println("2. Open your inventory");
                        System.out.println("3. Attack");
                        System.out.println("4. Flee");
                    }
                    else if(userDescision == 3){
                        System.out.println("\f" + player + "\n");
                        int result = handleFightActions(monsters);
                        if(result == -1){
                            //player died
                            hasDied = true;
                            break;
                        }
                        else if(result == 1){
                            System.out.println("\f" + player + "\n");
                            System.out.println("You have successfully fled");
                            hasFled = true;
                            break;
                        }
                        else if(result == 2){
                            System.out.println("\f" + player + "\n");
                            System.out.println("You have cleared the room of monsters");
                            location.killEnemies();
                            break;
                        }
                    }
                    else if(userDescision == 4){
                        if(handleFlee(monsters)){
                            System.out.println("\f" + player + "\n");
                            System.out.println("You have successfully fled");
                            hasFled = true;
                            break;
                        }
                        else{
                            System.out.println("\f" + player + "\n");
                            System.out.println("You did not get away");
                            System.out.println("\n1. Attack");
                            input.next();                            
                            System.out.println("\f" + player + "\n");
                            int result = handleFightActions(monsters);
                            if(result == -1){
                                //player died
                                hasDied = true;
                                break;
                            }
                            else if(result == 1){
                                System.out.println("\f" + player + "\n");
                                System.out.println("You have successfully fled");
                                hasFled = true;
                                break;
                            }
                            else if(result == 2){
                                System.out.println("\f" + player + "\n");
                                location.killEnemies();
                                System.out.println("You have cleared the room of monsters");
                                break;
                            }
                        }
                    }
                    else{
                        System.out.println("\f" + player + "\n");
                        System.out.println("Invalid choice" + "\n" + encounterText + "\n");
                        System.out.println("Choose an action:");
                        System.out.println("1. Check your equipment");
                        System.out.println("2. Open your inventory");
                        System.out.println("3. Attack");
                        System.out.println("4. Flee");
                    }
                }
            }       
        }

        if(!hasFled && !hasDied){
            ArrayList<String> actions = new ArrayList<String>();

            actions.add("Check your equipment");
            actions.add("Open your inventory");
            actions.add("Search for items");
            if(location.getIsExit()){
                actions.add("Exit the dungeon");   
            }
            else{
                int straight = random.nextInt(4) + 1;
                int left = random.nextInt(4) + 1;
                int right = random.nextInt(4) + 1;
                if(straight == 3)
                    actions.add("Go through the door straight ahead");
                else if(straight == 4)
                    actions.add("Take through the corridor straight ahead");
                if(left == 3)
                    actions.add("Go through the door on the left");
                else if(left == 4)
                    actions.add("Take through the corridor to the left");
                if(right == 3)
                    actions.add("Go through the door on the right");
                else if(right == 4)
                    actions.add("Take through the corridor to the right");
                if(straight < 3 && left < 3 && right < 3)   
                    actions.add("Go through the door straight ahead");
            }

            String userString;
            while (true){
                printActions(actions);
                userString = input.next();
                if(userString == null){
                    System.out.println("\f" + player + "\n");
                }
                else if (userString.equals("SAVE")){
                    try{
                        saveGame();  
                        System.out.println("\f" + player + "\n");
                        System.out.println("Game saved");
                    }
                    catch(IOException e){
                        System.out.println("\f" + player + "\n");
                        System.out.println("Game not saved");
                        e.printStackTrace();
                    }
                }
                else{
                    int userDescision = 0; 
                    try{
                        userDescision = Integer.parseInt(userString);
                    }
                    catch(Exception e){
                        userDescision = -1;
                    }
                    if(userDescision == 2)
                        handleOpenInventory();
                    else if(userDescision == 3)
                        handleSearchRoomForItems(location);
                    else if(userDescision > 3 && userDescision <= actions.size())
                        break;
                    else{
                        System.out.println("\f" + player + "\n");
                        System.out.println("Invalid choice");
                    }
                }
            } 
        }
        else if (!hasDied){
            ArrayList<String> actions = new ArrayList<String>();
            if(location.getIsExit()){
                actions.add("Exit the dungeon");   
            }
            else{
                int straight = random.nextInt(4) + 1;
                int left = random.nextInt(4) + 1;
                int right = random.nextInt(4) + 1;
                if(straight == 3)
                    actions.add("Go through the door straight ahead");
                else if(straight == 4)
                    actions.add("Take through the corridor straight ahead");
                if(left == 3)
                    actions.add("Go through the door on the left");
                else if(left == 4)
                    actions.add("Take through the corridor to the left");
                if(right == 3)
                    actions.add("Go through the door on the right");
                else if(right == 4)
                    actions.add("Take through the corridor to the right");
                if(straight < 3 && left < 3 && right < 3)   
                    actions.add("Go through the door straight ahead");
            }

            String userString;
            while (true){
                printActions(actions);
                userString = input.next();
                if(userString == null){
                    System.out.println("\f" + player + "\n");
                }
                else{
                    int userDescision = 0; 
                    try{
                        userDescision = Integer.parseInt(userString);
                    }
                    catch(Exception e){
                        userDescision = -1;
                    }
                    if(userDescision > 0 && userDescision <= actions.size())
                        break;
                    else{
                        System.out.println("\f" + player + "\n");
                        System.out.println("Invalid choice");
                    }
                }
            }
        }

        if(!hasDied){
            player.move(false);
        }
        else{
            performEndGameLogic();   
        }
    }
    
    private static void handleWorld(World location){
        System.out.println("You are out and about in the world.");
        if(location.getHasDungeonEnterance()){
            System.out.println("You see the enterance to a dungeon");
        }

        Vector<GameCharacter> enemies = location.getEnemies();

        boolean hasFled = false;
        boolean hasDied = false;
        if(enemies.size() != 0){
            Monster[] monsters = new Monster[enemies.size()];
            for(int i = 0; i < monsters.length; i++){
                if(enemies.get(i) instanceof Monster)
                    monsters[i] = (Monster)enemies.get(i);
            }

            String encounterText = "Your path is blocked by a " + monsters[0].getName();
            for(int i = 1; i < monsters.length; i++){
                if(i != monsters.length -1)
                    encounterText += ", a " + monsters[i].getName();
                else if (i == monsters.length - 1 && i == 1)
                    encounterText += " and a " + monsters[i].getName();
                else
                    encounterText += ", and a " + monsters[i].getName();
            }
            System.out.println(encounterText + "\n");
            System.out.println("Choose an action:");
            System.out.println("1. Check your equipment");
            System.out.println("2. Open your inventory");
            System.out.println("3. Attack");
            System.out.println("4. Flee");

            String userString;
            while (true){
                userString = input.next();
                if(userString == null){
                    System.out.println("\f" + player + "\n" + encounterText + "\n");
                    System.out.println("Choose an action:");
                    System.out.println("1. Check your equipment");
                    System.out.println("2. Open your inventory");
                    System.out.println("3. Attack");
                    System.out.println("4. Flee");
                }
                else{
                    int userDescision = 0; 
                    try{
                        userDescision = Integer.parseInt(userString);
                    }
                    catch(Exception e){
                        userDescision = -1;
                    }
                    if(userDescision == 2){
                        handleOpenInventory();
                        System.out.println("\f" + player + "\n\n" + encounterText + "\n");
                        System.out.println("Choose an action:");
                        System.out.println("1. Check your equipment");
                        System.out.println("2. Open your inventory");
                        System.out.println("3. Attack");
                        System.out.println("4. Flee");
                    }
                    else if(userDescision == 3){
                        System.out.println("\f" + player + "\n");
                        int result = handleFightActions(monsters);
                        if(result == -1){
                            //player died
                            hasDied = true;
                            break;
                        }
                        else if(result == 1){
                            System.out.println("\f" + player + "\n");
                            System.out.println("You have successfully fled");
                            hasFled = true;
                            break;
                        }
                        else if(result == 2){
                            System.out.println("\f" + player + "\n");
                            System.out.println("You have cleared the room of monsters");
                            location.killEnemies();
                            break;
                        }
                    }
                    else if(userDescision == 4){
                        if(handleFlee(monsters)){
                            System.out.println("\f" + player + "\n");
                            System.out.println("You have successfully fled");
                            hasFled = true;
                            break;
                        }
                        else{
                            System.out.println("\f" + player + "\n");
                            System.out.println("You did not get away");
                            System.out.println("\n1. Attack");
                            input.next();                            
                            System.out.println("\f" + player + "\n");
                            int result = handleFightActions(monsters);
                            if(result == -1){
                                //player died
                                hasDied = true;
                                break;
                            }
                            else if(result == 1){
                                System.out.println("\f" + player + "\n");
                                System.out.println("You have successfully fled");
                                hasFled = true;
                                break;
                            }
                            else if(result == 2){
                                System.out.println("\f" + player + "\n");
                                location.killEnemies();
                                System.out.println("You have cleared the room of monsters");
                                break;
                            }
                        }
                    }
                    else{
                        System.out.println("\f" + player + "\n");
                        System.out.println("Invalid choice" + "\n" + encounterText + "\n");
                        System.out.println("Choose an action:");
                        System.out.println("1. Check your equipment");
                        System.out.println("2. Open your inventory");
                        System.out.println("3. Attack");
                        System.out.println("4. Flee");
                    }
                }
            }       
        }

        if(!hasFled && !hasDied){
            ArrayList<String> actions = new ArrayList<String>();

            actions.add("Check your equipment");
            actions.add("Open your inventory");
            actions.add("Search for items");
                int straight = random.nextInt(2) + 1;
                int left = random.nextInt(4) + 1;
                int right = random.nextInt(4) + 1;
                if(straight == 2)
                    actions.add("Continue straight along the path");
                if(left == 3)
                    actions.add("Turn left at the crossroad");
                else if(left == 4)
                    actions.add("Follow the path to the left");
                if(right == 3)
                    actions.add("Turn right at the crossroad");
                else if(right == 4)
                    actions.add("Follow the path to the right");
                if(straight < 2 && left < 3 && right < 3)   
                    actions.add("Continue straight along the path");
            
        

            String userString;
            while (true){
                printActions(actions);
                userString = input.next();
                if(userString == null){
                    System.out.println("\f" + player + "\n");
                }
                else if (userString.equals("SAVE")){
                    try{
                        saveGame();  
                        System.out.println("\f" + player + "\n");
                        System.out.println("Game saved");
                    }
                    catch(IOException e){
                        System.out.println("\f" + player + "\n");
                        System.out.println("Game not saved");
                        e.printStackTrace();
                    }
                }
                else{
                    int userDescision = 0; 
                    try{
                        userDescision = Integer.parseInt(userString);
                    }
                    catch(Exception e){
                        userDescision = -1;
                    }
                    if(userDescision == 2)
                        handleOpenInventory();
                    else if(userDescision == 3)
                        handleSearchWorldForItems(location);
                    else if(userDescision > 3 && userDescision <= actions.size())
                        break;
                    else{
                        System.out.println("\f" + player + "\n");
                        System.out.println("Invalid choice");
                    }
                }
            } 
        }
        else if (!hasDied){
            ArrayList<String> actions = new ArrayList<String>();

                int straight = random.nextInt(2) + 1;
                int left = random.nextInt(4) + 1;
                int right = random.nextInt(4) + 1;
                if(straight == 2)
                    actions.add("Continue straight along the path");
                if(left == 3)
                    actions.add("Turn left at the crossroad");
                else if(left == 4)
                    actions.add("Follow the path to the left");
                if(right == 3)
                    actions.add("Turn right at the crossroad");
                else if(right == 4)
                    actions.add("Follow the path to the right");
                if(straight < 2 && left < 3 && right < 3)   
                    actions.add("Continue straight along the path");
            

            String userString;
            while (true){
                printActions(actions);
                userString = input.next();
                if(userString == null){
                    System.out.println("\f" + player + "\n");
                }
                else{
                    int userDescision = 0; 
                    try{
                        userDescision = Integer.parseInt(userString);
                    }
                    catch(Exception e){
                        userDescision = -1;
                    }
                    if(userDescision > 0 && userDescision <= actions.size())
                        break;
                    else{
                        System.out.println("\f" + player + "\n");
                        System.out.println("Invalid choice");
                    }
                }
            }
        }

        if(!hasDied){
            player.move(false); //take input to enter dungeon
        }
        else{
            performEndGameLogic();   
        }
    }

    private static void handleTown(){
        System.out.println("Found a town");
        input.nextLine();
    }

    private static boolean handleFlee(Monster[] monsters){
        for(Monster monster : monsters){
            if(monster != null){
                if(random.nextInt(player.getMaxWeight() + 1) <= player.getCurrentWeight())
                    return false;
            }
        }
        return true;
    }

    private static int handleFightActions(Monster[] monsters){
        System.out.println("Choose a combat action:");
        System.out.println("1. Check your equipment");
        System.out.println("2. Open your inventory");
        System.out.println("3. Attack");
        System.out.println("4. Flee");

        String userString;
        while (true){
            userString = input.next();
            if(userString == null){
                System.out.println("\f" + player + "\n");
                System.out.println("Choose a combat action:");
                System.out.println("1. Check your equipment");
                System.out.println("2. Open your inventory");
                System.out.println("3. Attack");
                System.out.println("4. Flee");
            }
            else{
                int userDescision = 0; 
                try{
                    userDescision = Integer.parseInt(userString);
                }
                catch(Exception e){
                    userDescision = -1;
                }
                if(userDescision == 2){
                    handleOpenInventory();
                    System.out.println("\f" + player + "\n");
                    System.out.println("Choose a combat action:");
                    System.out.println("1. Check your equipment");
                    System.out.println("2. Open your inventory");
                    System.out.println("3. Attack");
                    System.out.println("4. Flee");
                }
                else if (userDescision == 3){
                    int success = handleAttack(monsters);
                    if(success == 1){
                        return 2;
                    }
                    else if(success == -1){
                        return -1;
                    }
                    else{
                        System.out.println("\f" + player + "\n");
                        System.out.println("Choose a combat action:");
                        System.out.println("1. Check your equipment");
                        System.out.println("2. Open your inventory");
                        System.out.println("3. Attack");
                        System.out.println("4. Flee");
                    }
                }
                else if(userDescision == 4){
                    int success = handleMonsterOpertunityAttack(monsters);
                    if( success == 1){
                        return 1;
                    }
                    else if( success == -1){
                        return -1;
                    }
                    else{
                        System.out.println("\f" + player + "\n");
                        System.out.println("You did not get away\n");
                        System.out.println("Choose a combat action:");
                        System.out.println("1. Check your equipment");
                        System.out.println("2. Open your inventory");
                        System.out.println("3. Attack");
                        System.out.println("4. Flee");
                    }
                }
                else{
                    System.out.println("\f" + player + "\n");
                    System.out.println("Invalid choice\n");
                    System.out.println("Choose a combat action:");
                    System.out.println("1. Check your equipment");
                    System.out.println("2. Open your inventory");
                    System.out.println("3. Attack");
                    System.out.println("4. Flee");
                }
            }
        }
    }

    private static int handleAttack(Monster[] monsters){
        System.out.println("\f" + player + "\n");
        Monster currentTarget = null;
        int targetIndex;

        String combatResultsString = "";

        boolean doubleDamage = false;
        if(monsters.length > 1){
            System.out.println("Which monster will you attack?");
            for(int i = 0; i < monsters.length; i++){
                if(monsters[i] != null)
                    System.out.println((i+1) + ". " + monsters[i].getName());
            }

            String userString;
            while (true){
                userString = input.next();
                if(userString == null){
                    System.out.println("\f" + player + "\n");
                    System.out.println("Which monster will you attack?");
                    for(int i = 0; i < monsters.length; i++){
                        if(monsters[i] != null)
                            System.out.println((i+1) + ". " + monsters[i].getName());
                    }
                }
                else{
                    int userDescision = 0; 
                    try{
                        userDescision = Integer.parseInt(userString);
                    }
                    catch(Exception e){
                        userDescision = -1;
                    }
                    if(userDescision > 0 && userDescision <= monsters.length){
                        currentTarget = monsters[userDescision-1];
                        targetIndex = userDescision-1;
                        break;
                    }              
                    else{
                        System.out.println("\f" + player + "\n");
                        System.out.println("Which monster will you attack?");
                        for(int i = 0; i < monsters.length; i++){
                            if(monsters[i] != null)
                                System.out.println((i+1) + ". " + monsters[i].getName());
                        }
                    }
                }
            }
        }
        else{
            currentTarget = monsters[0];   
            targetIndex = 0;
        }

        int rollToHit = random.nextInt(20)+1;
        if(rollToHit == 20)
            doubleDamage = true;
        if(currentTarget.checkIfHit(rollToHit)){
            int attackDamage = 0;
            switch(currentTarget.getCharacterSize()){
                case SMALL:
                attackDamage = player.attackMed();
                if(doubleDamage)
                    attackDamage *= 2;
                currentTarget.takeDamage(attackDamage);
                break;
                case MEDIUM:
                attackDamage = player.attackMed();
                if(doubleDamage)
                    attackDamage *= 2;
                currentTarget.takeDamage(attackDamage);
                break;
                case LARGE:
                attackDamage = player.attackLarge();
                if(doubleDamage)
                    attackDamage *= 2;
                currentTarget.takeDamage(attackDamage);
                break;
            }        
            doubleDamage = false;

            boolean targetIsDead = currentTarget.checkIsDead(); 
            if(targetIsDead){
                player.addXP(currentTarget.getXP());
                combatResultsString +="You killed " + currentTarget.getName() + "\n";
                monsters[targetIndex] = null;
            }
            else{
                combatResultsString += "You hit " + currentTarget.getName() + " for " + attackDamage +" points of damage\n";
            }

            boolean allMonstersDead = true;
            for(int i = 0; i < monsters.length; i++){
                if(monsters[i] != null){
                    allMonstersDead = false;
                    break;
                }
            }

            if(allMonstersDead){
            System.out.println("\f" + player + "\n\n" + combatResultsString);
            System.out.println("1. Done");
            String userString;
            while (true){
                userString = input.next();
                return 1;           
            }   
            }
        }
        else{
            combatResultsString +="You missed\n";
        }

        int playerDamage = 0;
        for(int i = 0; i < monsters.length; i++)
        {
            rollToHit = random.nextInt(20)+1;
            if(rollToHit == 20){
                doubleDamage = true;   
            }
            if(monsters[i] != null && player.checkIfHit(rollToHit)){
                switch(player.getCharacterSize()){
                    case SMALL:
                    playerDamage = monsters[i].attackMed();
                    if(doubleDamage)
                        playerDamage *= 2;
                    player.takeDamage(playerDamage);
                    break;
                    case MEDIUM:
                    playerDamage = monsters[i].attackMed();
                    if(doubleDamage)
                        playerDamage *= 2;
                    player.takeDamage(playerDamage);
                    break;
                    case LARGE:
                    playerDamage = monsters[i].attackLarge();
                    if(doubleDamage)
                        playerDamage *= 2;
                    player.takeDamage(playerDamage);
                    break;
                }   
                combatResultsString += monsters[i].getName() + " hit you for " + playerDamage + " points of damage\n";
            }
            else if(monsters[i] != null){
                combatResultsString += monsters[i].getName() + " missed\n";
            }
        }

        System.out.println("\f" + player + "\n\n" + combatResultsString);
        boolean isPlayerDead = player.checkIsDead();
        if(isPlayerDead){
            System.out.println("1. Done");
            String userString;
            while (true){
                userString = input.next();
                return -1;           
            }
        }
        else{
            System.out.println("1. Done");
            String userString;
            while (true){
                userString = input.next();
                return 0;           
            }
        }
    }

    private static int handleMonsterOpertunityAttack(Monster[] monsters){
        boolean doubleDamage = false;

        String combatResultsString = "";

        boolean fled = handleFlee(monsters);

        if(fled)
            combatResultsString += "You successfully fled\n";
        else
            combatResultsString += "You did not get away\n";

        int rollToHit = 0;
        int playerDamage = 0;
        for(int i = 0; i < monsters.length; i++)
        {
            rollToHit = random.nextInt(20)+1;
            if(rollToHit == 20){
                doubleDamage = true;   
            }
            if(monsters[i] != null && player.checkIfHit(rollToHit)){
                switch(player.getCharacterSize()){
                    case SMALL:
                    playerDamage = monsters[i].attackMed();
                    if(doubleDamage)
                        playerDamage *= 2;
                    player.takeDamage(playerDamage);
                    break;
                    case MEDIUM:
                    playerDamage = monsters[i].attackMed();
                    if(doubleDamage)
                        playerDamage *= 2;
                    player.takeDamage(playerDamage);
                    break;
                    case LARGE:
                    playerDamage = monsters[i].attackLarge();
                    if(doubleDamage)
                        playerDamage *= 2;
                    player.takeDamage(playerDamage);
                    break;
                }   
                combatResultsString += monsters[i].getName() + " hit you for " + playerDamage + " points of damage\n";
            }
            else if(monsters[i] != null){
                combatResultsString += monsters[i].getName() + " missed\n";
            }
        }

        System.out.println("\f" + player + "\n\n" + combatResultsString);
        boolean isPlayerDead = player.checkIsDead();
        if(isPlayerDead){
            System.out.println("1. Done");
            String userString;
            while (true){
                userString = input.next();
                return -1;           
            }
        }
        else if(fled){
            System.out.println("1. Done");
            String userString;
            while (true){
                userString = input.next();
                return 1;           
            }
        }
        else{
            System.out.println("1. Done");
            String userString;
            while (true){
                userString = input.next();
                return 0;           
            }
        }
    }

    private static void printActions(ArrayList<String> actions){
        System.out.println();
        System.out.println("Choose an action:");
        for (int i = 0; i < actions.size(); i++){
            System.out.println((i+1) + ". " + actions.get(i));   
        }
    }

    private static void performEndGameLogic(){
        System.out.println("\f");
        System.out.println("                     -|-");
        System.out.println("                      |");
        System.out.println("                  .-'~~~`-.");
        System.out.println("                .'         `.");
        System.out.println("                |  R  I  P  |");
        System.out.println("                |           |");
        System.out.println("                |           |");
        System.out.println("               \\|           |//");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("You have been slain!");
        System.out.println("Alas, poor adventurer! Your corpse shall lay");
        System.out.println("rotting until it is found by some poor"); 
        System.out.println("unfortunate soul who follows in your footsteps.");
        System.out.println();
        System.out.println("1. Start again");
        System.out.println("2. Quit");

        String userString;
        while (true){
            userString = input.next();
            if(userString == null){
                System.out.println("\f");
                System.out.println("                     -|-");
                System.out.println("                      |");
                System.out.println("                  .-'~~~`-.");
                System.out.println("                .'         `.");
                System.out.println("                |  R  I  P  |");
                System.out.println("                |           |");
                System.out.println("                |           |");
                System.out.println("               \\|           |//");
                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                System.out.println("You have been slain!");
                System.out.println("Alas, poor adventurer! Your corpse shall lay");
                System.out.println("rotting until it is found by some poor"); 
                System.out.println("unfortunate soul who follows in your footsteps.");
                System.out.println();
                System.out.println("1. Start again");
                System.out.println("2. Quit");
            }
            else{
                int userDescision = 0; 
                try{
                    userDescision = Integer.parseInt(userString);
                }
                catch(Exception e){
                    userDescision = -1;
                }
                if(userDescision == 1){
                    break;
                }              
                else if(userDescision == 2){
                    System.exit(0);   
                }
                else{
                    System.out.println("\f");
                    System.out.println("                     -|-");
                    System.out.println("                      |");
                    System.out.println("                  .-'~~~`-.");
                    System.out.println("                .'         `.");
                    System.out.println("                |  R  I  P  |");
                    System.out.println("                |           |");
                    System.out.println("                |           |");
                    System.out.println("               \\|           |//");
                    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
                    System.out.println("You have been slain!");
                    System.out.println("Alas, poor adventurer! Your corpse shall lay");
                    System.out.println("rotting until it is found by some poor"); 
                    System.out.println("unfortunate soul who follows in your footsteps.");
                    System.out.println();
                    System.out.println("1. Start again");
                    System.out.println("2. Quit");
                }
            }
        }

        main();
    }

    private static void handleOpenInventory(){
        System.out.println("\f" + player + "\n");
        System.out.println("Choose an option");
        Vector<Item> playerInventory = player.getInventory();
        player.showInventory();
        System.out.println((playerInventory.size()+1) + ". Done");

        String userString;
        while(true){
            userString = input.next();
            if(userString == null){
                System.out.println("\f" + player + "\n");
            }
            else{
                int userDecision = 0;
                try{
                    userDecision = Integer.parseInt(userString);
                }
                catch(Exception e){
                    userDecision = -1;
                }
                if(userDecision == playerInventory.size()+1)
                    break;
                else if(userDecision > 0 && userDecision <= playerInventory.size()){
                    if(playerInventory.get(userDecision-1) instanceof Weapon){
                        handleInspectWeapon((Weapon)playerInventory.get(userDecision-1));
                        System.out.println("\f" + player + "\n");
                        System.out.println("Choose an option");
                        player.showInventory();                
                        System.out.println((playerInventory.size()+1) + ". Done");
                    }
                }
                else{
                    System.out.println("\f" + player + "\n");
                    System.out.println("Invalid choice\n");
                    System.out.println("Choose an option");
                    player.showInventory();                
                    System.out.println((playerInventory.size()+1) + ". Done");
                }
            }
        }
        System.out.print("\f" + player + "\n");
    }

    private static void handleInspectWeapon(Weapon weapon){
        System.out.print("\f" + player + "\n\n");
        System.out.println(weapon + "\n");
        System.out.println("Choose an option:");
        String equipString = "1. Equip";
        if(weapon.getIsEquipped())
            equipString += " -- (Already Equipped)";
        System.out.println(equipString);
        System.out.println("2. Drop");
        System.out.println("3. Done");

        String userString;
        while(true){
            userString = input.next();
            if(userString == null){
                System.out.println("\f" + player + "\n");
            }
            else{
                int userDecision = 0;
                try{
                    userDecision = Integer.parseInt(userString);
                }
                catch(Exception e){
                    userDecision = -1;
                }

                if(userDecision == 1){
                    player.equipWeapon(weapon);
                    break;
                }   
                else if(userDecision == 2){
                    player.dropWeapon(weapon);
                    break;
                }
                else if (userDecision == 3)
                    break;
                else{
                    System.out.print("\f" + player + "\n\n");
                    System.out.println("Invalid choice\n");
                    System.out.println(weapon + "\n");
                    System.out.println("Choose an option:");
                    System.out.println(equipString);
                    System.out.println("2. Done");
                }
            }
        }
    }

    private static void handleInspectArmour(Armour armour){

    }

    private static void hanldeInspectItem(Item item){

    }

    private static void handleCheckEquipment(){
        System.out.println("\f" + player + "\n");
    }

    private static void handleSearchRoomForItems(Room location){
        System.out.println("\f" + player + "\n");
        if(location.getItems() == null || location.getItems().size() == 0){
            System.out.println("You find nothing");
        }
        else{
            System.out.println("You found:");
            Vector<Item> items = location.getItems();
            for(int i = 0; i < items.size(); i++){
                System.out.println(items.get(i).getName());   
            }
            player.lootArea();
        }
        System.out.println("\n1. Done");
        String userString;
        userString = input.next();   
        System.out.print("\f" + player + "\n");
    }

    private static void handleSearchWorldForItems(World location){
        System.out.println("\f" + player + "\n");
        if(location.getItems() == null || location.getItems().size() == 0){
            System.out.println("You find nothing");
        }
        else{
            System.out.println("You found:");
            Vector<Item> items = location.getItems();
            for(int i = 0; i < items.size(); i++){
                System.out.println(items.get(i).getName());   
            }
            player.lootArea();
        }
        System.out.println("\n1. Done");
        String userString;
        userString = input.next();   
        System.out.print("\f" + player + "\n");
    }

    private static void handleCharacterSetUp(){
        input.nextLine();
        System.out.println("\fName your adventurer:");
        String playerName = input.nextLine();
        System.out.println("\fChoose your class:");
        System.out.println("1. Knight\n2. Ranger\n3. Bard\n4. " +
            "Thief\n5. Cleric\n6. Wizard");
        int classSelection = input.nextInt();
        input.nextLine();
        CharacterClass classChoice = null;
        switch(classSelection){
            case 1:
            classChoice = CharacterClass.KNIGHT;
            break;
            case 2:
            classChoice = CharacterClass.RANGER;
            break;
            case 3:
            classChoice = CharacterClass.BARD;
            break;
            case 4:
            classChoice = CharacterClass.THIEF;
            break;
            case 5:
            classChoice = CharacterClass.CLERIC;
            break;
            case 6:
            classChoice = CharacterClass.WIZARD;
            break;
        }
        //creates the player
        player = new Player(CharacterSize.MEDIUM, playerName, classChoice, 30 , 300);
        //starting balance
        player.getMoney(20);

        //give starting weapon
        System.out.println("\fChoose your starting weapon:");
        System.out.println("1. Dagger\n2. Club\n3. Hand Axe\n4. Spear");
        int weaponChoice = input.nextInt();
        input.nextLine();
        Weapon startingWeapon = WeaponDatabase.getWeaponFromDatabase(weaponChoice);
        player.addItem(startingWeapon);
        player.equipWeapon(startingWeapon);
    }

    private static void welcomeScreen(){
        System.out.print("\f");
        System.out.println("Welcome to the ENDLESS RPG");
        System.out.println();
        System.out.println("(c)Hayden Landolt 2020");
        System.out.println();
        System.out.println();
        System.out.println("Choose from the following options:");
        System.out.println("1. New game");
        System.out.println("2. Load saved game");
        System.out.println("3. Quit");

        String userString;
        while(true){
            userString = input.next();
            if(userString != null){
                int userDecision = 0;
                try{
                    userDecision = Integer.parseInt(userString);
                }
                catch(Exception e){
                    userDecision = -1;
                }
                if(userDecision == 1){
                    break;
                }   
                else if(userDecision == 2){
                    File loadGameFile;
                    JFileChooser chooser = new JFileChooser();
                    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) // WHY?
                    {
                        loadGameFile = chooser.getSelectedFile();
                        loadGame(loadGameFile);
                    }
                    else{
                        System.out.print("\f");
        System.out.println("Welcome to the ENDLESS RPG");
        System.out.println();
        System.out.println("(c)Hayden Landolt 2020");
        System.out.println();
        System.out.println();
        System.out.println("Choose from the following options:");
        System.out.println("1. New game");
        System.out.println("2. Load saved game");
        System.out.println("3. Quit");
                     continue;   
                    }
                    break;
                }
                else if (userDecision == 3)
                    System.exit(0);
                else{
                    System.out.print("\f");
        System.out.println("Welcome to the ENDLESS RPG");
        System.out.println();
        System.out.println("(c)Hayden Landolt 2020");
        System.out.println();
        System.out.println();
        System.out.println("Choose from the following options:");
        System.out.println("1. New game");
        System.out.println("2. Load saved game");
        System.out.println("3. Quit");
                }
            }
        }
    }

    private static void loadGame(File file){

    }

    private static void saveGame() throws IOException{
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");  
        Date date = new Date();  

        String fileName = player.getName() + "_Lvl_" + player.getLevel() +
            "_" + player.getCharacterClass() + "_" + formatter.format(date) + ".txt";

        File saveFile = new File("./Saved Games/" + fileName);
        PrintWriter out = new PrintWriter(saveFile);

        out.println(player.getName());
        out.println(player.getLevel());
        out.println(player.getCharacterClass());
        out.println(player.getXP());
        out.println(player.getCoins());
        out.println(player.getCurHealth());
        out.println(player.getMaxHealth());
        out.println(player.getCurrentWeight());
        out.println(player.getMaxWeight());
        out.println(player.getArrows());
        out.println(player.getBolts());

        out.println("START LOCATION DATA");
        Location playerLocation = player.getCurrentLocation();
        out.println(player.getCurrentLocation().getTerrain());
        if(playerLocation instanceof Room){
            out.println(((Room)playerLocation).getIsExit());
            out.println("START LOCATION ITEMS");
            Vector<Item> areaItems = ((Room)playerLocation).getItems();
            for(int i = 0; i < areaItems.size(); i++){
                out.println(areaItems.get(i).getName());   
            }
            out.println("START LOCATION ENEMIES");
            Vector<GameCharacter> areaChars = ((Room)playerLocation).getEnemies();
            for(int i = 0; i < areaChars.size(); i++){
                out.println(areaChars.get(i).getName());   
            }
        }

        out.close();
    }
}
