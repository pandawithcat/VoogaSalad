package Configs.GamePackage;

import ActiveConfigs.ActiveLevel;
import Configs.*;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Behaviors.Behavior;
import Configs.GamePackage.GameBehaviors.GameBehavior;
import Configs.LevelPackage.Level;
import org.w3c.dom.events.Event;

public class Game implements Updatable, EventHandlable, Configurable {

    public static final double gridPixelWidth = 500;
    public static final double gridPixelHeight = 500;

    private Configuration myConfiguration;

    @Configure
    private String myTitle;
    @Configure
    private String myDescription;
    @Configure
    private String myThumbnail;
    @Configure
    private Level[] levelList;
    /*@Configure
    private GameBehavior[] gameType;*/
    /*@Configure
    private WeaponConfig[] allWeaponConfigs;*/

    private ActiveLevel myActiveLevel;
    private int currentLevelNumber;
    private boolean gameOver;
    private boolean currentLevelOver;

    public Game(){
        myConfiguration = new Configuration(this);
        gameOver = false;
        currentLevelNumber=0;
    }

<<<<<<< HEAD
=======
    //FOR TESTING
    public void setName(String name) {
        myTitle = name;
    }
    public void setThumbnail(String name) {
        myThumbnail = name;
    }
    public void setMyDescription(String name) {
        myDescription = name;
    }
>>>>>>> e11763d259370ab5512cb002562f786bd4ef0f50

    @Override
    public void update(long ms) {
        myActiveLevel.update(ms);
        if(myActiveLevel.noMoreEnemiesLeft()) {
            currentLevelOver = true;
            currentLevelNumber++;
            if(currentLevelNumber==levelList.length) {
                gameOver = true;
            }
            else {
                myActiveLevel = new ActiveLevel(levelList[currentLevelNumber-1]);
            }
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
