package Configs.LevelPackage;

import Configs.*;
import Configs.GamePackage.Game;
import Configs.LevelPackage.LevelBehaviors.LevelBehavior;
import Configs.MapPackage.MapConfig;
import Configs.Waves.Wave;


public class Level implements Configurable{
    private Game myGame;
    public static final String DISPLAY_LABEL = "Level";
    @Configure
    private String myName;
    @Configure
    private Wave[] myWaves;
    @Configure
    private MapConfig myMap;

    @Configure
    private LevelBehavior[] levelBehaviors;
    private Configuration myConfiguration;

    public Level(Game game) {
        myGame = game;
        myConfiguration = new Configuration(this);
    }

    public Level(Level level){
        myWaves = level.getMyWaves();
        myMap = level.getMyMapConfig();
        levelBehaviors = level.getLevelBehaviors();
        myName = level.myName;
    }

    protected Game getMyGame() {
        return myGame;
    }


    private LevelBehavior[] getLevelBehaviors() {
        return levelBehaviors;
    }

    public MapConfig getMyMapConfig() {
        return myMap;
    }

    protected Wave[] getMyWaves() {
        return myWaves;
    }

    @Override
    public String getName() {
        return myName;
    }
    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    public Game getGame(){
        return myGame;
    }


}
