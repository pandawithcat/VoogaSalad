package frontendExample.GameAuthoringExample;

public class Grid {

    Game myGame;
    public Grid(Game game){
        myGame = game;
    }

    public void setGameConfig(){
        myGame.setLevel(level);
        myGame.setTowerLists(level, towers);
        myGame.setEnemies(level, enemy);
        myGame.setPath(level, path);
    }

    public void addTower(){

    }

    public void addPath();

    public void addTile():

}
