package ExternalAPIs;

public class GameInfo {

    private String myGameTitle;
    private String myGameThumbnailID;
    private String myGameDescription;

    public GameInfo(String title, String thumbnail, String description){
        myGameTitle = title;
        myGameThumbnailID = thumbnail;
        myGameDescription = description;
    }

    public String getGameTitle(){
        return myGameTitle;
    }

    public String getGameThumbnail(){
        return myGameThumbnailID;
    }

    public String getGameDescription(){
        return myGameDescription;
    }
}
