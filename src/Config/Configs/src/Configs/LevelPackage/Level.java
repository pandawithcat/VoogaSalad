package Configs.LevelPackage;

import Configs.*;
import Configs.weaponsConfigPackage.WeaponConfig;
import org.w3c.dom.events.Event;

import java.util.List;

public class Level implements Configurable, Viewable, EventHandlable, Updatable {
    List<LevelBehavior> levelBehaviors;
    List<WeaponConfig> activeWeapons;
    List<Enemy> activeEnemies;
    WaveSpawner myWaveSpawner;
    Arsenal myArsenal;
    int gridSizeX;
    int gridSizeY;
    Cell[][] myGrid;

    public Level(){

    }


    @Override
    public void handleEvent(Event e) {

    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<View> getViews() {
        return null;
    }
}
