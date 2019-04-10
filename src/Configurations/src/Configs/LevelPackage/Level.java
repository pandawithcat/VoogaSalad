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

    protected Game getMyGame() {
        return myGame;
    }

    protected Arsenal getMyArsenal() {
        return myArsenal;
    }

//    private Behavior<Level>[] getLevelBehaviors() {
//        return levelBehaviors;
//    }

    public MapConfig getMyMap() {
        return myMap;
    }

    protected WaveConfig[] getMyWaveConfigs() {
        return myWaveConfigs;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    public Game getGame(){
        return myGame;
    }


}
