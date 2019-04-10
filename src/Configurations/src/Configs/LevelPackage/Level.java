package Configs.LevelPackage;

import Configs.*;
import Configs.ArsenalConfig.Arsenal;
<<<<<<< HEAD
import Configs.Behaviors.Behavior;
import Configs.EnemyPackage.Enemy;
import Configs.GamePackage.Game;
import Configs.MapPackage.MapConfig;
import Configs.Waves.Wave;
import Configs.ArsenalConfig.Weapon;
import org.w3c.dom.events.Event;

import java.util.List;
=======
import Configs.GamePackage.Game;
import Configs.MapPackage.MapConfig;
import Configs.Waves.WaveConfig;
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860

public class Level implements Configurable, Viewable, EventHandlable, Updatable {
    private List<Weapon> activeWeapons;
    private List<Enemy> activeEnemies;
    private Game myGame;


    @Configure
    private WaveConfig[] myWaveConfigs;
    @Configure
    private Arsenal myArsenal;
    @Configure
    private MapConfig myMap;
//    @Configure
//    protected Behavior<Level>[] levelBehaviors;
    private Configuration myConfiguration;


    public Level(Game game){
        myGame = game;
        myConfiguration = new Configuration(this);
    }

<<<<<<< HEAD
    @Override
    public void handleEvent(Event e) {

    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<View> getViews() {
        return null;
=======
    public Level(Level level){
        myWaveConfigs = level.getMyWaveConfigs();
        myArsenal = level.getMyArsenal();
        myMap = level.getMyMapConfig();
//        levelBehaviors = level.getLevelBehaviors();
    }

    public void setMyGame(Game myGame) {
        this.myGame = myGame;
    }

    protected Game getMyGame() {
        return myGame;
    }


    public Arsenal getMyArsenal() {
        return myArsenal;
    }

//    private Behavior<Level>[] getLevelBehaviors() {
//        return levelBehaviors;
//    }

    public MapConfig getMyMapConfig() {
        return myMap;
    }

    protected WaveConfig[] getMyWaveConfigs() {
        return myWaveConfigs;
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
<<<<<<< HEAD
=======

    public Game getGame(){
        return myGame;
    }


>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
}
