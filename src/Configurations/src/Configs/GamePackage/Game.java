package Configs.GamePackage;

import ActiveConfigs.ActiveLevel;
import ActiveConfigs.ActiveWeapon;
import Configs.*;
import Configs.ArsenalConfig.Arsenal;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Behaviors.Behavior;
import Configs.GamePackage.GameBehaviors.GameBehavior;
import Configs.LevelPackage.Level;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.w3c.dom.events.Event;

import java.util.Arrays;


public class Game implements Updatable, EventHandlable, Configurable {

    public static final double gridPixelWidth = 585;
    public static final double gridPixelHeight = 585;

    private Configuration myConfiguration;

    @Configure
    private String myTitle;
    @Configure
    private String myDescription;
    @Configure
    private String myThumbnail;
    @Configure
    private Level[] levelList;
    @Configure
    private Arsenal myArsenal;
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

    public Arsenal getArsenal() {
        return myArsenal;
    }

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

    //TODO: EventHandler for adding new weapon to map
    public TransferImageView generateNewWeapon(int ID, double pixelX, double pixelY){
        WeaponConfig myWeaponConfig = getMyArsenal().getConfiguredWeapons()[ID-1];
        ActiveWeapon activeWeapon = new ActiveWeapon(myWeaponConfig, new MapFeature(pixelX, pixelY, 0, myWeaponConfig.getView(),gridHeight, gridWidth), this);
        activeWeapon.getMapFeature().setDisplayState(DisplayState.NEW);
        addToActiveWeapons(activeWeapon);
        return activeWeapon.getMapFeature().getImageView();
    }

    public Level[] getLevelList() {
        return levelList;
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
        setMyActiveLevel(levelNumber);//TODO check this logic

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
        System.out.println(Arrays.toString(levelList));
        System.out.println(levelIndex);
        System.out.println(levelList[levelIndex]);
        myActiveLevel = new ActiveLevel(levelList[levelIndex]);

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
