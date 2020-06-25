import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Vector;
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

        Weapon handAxe = WeaponDatabase.getWeaponFromDatabase(3);
        player = new Player(CharacterSize.MEDIUM, "Bruce", CharacterClass.KNIGHT, 12 , 300);
        player.addItem(handAxe);
        player.equipWeapon(handAxe);
        //handleCharacterSetUp();

        while(true){
            System.out.println("\f" + player + "\n");
            enterLocation(player.getCurrentLocation());
        }

        //performEndGameLogic();
    }

    private static void enterLocation(Location location){
        if(location instanceof Room)
            handleRoom(location);
        else if(location instanceof Town)
            handleTown();
        else if(location instanceof World)
            handleWorld();
    }

    private static void handleRoom(Location location){
        System.out.println("You enter a new room in the dungeon.");
        if(((Room)location).getIsExit()){
            System.out.println("You see the dungeon's exit in front of you");
        }

        ArrayList<String> actions = new ArrayList<String>();

        actions.add("Check your equipment");
        actions.add("Open your inventory");
        actions.add("Search for items");
        if(((Room)location).getIsExit()){
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

        int userDescision;
        while (true){
            printActions(actions);
            userDescision = input.nextInt();
            input.nextLine();
            if(userDescision == 2)
                handleOpenInventory();
            else if(userDescision == 3)
                handleSearchRoomForItems((Room)location);
            else if(userDescision > 3 && userDescision <= actions.size())
                break;
            else{
                System.out.println("\f" + player + "\n");
                System.out.println("Invalid choice");
            }
        }       

    }

    private static void handleWorld(){

    }

    private static void handleTown(){

    }

    private static void handleFightActions(){

    }

    private static void handleRoomMovementActions(Room location){

    }

    private static void handleWorldMovementActions(){

    }

    private static void printActions(ArrayList<String> actions){
        System.out.println();
        System.out.println("Choose an action:");
        for (int i = 0; i < actions.size(); i++){
            System.out.println((i+1) + ". " + actions.get(i));   
        }
    }

    private static void performEndGameLogic(){

    }

    private static void handleOpenInventory(){
        System.out.println("\f" + player + "\n");
        System.out.println("Choose an option");
        Vector<Item> playerInventory = player.getInventory();
        player.showInventory();
        System.out.println((playerInventory.size()+1) + ". Done");

        int userDecision;
        while(true){
            userDecision = input.nextInt();
            input.nextLine();
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
        System.out.println("2. Done");

        int userDecision;
        while(true){
            userDecision = input.nextInt();
            input.nextLine();
            if(userDecision == 1){
                player.equipWeapon(weapon);
                break;
            }   
            else if (userDecision == 2)
                break;
            else{
                System.out.print("\f" + player + "\n\n");
                System.out.println(weapon + "\n");
                System.out.println("Choose an option:");
                if(weapon.getIsEquipped())
                    equipString += " -- (Already Equipped)";
                System.out.println(equipString);
                System.out.println("2. Done");
            }
        }
    }

    private static void handleCheckEquipment(){
        System.out.println("\f" + player + "\n");
    }

    private static void handleSearchRoomForItems(Room location){
        System.out.println("\f" + player + "\n");
        if(location.getItems() == null || location.getItems().size() == 0){
            System.out.println("You find nothing\n");
            System.out.println("Press ENTER to continue");
        }
        else{
            System.out.println("You found:");
            Vector<Item> items = location.getItems();
            for(int i = 0; i < items.size(); i++){
                System.out.println(items.get(i).getName());   
            }
            player.lootArea();
            System.out.println("\nPress ENTER to continue");
        }
        input.nextLine();
        System.out.print("\f" + player + "\n");
    }

    private static void handleCharacterSetUp(){
        System.out.println("\fName your adventurer:");
        String playerName = input.nextLine();
        System.out.println("\fChoose your class:");
        System.out.println("1. Knight\n2. Ranger\n3. Bard\n4. " +
            "Theif\n5. Cleric\n6. Wizard");
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
            classChoice = CharacterClass.THEIF;
            break;
            case 5:
            classChoice = CharacterClass.CLERIC;
            break;
            case 6:
            classChoice = CharacterClass.WIZARD;
            break;
        }
        //creates the player
        player = new Player(CharacterSize.MEDIUM, playerName, classChoice, 12 , 300);
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
        System.out.println("Press ENTER to begin");
        input.nextLine();
        System.out.print("\f");
    }
}
