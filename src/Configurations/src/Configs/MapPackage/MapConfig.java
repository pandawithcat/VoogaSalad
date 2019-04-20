package Configs.MapPackage;

import Configs.Configurable;
import Configs.Configuration;
import Configs.LevelPackage.Level;
import Configs.MapPackage.Terrain;

import java.util.Collection;
import java.util.List;

import static Configs.MapPackage.Terrain.TERRAIN_SIZE;

public class MapConfig implements Configurable {
    //for the game player frontend to easily display terrain
    private String myLabel;
    @Configure
    private String name;
    @Configure
    private List<Terrain> myTerrain;
    @Configure
    private int enemyEnteringGridXPos;
    @Configure
    private int enemyEnteringGridYPos;
    @Configure
    private int enemyEnteringDirection;
    @Configure
    private int enemyExitGridXPos;
    @Configure
    private int enemyExitGridYPos;
    @Configure
    private int gridHeight;
    @Configure
    private int gridWidth;

//    @Configure
//    protected double gridPixelWidth;
//    @Configure
//    protected double gridPixelHeight;

    private Configuration configuration;
    private Level myLevel;

    public MapConfig(Level level) {
        configuration = new Configuration(this);
        myLevel = level;

    }

    public Level getLevel() {
        return myLevel;
    }

    public List<Terrain> getTerrain() {
        return myTerrain;
    }


    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    public int getEnemyEnteringDirection() {
        return enemyEnteringDirection*TERRAIN_SIZE;
    }

    public int getEnemyEnteringGridXPos() {
        return enemyEnteringGridXPos*TERRAIN_SIZE;
    }

    public int getEnemyEnteringGridYPos() {
        return enemyEnteringGridYPos*TERRAIN_SIZE;
    }

    public int getEnemyExitGridXPos() {
        return enemyExitGridXPos*TERRAIN_SIZE;
    }

    public int getEnemyExitGridYPos() {
        return enemyExitGridYPos*TERRAIN_SIZE;
    }

    public int getGridHeight() {

        System.out.println("HEIGHT");
        System.out.println(gridHeight);

        return gridHeight*TERRAIN_SIZE;
    }

    public int getGridWidth() {

        System.out.println("WIDTH");
        System.out.println(gridWidth);



        return gridWidth*TERRAIN_SIZE;
    }

    public void setTerrainList(List<Terrain> terrain) {
        myTerrain = terrain;
        for(Terrain t : terrain) {

        }
    }

    @Override
    public String getLabel() {
        return myLabel;
    }

}
