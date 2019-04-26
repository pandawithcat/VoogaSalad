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

    private transient Configuration myConfiguration;

    public static final String DISPLAY_LABEL = "Game";

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

    @XStreamOmitField
    private transient double paneWidth;
    @XStreamOmitField
    private transient double paneHeight;

    @XStreamOmitField
    private transient LevelSpawner myLevelSpawner;
    @XStreamOmitField
    private transient boolean gameOver;
    @XStreamOmitField
    private transient boolean gameWon;


    public Game(){
        myConfiguration = new Configuration(this);
        gameOver = false;
    }

    public Arsenal getArsenal() {
        return myArsenal;
    }

    @Override
    public void update(double ms, Updatable parent) {
        if(myLevelSpawner.isGameOver()) gameOver = true;
        else {
            myLevelSpawner.update(ms, this);
        }
    }


    public void setGameOver() {
        gameOver = true;
    }

    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public boolean isGameOver() {
        return gameOver;
    }


    public void startGame(int levelNumber, double paneWidth, double paneHeight) throws IllegalStateException{
        if(levelNumber>=levelList.length) {
            throw new IllegalStateException();
        }
        this.paneHeight = paneHeight;
        this.paneWidth = paneWidth;
        this.myLevelSpawner = new LevelSpawner(this, levelNumber, levelList, activeLevel -> activeLevel.noMoreEnemiesLeft());
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

    public double getPaneWidth() {
        return paneWidth;
    }

    public double getPaneHeight() {
        return paneHeight;
    }

    @Override
    public String getName() {
        return myName;
    }
}
