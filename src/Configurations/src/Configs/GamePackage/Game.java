package Configs.GamePackage;

<<<<<<< HEAD

=======
>>>>>>> a0fbae9c7c0c83ef94a96e04f9852a12117d70ef
import ActiveConfigs.ActiveLevel;
import Configs.*;
import Configs.ArsenalConfig.Weapon;
import Configs.Behaviors.Behavior;
import Configs.LevelPackage.Level;
<<<<<<< HEAD
import Configs.Updatable;
=======
>>>>>>> a0fbae9c7c0c83ef94a96e04f9852a12117d70ef
import org.w3c.dom.events.Event;

public class Game implements Updatable, EventHandlable, Configurable {

<<<<<<< HEAD

=======
>>>>>>> a0fbae9c7c0c83ef94a96e04f9852a12117d70ef
    private Configuration myConfiguration;

    @Configure
    private Level[] levelList;
    @Configure
    private Behavior<Game>[] gameType;
    @Configure
    private Weapon.WeaponConfig[] allWeaponConfigs;
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

=======
>>>>>>> a0fbae9c7c0c83ef94a96e04f9852a12117d70ef
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
