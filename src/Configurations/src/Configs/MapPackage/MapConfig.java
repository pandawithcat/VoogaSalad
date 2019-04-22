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
    public static final String myLabel = "Map";
    @Configure
    private String myName;
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
        return gridHeight*TERRAIN_SIZE;
    }

    public int getGridWidth() {
        return gridWidth*TERRAIN_SIZE;
    }


    @Override
    public String getName() {
        return myName;
    }

}
