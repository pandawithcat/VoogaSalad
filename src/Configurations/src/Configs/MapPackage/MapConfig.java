package Configs.MapPackage;

import Configs.Configurable;
import Configs.Configuration;
import Configs.LevelPackage.Level;
import Configs.MapPackage.Terrain;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.awt.*;
import java.util.Collection;
import java.util.List;

import static Configs.MapPackage.Terrain.TERRAIN_SIZE;

public class MapConfig implements Configurable {
    public static final String DISPLAY_LABEL = "Map";
    public static final int GRID_HEIGHT = 20;
    public static final int GRID_WIDTH = 32;
    @Configure
    private String myName;
    @Configure
    private List<Terrain> myTerrain;
    @Configure
    private List<Point> enemyEnteringGridPosList;
    @Configure
    private int enemyEnteringDirection;
    @Configure
    private List<Point> enemyExitGridPosList;


    @XStreamOmitField
    private transient Configuration configuration;
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

    public List<Point> getEnemyEnteringGridPosList() {
        for(int a=0 ; a< enemyEnteringGridPosList.size(); a++){
             Point b = enemyEnteringGridPosList.get(a);
             Point newPoint = new Point((int)b.getX() * TERRAIN_SIZE, (int)b.getY() * TERRAIN_SIZE);
             enemyEnteringGridPosList.set(a,newPoint);
        }
        return enemyEnteringGridPosList;
    }

    public List<Point> getEnemyExitGridPosList() {
        for(int a=0 ; a< enemyExitGridPosList.size(); a++){
            Point b = enemyExitGridPosList.get(a);
            Point newPoint = new Point((int)b.getX() * TERRAIN_SIZE, (int)b.getY() * TERRAIN_SIZE);
            enemyExitGridPosList.set(a,newPoint);
        }

        return enemyExitGridPosList;
    }


    public int getGridHeight() {
        return GRID_HEIGHT*TERRAIN_SIZE;
    }

    public int getGridWidth() {
        return GRID_WIDTH*TERRAIN_SIZE;
    }


    @Override
    public String getName() {
        return myName;
    }

}
