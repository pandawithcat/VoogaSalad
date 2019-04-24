package BackendExternal;

import ActiveConfigs.Cell;
import Configs.*;
import Configs.GamePackage.Game;
import Configs.MapPackage.Terrain;
import Data.GameLibrary;
import ExternalAPIs.GameInfo;
import ExternalAPIs.PlayerData;
import ExternalAPIs.UserState;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Project 4: VoogaSalad
 * Duke CompSci 308 Spring 2019 - Duvall
 * Date Created: 4/4/2019
 * Date Last Modified: 4/4/2019
 * @author Brian Jordan
 */

public class Logic {

    private static final int DEFAULT_START_LEVEL = 0;
    private final double PANE_WIDTH;
    private final double PANE_HEIGHT;



    private Game myGame;
    private GameLibrary myGameLibrary;
    private PlayerData myPlayerData;


    public Logic(double paneWidth, double paneHeight) {
//        myUserAuthenticator = new UserAuthenticator();
        myGameLibrary = new GameLibrary();
        myPlayerData = new PlayerData();
        PANE_WIDTH = paneWidth;
        PANE_HEIGHT = paneHeight;

    }

    // Do Not Call Yet !!!!!!!!!!!!!!!
    public boolean authenticateUser(String username, String password){
        return myPlayerData.authenticateUser(username, password);
    }

    // Do Not Call Yet !!!!!!!!!!!!!!!
    public void createNewUser(String username, String password, String passwordRepeated){
        myPlayerData.createNewUser(username, password, passwordRepeated);
    }

    // View will call this first to get the name and thumbnail file name of each game
    // No Input
    // Return: List of GameInfo objects
    public List<GameInfo> getGameOptions(){
        return myGameLibrary.getImmutableGameList();
    }

    // Final Implementation version of getGameOptions
    // Do Not Call Yet !!!!!!!!!!!!!!!
    public List<GameInfo> getGameOptions2(){
        return myPlayerData.getAuthoredGames();
    }


    // Do Not Call Yet !!!!!!!!!!!!!!!!
    public void createGameInstance2(GameInfo selectedGame){
        XStream serializer = new XStream(new DomDriver());
        String gameXMLString = myPlayerData.getGameFile(selectedGame);
        myGame =  (Game)serializer.fromXML(gameXMLString);
    }

    // Do Not Call Yet !!!!!!!!!!!!!!!!
    public void startAtUserState(){
        UserState gameState = myPlayerData.getCurrentUserState();
        myGame.getActiveLevel().setScore(gameState.getMyCurrentScore());
        myGame.startGame(gameState.getMyCurrentLevel(), PANE_WIDTH, PANE_HEIGHT);
    }

    // Do Not Call Yet !!!!!!!!!!!!!!!!
    public void startAtDefaultState(){
        myGame.startGame(DEFAULT_START_LEVEL, PANE_WIDTH, PANE_HEIGHT);
    }

    // View calls this when user select a game to play
    // Input: Selected GameInfo Object
    // No Return Value
    public void createGameInstance(GameInfo selectedGame, double paneWidth, double paneHeight) {
        myGame = myGameLibrary.getGame(selectedGame);
        // TODO: Second sprint have the option of getting this from User Data (Previous Level)
        myGame.startGame(DEFAULT_START_LEVEL, paneWidth, paneHeight);
    }

    // View calls to get the current level of the game when moving between levels
    // No Input
    // Return: integer Level number
    public int startNextLevel(){
        return myGame.getLevelSpawner().startNextLevel();
    }


    // View calls this when the user presses play or level is over
    // No Input
    // Return: List of Viewable instances of static level items
    public List<ImmutableImageView> getLevelTerrain(double screenWidth, double screenHeight){
        return myGame
                .getActiveLevel()
                .getMyMapConfig()
                .getTerrain()
                .stream()
                .map(terrain -> terrain.getImageView(screenWidth, screenHeight, myGame.getActiveLevel().getGridHeight(),myGame.getActiveLevel().getGridWidth()))
                .collect(Collectors.toList());

    }



    // View call this when the user presses play or a level is over
    // Return: ID and image file of available weapons
    public Map<Integer, Info> getMyArsenal(){
        return myGame.getArsenal().getAllWeaponConfigOptions();
    }

    // View calls this when a weapon is placed onto the map
    // Input: WeaponInfo Object
    // Return: ImageView corresponding to the weapon
    public ImmutableImageView instantiateWeapon(int weaponID, double xPixel, double yPixel, int direction){
        return myGame.getArsenal().generateNewWeapon(weaponID, xPixel, yPixel, direction);
    }

    // View calls to update the state of the Dynamic parts of the level in the game loop
    // Input: Time the method is called
    // No Return
    public void update(double currentTime){
        myGame.update(currentTime);
    }

    // View calls to get objects to add to the view
    // No Input
    // Return: List of Viewable instances
    public List<ImmutableImageView> getObjectsToAdd(){
        return myGame.getActiveLevel().getViewsToBeAdded();
    }

    // View calls to get objects to remove from the view
    // No Input
    // Return: List of Viewable instances
    public List<ImmutableImageView> getObjectsToRemove(){
        return myGame.getActiveLevel().getViewsToBeRemoved();
    }

    // View calls to check the current score of the game in the game loop
    // No Input
    // Return: integer score
    public int getScore(){
        return myGame.getActiveLevel().getMyScore();
    }

    // View calls to check the current lives of the game in the game loop
    // No Input
    // Return: integer lives
//    public int getNumLives(){
//        return myGame.getActiveLevel().ge;
//    }



    // View calls to check if a location is valid to place a weapon
    // Input: WeaponInfo object, x and y coordinate
    // Return: boolean
    public boolean checkPlacementLocation(int weaponId, double xPixel, double yPixel, int direction){
        View weaponView = myGame.getArsenal().getConfiguredWeapons()[weaponId-1].getView();
        int height;
        int width;
        if(direction==90||direction==270) {
            height = weaponView.getHeight();
            width = weaponView.getWidth();
        }
        else{
            height = weaponView.getWidth();
            width = weaponView.getHeight();
        }
        Cell[][] grid = myGame.getActiveLevel().getMyGrid();



        int x = (int) (xPixel/(myGame.getActiveLevel().getGridWidth()/myGame.getActiveLevel().getPaneWidth()));
        int y = (int) (yPixel/(myGame.getActiveLevel().getGridHeight()/myGame.getActiveLevel().getPaneHeight()));

        for(int col = x;col<x+width;col++) {
            for(int row = y;row<y+height;row++) {
                if (!grid[row][col].isValidWeaponPlacement()) return false;
            }
        }
        return true;
    }



    // View calls to move a dynamic object that has already been instantiated
    // Input: WeaponInfo object, x and y coordinate
    // No return
    // TODO: Second Sprint move objects mid level
//    public void placeMovingObject(WeaponInfo placedWeapon, double x, double y){
//
//    }

    // View calls this in game Loop to check if the level has ended
    // No input
    // Return: Boolean value indicating the status of the running level
    boolean checkIfLevelEnd(){
        return myGame.getLevelSpawner().isLevelOver();
    }

    boolean checkIfGameEnd(){
        return myGame.isGameOver();
    }

    // Do Not Call Yet !!!!!!!!!!!!!!!
    public void saveGameState(){
        UserState currentUserState = new UserState(myGame.getLevelSpawner().getLevelIndex(), myGame.getActiveLevel().getScore());
        myPlayerData.saveUserState(currentUserState);
    }


}
