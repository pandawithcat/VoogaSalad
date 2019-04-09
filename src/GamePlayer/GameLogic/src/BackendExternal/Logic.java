package BackendExternal;

import Configs.GamePackage.Game;
import Configs.ImmutableImageView;
import Configs.TransferImageView;
import Data.GameLibrary;

import java.util.EventObject;
import java.util.List;

/**
 * Project 4: VoogaSalad
 * Duke CompSci 308 Spring 2019 - Duvall
 * Date Created: 4/4/2019
 * Date Last Modified: 4/4/2019
 * @author Brian Jordan
 */

public class Logic {
//     TODO: Second Sprint
//     private UserAuthenticator myUserAuthenticator;
//     private myUserGameData;

    private Game myGame;
    private GameLibrary myGameLibrary;

    public Logic() {
//        myUserAuthenticator = new UserAuthenticator();
        myGameLibrary = new GameLibrary();

    }


    // View will call this first to get the name and thumbnail file name of each game
    // No Input
    // Return: List of GameInfo objects
    public List<GameInfo> getGameOptions(){
        return myGameLibrary.getImmutableGameList();
    }

//    TODO: Implement User Authentification in Second Sprint
//    UserData authenticateUser(String userName, String userPassword) throws IllegalAccessError{
//
//    }

    // First Sprint Version
    // View calls this when user select a game to play
    // Input: Selected GameInfo Object
    // No Return Value
    public void createGameInstance(GameInfo selectedGame) {
        myGame = myGameLibrary.getGame(selectedGame);
    }

    // View calls to get the current level of the game when moving between levels
    // No Input
    // Return: integer Level
//    public int getLevelNum(){
//        return myGame.getLevel();
//    }


    // View calls this when the user presses play or level is over
    // No Input
    // Return: List of Viewable instances of static level items
    // TODO: Adjust the type that is returned to make it as encapsulated as possible
//    public List<TransferImageView> getLevelTerrain(){
//
//    }

    // View call this when the user presses play or a level is over
    // Return: ID and image file of available weapons
//    public Map<Integer,Info> getArsenal(){
//
//    }

    // View calls this when a weapon is placed onto the map
    // Input: WeaponInfo Object
    // Return: ImageView corresponding to the weapon
    // TODO: Adjust the type that is returned to make it as encapsulated as possible
//    public TransferImageView instantiateWeapon(int weaponID){
//
//    }

    // View calls to update the state of the Dynamic parts of the level in the game loop
    // Input: Time the method is called
    // No Return
    public void update(double currentTime){

    }

    // View calls to check the current score of the game in the game loop
    // No Input
    // Return: integer score
//    public int getScore(){
//        return myGame.getScore();
//    }

    // View calls to check the current lives of the game in the game loop
    // No Input
    // Return: integer lives
//    public int getNumLives(){
//        return myGame.getLives();
//    }

    // View calls to check the current lives of the game in the game loop
    // No Input
    // Return: integer lives
//    public int getNumLives(){
//        return myGame.getLives();
//    }


    // View calls to check if a location is valid to place a weapon
    // Input: WeaponInfo object, x and y coordinate
    // Return: boolean
//    public boolean checkPlacementLocation(WeaponInfo movingWeapon, double x, double y){
//
//    }

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

        return false;
    }


}
