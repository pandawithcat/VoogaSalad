package Configs.Waves;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.LevelPackage.Level;

import java.util.List;

//holds the enemies
public class Wave implements Updatable, Viewable, Configurable {
    private Level myLevel;
    @Configure
    Behavior<Wave>[] myWaveBehaviors;

    public Wave(Level level) {
        myLevel = level;
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<View> getViews() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
