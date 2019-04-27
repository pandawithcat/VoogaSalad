package ExternalAPIs;

public class GameInfo {

    private String myGameID;
    private String myGameTitle;
    private String myGameThumbnailID;
    private int myGameThumbnailIDNew;
    private String myGameDescription;

    public GameInfo(String title, String thumbnail, String description){
        myGameTitle = title;
        myGameThumbnailID = thumbnail;
        myGameDescription = description;
    }

    public GameInfo(String title, int thumbnail, String description){
        myGameTitle = title;
        myGameThumbnailIDNew = thumbnail;
        myGameDescription = description;
    }

    public String getGameID() {
        return myGameID;
    }

    public String getGameTitle(){
        return myGameTitle;
    }

    public String getGameThumbnail(){
        return myGameThumbnailID;
    }

    public int getGameThumbnailNew(){ return myGameThumbnailIDNew;}

    public String getGameDescription(){
        return myGameDescription;
    }
}
