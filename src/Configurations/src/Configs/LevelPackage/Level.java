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
    private int gridSizeX;
    @Configure
    private int gridSizeY;
    @Configure
    private MapConfig myMap;
    @Configure
    private Behavior<Level>[] levelBehaviors;
    private Configuration myConfiguration;

    public Level(Game game){
        myGame = game;
        myConfiguration = new Configuration(this);
    }

    public Level(Level level){
        myWaveConfigs = level.getMyWaveConfigs();
        myArsenal = level.getMyArsenal();
        gridSizeX = level.getGridSizeX();
        gridSizeY = level.getGridSizeY();
        myMap = level.getMyMap();
        levelBehaviors = level.getLevelBehaviors();
    }

    public void setMyGame(Game myGame) {
        this.myGame = myGame;
    }

    private Game getMyGame() {
        return myGame;
    }

    private Arsenal getMyArsenal() {
        return myArsenal;
    }

    private Behavior<Level>[] getLevelBehaviors() {
        return levelBehaviors;
    }

    private int getGridSizeX() {
        return gridSizeX;
    }

    private int getGridSizeY() {
        return gridSizeY;
    }

    private MapConfig getMyMap() {
        return myMap;
    }

    private WaveConfig[] getMyWaveConfigs() {
        return myWaveConfigs;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    public Configurable getParent(){
        return myGame;
    }
}
