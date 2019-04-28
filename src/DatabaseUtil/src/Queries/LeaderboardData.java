package Queries;

public class LeaderboardData extends DBUtil{


    //    public GameInfo fetchGame(int gameID){
//        String selectionQuery =
//                "select * " +
//                        "from games where gameID = (?)";
//        try {
//            PreparedStatement statement = getConnection().prepareStatement(selectionQuery);
//            statement.setInt(1, gameID);
//            ResultSet results = statement.executeQuery();
//            GameInfo game = null;
//            if(results.next()){
//                InputStream binary = results.getBinaryStream("gameFile");
//                int authorID = results.getInt("authorID");
//                int imageID = results.getInt("thumbnailID");
//                String description = results.getString("description");
//                String title = results.getString("title");
//                game = new GameInfo(gameID, imageID, authorID, title, description, binary);
//            }
//            results.close();
//            statement.close();
//            return game;
//        }
//        catch (SQLException e){
//            closeConnection();
//            throw new ConnectionException(e.toString());
//        }
//    }
}
