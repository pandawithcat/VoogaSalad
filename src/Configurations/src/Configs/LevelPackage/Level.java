package Configs.LevelPackage;

import Configs.*;
import Configs.ArsenalConfig.Arsenal;
import Configs.Behaviors.Behavior;
import Configs.GamePackage.Game;
import Configs.MapPackage.MapConfig;
import Configs.Waves.WaveConfig;

public class Level implements Configurable{

    private Game myGame;


    @Configure
    private WaveConfig[] myWaveConfigs;
    @Configure
    private Arsenal myArsenal;
    @Configure
    private MapConfig myMap;
//    @Configure
//    private Behavior<Level>[] levelBehaviors;
    private Configuration myConfiguration;
    private int currentWave=0;

    public Level(Game game){
        myGame = game;
        myConfiguration = new Configuration(this);
    }

    public Level(Level level){
        myWaveConfigs = level.getMyWaveConfigs();
        myArsenal = level.getMyArsenal();
        myMap = level.getMyMap();
//        levelBehaviors = level.getLevelBehaviors();
    }

    public void setMyGame(Game myGame) {
        this.myGame = myGame;
    }


    private Game getMyGame() {
        return myGame;
    }

    public Arsenal getMyArsenal() {
        return myArsenal;
    }

//    private Behavior<Level>[] getLevelBehaviors() {
//        return levelBehaviors;
//    }

    public MapConfig getMyMap() {
        return myMap;
    }

    private WaveConfig[] getMyWaveConfigs() {
        return myWaveConfigs;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    public Game getParent(){
        return myGame;
    }


}
