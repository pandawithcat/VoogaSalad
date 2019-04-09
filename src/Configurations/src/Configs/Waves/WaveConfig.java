package Configs.Waves;

import Configs.*;
import Configs.EnemyPackage.EnemyConfig;
import Configs.LevelPackage.Level;

//holds the enemies
public class WaveConfig implements Updatable, Configurable {
    private Level myLevel;
    @Configure
    private long timeToRelease;
    @Configure
    private long rateOfRelease;
    @Configure
    private EnemyConfig[] enemies;
//    @Configure
//    private Behavior<WaveConfig>[] myWaveBehaviors;

    private long[] startTimes;
    private int currentEnemyIndex = 0;
    private boolean isFinished = false;


    public WaveConfig(Level level) {
        myLevel = level;
    }

    @Override
    public void update(long ms) {
        if(ms>=timeToRelease && currentEnemyIndex==0) {
            startTimes = new long[enemies.length];
            long relativeTime = 0;
            for(int i = 0;i<startTimes.length;i++) {
                startTimes[i] = ms+relativeTime;
                relativeTime+=rateOfRelease;
            }
        }
        if(currentEnemyIndex<enemies.length) {
            while(startTimes[currentEnemyIndex]>=ms) {
                myLevel.getParent().getActiveLevel().addToActiveEnemies(enemies[currentEnemyIndex]);
                myLevel.getParent().getActiveLevel().addViewToBeAdded(enemies[currentEnemyIndex].getView().getImageView());
                currentEnemyIndex++;
            }
        }
        else {
            isFinished = true;
        }
//        ArrayAttributeManager.updateList(myWaveBehaviors, ms);

    }

    public boolean getIsFinished() {
        return isFinished;
    }


    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
