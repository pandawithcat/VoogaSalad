package Queries.Results;

import java.io.InputStream;

public class GameInfo {

    private int gameID;
    private int authorID;
    private int thumbnailID;
    private String title;
    private String description;
    private InputStream binary;

    public GameInfo(int gameId, int thumbnailID, int authorID, String title, String description, InputStream binary) {
        this.title = title;
        this.description = description;
        this.thumbnailID = thumbnailID;
        this.gameID = gameId;
        this.authorID = authorID;
        this.binary = binary;
    }

    public int getGameID() {
        return gameID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public int getThumbnailID() { return thumbnailID; }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getBinary(){
        try {
            return binary.readAllBytes();
        }
        catch (Exception e){
            return null;
        }
    }
}



