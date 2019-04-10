package Configs.GamePackage;

import ActiveConfigs.ActiveLevel;
import Configs.*;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Behaviors.Behavior;
import Configs.LevelPackage.Level;
import org.w3c.dom.events.Event;

import java.util.List;
import java.util.stream.Collectors;

public class Game implements Updatable, EventHandlable, Configurable {

    private Configuration myConfiguration;

    @Configure
    protected Level[] levelList;
    @Configure
    protected Behavior<Game>[] gameType;
    @Configure
    protected WeaponConfig[] allWeaponConfigs;
    @Configure
    protected String myTitle;
    @Configure
    protected String myDescription;
    @Configure
    protected String myThumbnail;

    private ActiveLevel myActiveLevel;
    private int currentLevelNumber;
    private boolean gameOver;
    private boolean currentLevelOver;

    public Game(){
        myConfiguration = new Configuration(this);
        gameOver = false;
    }

    @Override
    public void update(long ms) {
        //TODO CHECK IF LEVEL IS OVER AND CHANGE currentLevelOver = true;
        //TODO CHECK IF GAME IS OVER
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isLevelOver() {
        return currentLevelOver;
    }

    public void startGame(int startingLevel) {
        currentLevelNumber = startingLevel;
        currentLevelOver = false;
    }

    public int startNextLevel() throws IllegalStateException{
        if(gameOver) throw new IllegalStateException();
        currentLevelNumber++;
        currentLevelOver = false;
        return currentLevelNumber;
    }

    @Override
    public void handleEvent(Event e) {

    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    public ActiveLevel getActiveLevel() {
        return myActiveLevel;
    }

    public void setMyActiveLevel(int levelIndex) {
        ActiveLevel activeLevel = new ActiveLevel(levelList[levelIndex]);

    }

    public String getTitle(){
        return myTitle;
    }

    public String getDescription(){
        return myDescription;
    }

    public String getThumbnail(){
        return myThumbnail;
    }






}
