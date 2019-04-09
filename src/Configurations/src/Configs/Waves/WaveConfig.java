package Configs.Waves;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.LevelPackage.Level;

import java.util.List;

//holds the enemies
public class WaveConfig implements Updatable, Configurable {
    private Level myLevel;
    @Configure
    Behavior<WaveConfig>[] myWaveBehaviors;

    public WaveConfig(Level level) {
        myLevel = level;
    }

    @Override
    public void update(long ms) {

    }

    
    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
