package Configs.LevelPackage;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.EnemyPackage.Enemy;
import Configs.WeaponsConfig.Weapon;
import Configs.Waves.WaveSpawner;
import org.w3c.dom.events.Event;

import java.util.List;
import java.util.Map;

public class Level implements Configurable, Viewable, EventHandlable, Updatable {
    List<Behavior<Level>> levelBehaviors;
    List<Weapon> activeWeapons;
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
