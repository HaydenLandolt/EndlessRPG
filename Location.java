
public class Location
{
    private TerrainType terrain;

    /**
     * Constructor for objects of class Location
     */
    public Location(TerrainType terrain)
    {
        this.terrain = terrain;
    }
    
    public TerrainType getTerrain(){
     return terrain;   
    }
}
