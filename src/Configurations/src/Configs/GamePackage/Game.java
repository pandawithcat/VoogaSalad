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
    Level[] levelList;
    @Configure
    private Behavior<Game>[] gameType;
    @Configure
    private WeaponConfig[] allWeaponConfigs;
    @Configure
    private String myTitle;
    @Configure
    private String myDescription;
    @Configure
    private String myThumbnail;

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

    public void startGame() {
        currentLevelNumber = 1;
        currentLevelOver = false;
    }

    public void startNextLevel() throws IllegalStateException{
        if(gameOver) throw new IllegalStateException();
        currentLevelNumber++;
        currentLevelOver = false;
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
