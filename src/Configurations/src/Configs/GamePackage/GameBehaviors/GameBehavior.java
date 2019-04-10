package Configs.GamePackage.GameBehaviors;

import Configs.Behaviors.Behavior;
import Configs.EnemyPackage.EnemyBehaviors.Stealthy;
import Configs.GamePackage.Game;

import java.util.List;

public abstract class GameBehavior implements Behavior<Game> {
    Game myGame;
    public static final List<Class> IMPLEMENTING_BEHAVIORS = List.of();

    GameBehavior(Game game){
        myGame = game;
    }

    public Game getMyGame() {
        return myGame;
    }

    public void setMyGame(Game myGame) {
        this.myGame = myGame;
    }

    @Override
    public List<Class> getBehaviorOptions() {
        return IMPLEMENTING_BEHAVIORS;
    }
}
