package ExternalAPIs;

public class GameInfo {

    private String myGameTitle;
    private String myGameThumbnail;
    private String myGameDescription;

    public GameInfo(String title, String thumbnail, String description){
        myGameTitle = title;
        myGameThumbnail = thumbnail;
        myGameDescription = description;
    }

    public String getGameTitle(){
        return myGameTitle;
    }

    public String getGameThumbnail(){
        return myGameThumbnail;
    }

    public String getGameDescription(){
        return myGameDescription;
    }
}
