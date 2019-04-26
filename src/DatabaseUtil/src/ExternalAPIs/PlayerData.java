package ExternalAPIs;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.scene.image.Image;

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
                GameInfo nextGame = new GameInfo("Title", 8, "Description");
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

        updateLeaderBoard(currentScore);

        // TODO: Store Data in database


    }

    private void updateLeaderBoard(int score){
        currentUserID = currentUserID;
        currentGameID = currentGameID;
        // TODO: Use this info to query into database to update the specified users high score in the leaderboard if it
        // is greater than the current saved entry
        // TODO: If the user is not in the leaderboard create an entry for them

        int prevHighScore = 0;

        if (score > prevHighScore){
            // TODO: Replace saved score with new one
        }
    }

    /**
     * Retrieves the specified number of leaders from the database for a particular game and passes their information in
     * a list of LeaderBoardEntry Objects
     * @param numberOfEntries - number of leaders to retrieve
     * @return - unmodifiable list of LeaderBoardEntry objects
     */
    public List<LeaderBoardEntry> compileLeaderboardEntries(int numberOfEntries){
        currentGameID = currentGameID;
        // TODO: using current game ID query for the highest "numberOfEntries" leader information
        ArrayList<LeaderBoardEntry> leaderInfo = new ArrayList<>();

        for (int i = 0; i < numberOfEntries; i++){
            String userName = new String();
            int score = 0;

            LeaderBoardEntry newEntry = new LeaderBoardEntry(i + 1, userName, score);
            leaderInfo.add(newEntry);
        }

        return Collections.unmodifiableList(leaderInfo);
    }


}
