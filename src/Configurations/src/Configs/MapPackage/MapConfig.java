package Configs.MapPackage;

import Configs.Configurable;
import Configs.Configuration;
import Configs.MapPackage.Terrain;

import java.util.List;

public class MapConfig implements Configurable {
    //for the game player frontend to easily display terrain
    @Configure
    protected List<Terrain> myTerrain;
    @Configure
    protected int enemyEnteringGridXPos;
    @Configure
    protected int enemyEnteringGridYPos;
    @Configure
    protected int enemyEnteringDirection;
    @Configure
    protected int gridHeight;
    @Configure
    protected int gridWidth;
    @Configure
    protected double gridPixelWidth;
    @Configure
    protected double gridPixelHeight;

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
