package Configs.GamePackage;

import ActiveConfigs.ActiveLevel;
import Configs.*;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Behaviors.Behavior;
import Configs.GamePackage.GameBehaviors.GameBehavior;
import Configs.LevelPackage.Level;
import org.w3c.dom.events.Event;

<<<<<<< HEAD
public class Game implements Updatable, EventHandlable, Viewable, Configurable {
=======
public class Game implements Updatable, EventHandlable, Configurable {

    public static final double gridPixelWidth = 500;
    public static final double gridPixelHeight = 500;

    private Configuration myConfiguration;
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860

    @Configure
    private String myTitle;
    @Configure
    private String myDescription;
    @Configure
    private String myThumbnail;
    @Configure
    private Level[] levelList;
    @Configure
    private GameBehavior[] gameType;
    @Configure
    private WeaponConfig[] allWeaponConfigs;

    private ActiveLevel myActiveLevel;
    private int currentLevelNumber;
    private boolean gameOver;
    private boolean currentLevelOver;

    public Game(){
        myConfiguration = new Configuration(this);
        gameOver = false;
        currentLevelNumber=0;
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


    public void startGame(int levelNumber) throws IllegalStateException{
        if(levelNumber>=levelList.length) {
            throw new IllegalStateException();
        }
        currentLevelNumber = levelNumber;

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

    @Override
    public String getLabel() {
        return myTitle;
    }
}
