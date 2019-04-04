package BackendExternal;

public class GameInfo {

    String getGameTitle();

    String getGameThumbnail();

    String getGameDescription();

    private String myGameTitle;
    private String myGameThumbnail;

    public GameInfo(String title, String thumbnail){
        myGameTitle = title;
        myGameThumbnail = thumbnail;
    }




}
