package GUI.GameAuthoringEnvironment;

import GUI.GameAuthoringEnvironment.AuthoringConfig.Game;
import GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.GameOutline;

public class AuthoringController {


    private Game myGame;
    private GameOutline myGameOutLine;


    public AuthoringController(Game game){

        myGame = game;

    }

    public int getLevel(){
        return myGame.getMyNumberOfLevels();
    }

    public void setGameOutLine(String gameName, int gameLevel){
        myGameOutLine.setContent();
    }
}
