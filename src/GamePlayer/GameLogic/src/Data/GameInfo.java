package Data;

public class GameInfo {

        private String myGameTitle;
        private String myGameThumbnail;

        public GameInfo(String title, String thumbnail){
            myGameTitle = title;
            myGameThumbnail = thumbnail;
        }

        public String getGameTitle(){
            return myGameTitle;
        }

        public String getMyGameThumbnail(){
            return myGameThumbnail;
        }
}
