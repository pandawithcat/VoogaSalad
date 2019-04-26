package ExternalAPIs;

public class GameInfo {

    private String myGameID;
    private String myGameTitle;
    private int myGameThumbnailID;
    private String myGameDescription;

    public GameInfo(String title, int thumbnail, String description){
        myGameTitle = title;
        myGameThumbnailID = thumbnail;
        myGameDescription = description;
    }

    public String getGameID() {
        return myGameID;
    }

    public String getGameTitle(){
        return myGameTitle;
    }

    public int getGameThumbnail(){
        return myGameThumbnailID;
    }

    public String getGameDescription(){
        return myGameDescription;
    }
}
