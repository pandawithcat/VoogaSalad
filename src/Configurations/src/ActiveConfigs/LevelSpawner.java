package ActiveConfigs;

import Configs.GamePackage.Game;
import Configs.LevelPackage.Level;
import Configs.Updatable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class LevelSpawner implements Updatable {
    private List<Level> myLevels;
    private ActiveLevel currLevel;
    private int levelIndex;
    private Predicate<ActiveLevel> endLevelCondition;
    private boolean gameOver;
    private Game myGame;
    private boolean levelOver;


    public LevelSpawner(Game game, int levelIndex, Level[] levels, Predicate<ActiveLevel> endCondition) {
        this.myGame = game;
        this.levelIndex = levelIndex;
        myLevels = new ArrayList<>(Arrays.asList(levels));
        currLevel = new ActiveLevel(levels[levelIndex], game.getPaneWidth(), game.getPaneHeight());
        endLevelCondition = endCondition;
        levelOver = false;
        gameOver = false;
    }

    @Override
    public void update(double ms) {
        if(endLevelCondition.test(currLevel)) {
            levelOver = true;
            if(levelIndex==myLevels.size()) {
                gameOver = true;
            }
        }
        else {
            currLevel.update(ms);
        }
    }

    public boolean isLevelOver() {
        return levelOver;
    }

    public int startNextLevel() throws IllegalStateException{
        if(gameOver) throw new IllegalStateException();
        levelIndex++;
        currLevel = new ActiveLevel(myLevels.get(levelIndex), myGame.getPaneWidth(), myGame.getPaneHeight());
        return levelIndex;
    }

    public ActiveLevel getCurrLevel() {
        return currLevel;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getLevelIndex() {
        return levelIndex;
    }
}
