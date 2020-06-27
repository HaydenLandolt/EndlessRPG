import java.util.Vector;
import java.util.Random;

public class LocationGenerator
{
  private static Random random = new Random();
  
  public static Location nextLocation(Location currentLocation, boolean enterDungeon){
      Location newLocation = null;
      if(currentLocation instanceof Room && ((Room)currentLocation).getIsExit()){
          newLocation = generateWorld(currentLocation);
        }
      else if(currentLocation instanceof Room){
          newLocation = generateRoom();
      }
      else if(currentLocation instanceof Town){
         newLocation = generateWorld(currentLocation); 
        }
      else if(currentLocation instanceof World && ((World)currentLocation).getHasDungeonEnterance() && enterDungeon){
         newLocation = generateRoom(); 
        }
      else if(currentLocation instanceof World){
          int nextAreaType = random.nextInt(12) + 1;
          if(nextAreaType == 12){
             newLocation = generateTown(); 
            }
          else{
             newLocation = generateWorld(currentLocation); 
            }
        }
        
      return newLocation;
    }
    
  private static Location generateRoom(){
      int exitChance = random.nextInt(7) + 1;
      Vector<Item> roomItems = new Vector<Item>();
      Vector<GameCharacter> roomCharacters = generateDungeonMonsters();
      return new Room(exitChance == 7, roomItems, roomCharacters);
    }
   
  private static Location generateTown(){
      return new Town(random.nextInt(3) + 1 > 1, random.nextInt(3) + 1 > 1, random.nextInt(3) + 1 > 1, random.nextInt(3) + 1 > 1, random.nextInt(3) + 1 > 1);
    }
  
  private static Location generateWorld(Location currentLocation){
      TerrainType terrain = null;
      int nextTerrain = random.nextInt(5);
      if(currentLocation.getTerrain() == TerrainType.DUNGEON){
          terrain = TerrainType.GRASSLANDS;
        }
      else if(currentLocation.getTerrain() == TerrainType.HILL){
          switch(nextTerrain){
             case 0:
             terrain = TerrainType.HILL;
             break;
             case 1:
             terrain = TerrainType.GRASSLANDS;
             break;
             case 2:
             terrain = TerrainType.GRASSLANDS;
             break;
             case 3:
             terrain = TerrainType.FOREST;
             break;
             case 4:
             terrain = TerrainType.MOUNTAINS;
            }
        }
      else if(currentLocation.getTerrain() == TerrainType.GRASSLANDS){
            switch(nextTerrain){
          case 0:
             terrain = TerrainType.HILL;
             break;
             case 1:
             terrain = TerrainType.GRASSLANDS;
             break;
             case 2:
             terrain = TerrainType.GRASSLANDS;
             break;
             case 3:
             terrain = TerrainType.FOREST;
             break;
             case 4:
             terrain = TerrainType.FOREST;
            }
        }
        else if(currentLocation.getTerrain() == TerrainType.FOREST){
            switch(nextTerrain){
          case 0:
             terrain = TerrainType.HILL;
             break;
             case 1:
             terrain = TerrainType.GRASSLANDS;
             break;
             case 2:
             terrain = TerrainType.MOUNTAINS;
             break;
             case 3:
             terrain = TerrainType.FOREST;
             break;
             case 4:
             terrain = TerrainType.FOREST;
            }
        }
        else if(currentLocation.getTerrain() == TerrainType.MOUNTAINS){
            switch(nextTerrain){
          case 0:
             terrain = TerrainType.HILL;
             break;
             case 1:
             terrain = TerrainType.SNOW;
             break;
             case 2:
             terrain = TerrainType.MOUNTAINS;
             break;
             case 3:
             terrain = TerrainType.MOUNTAINS;
             break;
             case 4:
             terrain = TerrainType.FOREST;
            }
        }
        else if(currentLocation.getTerrain() == TerrainType.SNOW){
            switch(nextTerrain){
          case 0:
             terrain = TerrainType.HILL;
             break;
             case 1:
             terrain = TerrainType.SNOW;
             break;
             case 2:
             terrain = TerrainType.SNOW;
             break;
             case 3:
             terrain = TerrainType.MOUNTAINS;
             break;
             case 4:
             terrain = TerrainType.FOREST;
            }
        }
        else if(currentLocation.getTerrain() == TerrainType.TOWN){
            switch(nextTerrain){
          case 0:
             terrain = TerrainType.HILL;
             break;
             case 1:
             terrain = TerrainType.GRASSLANDS;
             break;
             case 2:
             terrain = TerrainType.GRASSLANDS;
             break;
             case 3:
             terrain = TerrainType.GRASSLANDS;
             break;
             case 4:
             terrain = TerrainType.FOREST;
            }
        }
        
        Vector<Item> areaItems = new Vector<Item>();
        Vector<GameCharacter> areaCharacters = null;
        switch(terrain){
         case HILL:
         areaCharacters = generateHillMonsters();
         break;
         case GRASSLANDS:
         areaCharacters = generateGrasslandsMonsters();
         break;
         case FOREST:
         areaCharacters = generateForestMonsters();
         break;
         case MOUNTAINS:
         areaCharacters = generateMountainMonsters();
         break;
         case SNOW:
         areaCharacters = generateSnowMonsters();
         break;
        }
        
      return new World(terrain, random.nextInt(8) + 1 == 8, areaItems, areaCharacters);
    }
    
