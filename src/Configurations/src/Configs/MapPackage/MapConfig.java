package Configs.MapPackage;

import Configs.Configurable;
import Configs.Configuration;
import Configs.MapPackage.Terrain;

import java.util.List;

public class MapConfig implements Configurable {
    //for the game player frontend to easily display terrain
    @Configure
    private List<Terrain> myTerrain;
    @Configure
    private int enemyEnteringGridXPos;
    @Configure
    private int enemyEnteringGridYPos;
    @Configure
    private int enemyEnteringDirection;
    @Configure
    private int gridHeight;
    @Configure
    private int gridWidth;
    @Configure
    private double gridPixelWidth;
    @Configure
    private double gridPixelHeight;

    private Configuration configuration;

    public List<Terrain> getTerrain() {
        return myTerrain;
    }


    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

    public int getEnemyEnteringDirection() {
        return enemyEnteringDirection;
    }

    public int getEnemyEnteringGridXPos() {
        return enemyEnteringGridXPos;
    }

    public int getEnemyEnteringGridYPos() {
        return enemyEnteringGridYPos;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public void setTerrainList(List<Terrain> terrain) {
        myTerrain = terrain;
        for(Terrain t : terrain) {

        }
    }

}
