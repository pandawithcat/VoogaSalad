import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SessionData extends DBUtil{

    /**
     * @param level the level achieved
     * @param score the
     * @param userID The desired username
     * @return The userID of the new user if successful, -1 if not
     */
    public int addSession(int level, int score, int gameID, int userID){
        String insertionQuery =
                " insert into gameSessions (level, score, gameID, userID)"
                        + " values (?, ?, ?, ?);";
        try {
            PreparedStatement statement = getConnection().prepareStatement(insertionQuery, RETURN_GENERATED_KEYS);
            statement.setInt(1, level);
            statement.setInt(2, score);
            statement.setInt(3, gameID);
            statement.setInt(4, userID);
            statement.executeUpdate();
            int ID = getGeneratedAutoIndex(statement);
            statement.close();
            return ID;
        }
        catch (SQLException e){
            closeConnection();
            throw new ConnectionException(e.toString());
        }
    }

    /**
     * @param userID The userID of the requested user
     * @return The GameSessions corresponding to the given user
     */
    public ArrayList<GameSession> getSavedSessions(int userID){
        String selectionQuery = "select * from gameSessions where playerID = (?)";
        try {
            PreparedStatement statement = getConnection().prepareStatement(selectionQuery);
            statement.setInt(1, userID);
            ResultSet results = statement.executeQuery();
            ArrayList<GameSession> sessionsList = new ArrayList<>();
            while(results.next()){
                int sessionID = results.getInt("sessionID");
                int gameID = results.getInt("gameID");
                int level = results.getInt("level");
                int score = results.getInt("score");
                GameSession session = new GameSession(level, score, gameID, sessionID);
                sessionsList.add(session);
            }
            results.close();
            statement.close();
            return sessionsList;
        }
        catch (SQLException e){
            closeConnection();
            throw new ConnectionException(e.toString());
        }
    }
}
