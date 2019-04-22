package Configs.GamePackage;

import ActiveConfigs.ActiveLevel;
import ActiveConfigs.ActiveWeapon;
import ActiveConfigs.LevelSpawner;
import Configs.*;
import Configs.ArsenalConfig.Arsenal;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Behaviors.Behavior;
import Configs.GamePackage.GameBehaviors.GameBehavior;
import Configs.LevelPackage.Level;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.w3c.dom.events.Event;

import java.util.Arrays;


public class Game implements Updatable, Configurable {

    public static final double gridPixelWidth = 585;
    public static final double gridPixelHeight = 585;

    private Configuration myConfiguration;

    public static final String myLabel = "Game";

    @Configure
    private String myName;
    @Configure
    private String myDescription;
    @Configure
    private String myThumbnail;
    @Configure
    private Level[] levelList;
    @Configure
    private Arsenal myArsenal;
    @Configure
    private GameBehavior gameType;
    /*@Configure
    private WeaponConfig[] allWeaponConfigs;*/

    private LevelSpawner myLevelSpawner;
    private int currentLevelNumber;
    private boolean gameOver;
    private boolean currentLevelOver;

    public Game(){
        myConfiguration = new Configuration(this);
        gameOver = false;
        currentLevelNumber=0;
    }

    public Arsenal getArsenal() {
        return myArsenal;
    }

    @Override
    public void update(double ms) {
        if(myLevelSpawner.isGameOver()) gameOver = true;
        else {
            myLevelSpawner.update(ms);
        }
    }


    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isLevelOver() {
        return currentLevelOver;
    }


    public void startGame(int levelNumber) throws IllegalStateException{
        if(levelNumber>=levelList.length) {
            throw new IllegalStateException();
        }
        currentLevelNumber = levelNumber;
        // TODO: CHANGE LAMBDA BASED ON THE GAME MODE
        this.myLevelSpawner = new LevelSpawner(levelNumber, levelList, activeLevel -> activeLevel.noMoreEnemiesLeft());

    }

    public LevelSpawner getLevelSpawner() {
        return myLevelSpawner;
    }


    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    public ActiveLevel getActiveLevel() {
        return myLevelSpawner.getCurrLevel();
    }

    public String getTitle(){
        return myName;
    }

    public String getDescription(){
        return myDescription;
    }

    public String getThumbnail(){
        return myThumbnail;
    }


    @Override
    public String getName() {
        return myName;
    }
}
