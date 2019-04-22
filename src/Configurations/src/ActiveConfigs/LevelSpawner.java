package ActiveConfigs;

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


    public LevelSpawner(int levelIndex, Level[] levels, Predicate<ActiveLevel> endCondition) {
        myLevels = new ArrayList<>(Arrays.asList(levels));
        this.levelIndex = levelIndex;
        currLevel = new ActiveLevel(levels[levelIndex]);
        endLevelCondition = endCondition;
    }

    @Override
    public void update(double ms) {
        if(endLevelCondition.test(currLevel)) {
            if(levelIndex==myLevels.size()) {
                gameOver = true;
            }
        }
        else {
            currLevel.update(ms);
        }
    }

    public int startNextLevel() throws IllegalStateException{
        if(gameOver) throw new IllegalStateException();
        levelIndex++;
        currLevel = new ActiveLevel(myLevels.get(levelIndex));
        return levelIndex;
    }

    public ActiveLevel getCurrLevel() {
        return currLevel;
    }

    public boolean isGameOver() {
        return gameOver;
    }

}
