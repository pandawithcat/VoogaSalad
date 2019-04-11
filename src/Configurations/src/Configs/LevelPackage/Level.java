package Configs.LevelPackage;

import Configs.*;
import Configs.ArsenalConfig.Arsenal;
import Configs.GamePackage.Game;
import Configs.LevelPackage.LevelBehaviors.LevelBehavior;
import Configs.MapPackage.MapConfig;
import Configs.Waves.WaveConfig;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Level implements Configurable{
    private Game myGame;

    @Configure
    @XStreamOmitField
    private String myLabel;
    @Configure
    @XStreamOmitField
    private WaveConfig[] myWaveConfigs;
    @Configure
    @XStreamOmitField
    private Arsenal myArsenal;
    @Configure
    @XStreamOmitField
    private MapConfig myMap;
    @Configure
    @XStreamOmitField
    private LevelBehavior[] levelBehaviors;
    @XStreamOmitField
    private Configuration myConfiguration;


    public Level(Game game){
        myGame = game;
        myConfiguration = new Configuration(this);
    }

    public Level(Level level){
        myWaveConfigs = level.getMyWaveConfigs();
        myArsenal = level.getMyArsenal();
        myMap = level.getMyMapConfig();
        levelBehaviors = level.getLevelBehaviors();
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

    private LevelBehavior[] getLevelBehaviors() {
        return levelBehaviors;
    }

    public MapConfig getMyMapConfig() {
        return myMap;
    }

    protected WaveConfig[] getMyWaveConfigs() {
        return myWaveConfigs;
    }

    @Override
    public String getLabel() {
        return myLabel;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    public Game getGame(){
        return myGame;
    }


}
