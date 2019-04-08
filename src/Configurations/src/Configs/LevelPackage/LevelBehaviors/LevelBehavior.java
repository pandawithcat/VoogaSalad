package Configs.LevelPackage.LevelBehaviors;

import Configs.Behaviors.Behavior;
import Configs.LevelPackage.Level;

public abstract class LevelBehavior implements Behavior<Level> {
    private Level myLevel;
    public LevelBehavior(Level level) {
        myLevel = level;
    }

    public Level getMyLevel() {
        return myLevel;
    }

    public void setMyLevel(Level myLevel) {
        this.myLevel = myLevel;
    }
}
