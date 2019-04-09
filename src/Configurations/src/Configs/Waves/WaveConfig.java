package Configs.Waves;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.EnemyPackage.Enemy;
import Configs.LevelPackage.Level;

import java.util.List;

//holds the enemies
public class WaveConfig implements Updatable, Configurable {
    private Level myLevel;
    @Configure
    private long timeToRelease;
    @Configure
    private double rateOfRelease;
    @Configure
    private Enemy[] enemies;
//    @Configure
//    private Behavior<WaveConfig>[] myWaveBehaviors;

    private long relativeTime = 0;
    private int currentEnemyIndex = 0;


    public WaveConfig(Level level) {
        myLevel = level;
    }

    @Override
    public void update(long ms) {
        if(ms>=timeToRelease) {
            relativeTime = 0;
            currentEnemyIndex = 0;
            myLevel.getParent().getActiveLevel().addToEnemies(enemies[currentEnemyIndex]);
            myLevel.getParent().getActiveLevel().addToViewsToBeAdded(enemies[currentEnemyIndex]);

        }
//        ArrayAttributeManager.updateList(myWaveBehaviors, ms);

    }


    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
