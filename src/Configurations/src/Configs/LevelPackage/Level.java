package Configs.LevelPackage;

import Configs.*;
import Configs.ArsenalConfig.Arsenal;
import Configs.Behaviors.Behavior;
import Configs.EnemyPackage.Enemy;
import Configs.GamePackage.Game;
import Configs.MapPackage.MapConfig;
import Configs.Waves.Wave;
import Configs.ArsenalConfig.Weapon;
import org.w3c.dom.events.Event;

import java.util.List;

public class Level implements Configurable, Viewable, EventHandlable, Updatable {
    private List<Weapon> activeWeapons;
    private List<Enemy> activeEnemies;
    private Game myGame;

    @Configure
    private Wave[] myWaves;
    @Configure
    private Arsenal myArsenal;
    @Configure
    private int gridSizeX;
    @Configure
    private int gridSizeY;
    @Configure
    private MapConfig myMap;
    @Configure
    private Behavior<Level>[] levelBehaviors;


    public Level(Game game){
        myGame = game;
    }

    @Override
    public void handleEvent(Event e) {

    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<ImmutableImageView> getViewsToBeAdded() {
        return null;
    }

    @Override
    public List<ImmutableImageView> getViewsToBeRemoved() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
