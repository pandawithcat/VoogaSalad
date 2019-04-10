package Configs.GamePackage;

<<<<<<< HEAD
import Configs.EventHandlable;
=======
import ActiveConfigs.ActiveLevel;
import Configs.*;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Behaviors.Behavior;
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
import Configs.LevelPackage.Level;
import Configs.Updatable;
import Configs.View;
import Configs.Viewable;
import org.w3c.dom.events.Event;

public class Game implements Updatable, EventHandlable, Configurable {

<<<<<<< HEAD
public class Game implements Updatable, EventHandlable, Viewable {

    List<Level> levelList;
    GameOptions gameType;
=======
    private Configuration myConfiguration;

    @Configure
    private Level[] levelList;
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
        currentLevelNumber=0;
    }
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860

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

<<<<<<< HEAD
    @Override
    public List<View> getViews() {
        return null;
    }

=======
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

>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860





}