    private static Vector<GameCharacter> generateDungeonMonsters(){
        Vector<GameCharacter> monsters = new Vector<GameCharacter>();
        
        int packageChance = random.nextInt(108)+1;
        
        if(packageChance <= 2){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(1));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(1));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(1));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(1));
        }
        else if(packageChance <= 4){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(1));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(1));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(2));    
        }
        else if(packageChance <= 6){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(22));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(22));
        }
        else if(packageChance <= 8){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(22));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(22));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(16));
        }
        else if(packageChance <= 10){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(47));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(47));
        }
        else if(packageChance <= 12){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(47));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(16));
        }
        else if(packageChance <= 14){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(55));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(55));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(55));
        }
        else if(packageChance <= 16){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(55));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(55));         
        }
        else if(packageChance <= 18){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(64));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(64));         
        }
        else if(packageChance <= 20){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(64));
        }
        else if(packageChance <= 22){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(80));        
        }
        else if(packageChance <= 24){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(82)); 
            monsters.add(MonsterDatabase.getMonsterFromDatabase(82)); 
            monsters.add(MonsterDatabase.getMonsterFromDatabase(82)); 
        }
        else if(packageChance <= 26){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(82)); 
            monsters.add(MonsterDatabase.getMonsterFromDatabase(82));          
        }
        else if(packageChance <= 28){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(56));          
        }
        else if(packageChance <= 29){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(77));          
        }
        else if(packageChance <= 30){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(76));          
        }
        else if(packageChance <= 32){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(74));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(74)); 
        }
        else if(packageChance <= 34){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(57));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(57)); 
        }
        else if(packageChance <= 36){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(2));          
        }
        else if(packageChance <= 38){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(2)); 
            monsters.add(MonsterDatabase.getMonsterFromDatabase(2)); 
        }
        else if(packageChance <= 40){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(5));          
        }
        else if(packageChance <= 42){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(39));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(39));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(39));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(39));
        }
        else if(packageChance <= 44){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(40));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(40));
        }
        else if(packageChance <= 46){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(6));
        }
        else if(packageChance <= 47){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(6));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(6));
        }
        else if(packageChance <= 49){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(66));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(66));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(66));
        }
        else if(packageChance <= 51){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(66));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(66));
        }
        else if(packageChance <= 53){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(67));
        }
        else if(packageChance <= 55){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(68));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(68));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(68));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(68));
        }
        else if(packageChance <= 57){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(68));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(68));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(68));
        }
        else if(packageChance <= 59){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(68));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(68));
        }
        else if(packageChance <= 61){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(32));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(32));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(33));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(33));
        }
        else if(packageChance <= 62){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(15));
        }
        else if(packageChance <= 64){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(32));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(32));
        }
        else if(packageChance <= 66){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(33));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(33));
        }
        else if(packageChance <= 67){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(0));        
        }
        else if(packageChance <= 69){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(45));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(45));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(45));
        }
        else if(packageChance <= 71){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(51));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(51));
        }
        else if(packageChance <= 73){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(52));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(52));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(52));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(52));
        }
        else if(packageChance <= 75){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(45));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(45));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(16));
        }
        else if(packageChance <= 77){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(16));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(16));
        }
        else if(packageChance <= 79){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(50));
        }
        else if(packageChance <= 81){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(54));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(54));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(54));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(54));
        }
        else if(packageChance <= 83){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(50));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(52));
        }
        else if(packageChance <= 84){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(59));
        }
        else if(packageChance <= 86){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(60));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(60));
        }
        else if(packageChance <= 87){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(75));
        }
        else if(packageChance <= 88){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(63));
        }
        else if(packageChance <= 89){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(79));
        }
        else if(packageChance <= 90){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(58));
        }
        else if(packageChance <= 91){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(61));
        }
        else if(packageChance <= 92){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(34));
        }
        else if(packageChance <= 93){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(34));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(16));
        }
        else if(packageChance <= 94){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(11));
        }
        else if(packageChance <= 95){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(69));
        }
        else if(packageChance <= 96){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(70));
        }
        else if(packageChance <= 97){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(14));
        }
        else if(packageChance <= 98){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(62));
            monsters.add(MonsterDatabase.getMonsterFromDatabase(51));
        }
        else if(packageChance <= 99){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(29));
        }
        else if(packageChance <= 100){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(30));
        }
        else if(packageChance <= 101){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(31));
        }
        else if(packageChance <= 102){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(48));
        }
        else if(packageChance <= 103){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(53));
        }
        else if(packageChance <= 104){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(49));
        }
        else if(packageChance <= 105){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(17));
        }
        else if(packageChance <= 106){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(18));
        }
        else if(packageChance <= 107){
            monsters.add(MonsterDatabase.getMonsterFromDatabase(19));
        }
        else{
            monsters.add(MonsterDatabase.getMonsterFromDatabase(72));
        }
        
        return monsters;
    }
    
    private static Vector<GameCharacter> generateHillMonsters(){
        Vector<GameCharacter> monsters = new Vector<GameCharacter>();
        
        int packageChance = random.nextInt(100)+1;
        
        return monsters;
    }
    
    private static Vector<GameCharacter> generateGrasslandsMonsters(){
        Vector<GameCharacter> monsters = new Vector<GameCharacter>();
        
        int packageChance = random.nextInt(100)+1;
        
        return monsters;
    }
    
    private static Vector<GameCharacter> generateMountainMonsters(){
        Vector<GameCharacter> monsters = new Vector<GameCharacter>();
        
        int packageChance = random.nextInt(100)+1;
        
        return monsters;
    }
    
    private static Vector<GameCharacter> generateForestMonsters(){
        Vector<GameCharacter> monsters = new Vector<GameCharacter>();
        
        int packageChance = random.nextInt(100)+1;
        
        return monsters;
    }
    
    private static Vector<GameCharacter> generateSnowMonsters(){
        Vector<GameCharacter> monsters = new Vector<GameCharacter>();
        
        int packageChance = random.nextInt(100)+1;
        
        return monsters;
    }
}
