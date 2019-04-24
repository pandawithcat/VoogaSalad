package ExternalAPIs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerData extends Data{

    public PlayerData(){
        super();
    }

    /**
     * Returns a list of all the game that have been created and are stored in the database
     * @return - List of authored games, Null if no games have been authored yet
     */
    @Override
    public List<GameInfo> getAuthoredGames() {
            List<GameInfo> games = new ArrayList<>();
            // TODO: Change loop to go through all games list
            for (int i = 0; i < 1; i++){
                // TODO: Fetch game info - title, thumbnail imageID, description
                GameInfo nextGame = new GameInfo("Title", "Thumbnail.jpeg", "Description");
                games.add(nextGame);
            }
            // TODO: Download Image files with matching IDs to the local machine to display
            return Collections.unmodifiableList(games);
    }

    /**
     * Uses the user ID and current game to query the database for the level and score where the user left off
     * @return - User state object corresponding to current user and game
     */
    public UserState getCurrentUserState(){
        currentUserID = currentUserID;
        currentGameID = currentGameID;
        // TODO: Use these to query into user set and games played
        int currentLevel = 0;
        int currentScore = 0;

        UserState playerState = new UserState(currentLevel, currentScore);

        return playerState;
    }

    /**
     * Stores the current level and score of the user when they exit the game
     * @param savingState - User state object corresponding to current user and game
     */
    public void saveUserState(UserState savingState){
        currentUserID = currentUserID;
        currentGameID = currentGameID;
        // TODO: Use these to query into user set and games played

        int currentLevel = savingState.getMyCurrentLevel();
        int currentScore = savingState.getMyCurrentScore();

        // TODO: Store Data in database


    }


}
