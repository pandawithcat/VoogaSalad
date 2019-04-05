package BackendExternal;

import Data.GameInfo;
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

//    private Game myGame;
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
    // Return: String game description to display in the splash screen
    public String createGameInstance(GameInfo selectedGame) {
        Game myGame =
    }

    // TODO: Not completely necessary
    // This method would return the game objects description string to display in a Splash Screen
    public String getGameDescription(){

    }


    // View calls this when the user presses play or level is over
    // No Input
    // Return: List of Viewable instances of static level items
    public List<Viewable> getLevelStaticView(){

    }

    // View calls this when there is an event in the frontend that needs to be resolved in backend
    // Input: Event
    // No return
    public void handleEvent(EventObject event){

    }

    // View calls to update the state of the Dynamic parts of the level in the game loop
    // Input: Time the method is called
    // No Return
    public void update(double currentTime){

    }

    // View calls to get the Viewable instances of dynamic items in the game loop
    // No input
    // Return: List of Viewable instances of dynamic level items
    public List<Viewable> getViewUpdates(){

    }

    // View calls this in game Loop to check if the level has ended
    // No input
    // Return: Boolean value indicating the status of the running level
    boolean checkIfLevelEnd(){

        return false;
    }


}
