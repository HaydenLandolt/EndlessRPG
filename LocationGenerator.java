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
      Vector<GameCharacter> roomCharacters = new Vector<GameCharacter>();
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
        Vector<GameCharacter> areaCharacters = new Vector<GameCharacter>();
        
      return new World(terrain, random.nextInt(8) + 1 == 8, areaItems, areaCharacters);
    }
}
