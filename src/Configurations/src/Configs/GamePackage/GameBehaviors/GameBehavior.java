package Configs.GamePackage.GameBehaviors;

import Configs.Behaviors.Behavior;
import Configs.GamePackage.Game;

public abstract class GameBehavior implements Behavior<Game> {
    Game myGame;

    GameBehavior(Game game){
        myGame = game;
    }

    public Game getMyGame() {
        return myGame;
    }

    public void setMyGame(Game myGame) {
        this.myGame = myGame;
    }
}
