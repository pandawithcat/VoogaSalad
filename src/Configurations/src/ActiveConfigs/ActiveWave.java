package ActiveConfigs;

import Configs.EnemyPackage.EnemyConfig;
import Configs.MapFeature;
import Configs.Updatable;
import Configs.Waves.WaveConfig;

import java.util.Arrays;


public class ActiveWave extends WaveConfig implements Updatable {
    private long[] startTimes;
    private int currentEnemyIndex = 0;
    private boolean isFinished = false;
    private ActiveLevel myActiveLevel;

    public ActiveWave(WaveConfig waveConfig, ActiveLevel activeLevel) {
        super(waveConfig);
        myActiveLevel = activeLevel;

    }


    public boolean isFinished() {
        return isFinished;
    }


    public ActiveLevel getMyActiveLevel() {
        return myActiveLevel;
    }

    @Override
    public void update(long ms) {
        if(ms>=getTimeToRelease() && currentEnemyIndex==0) {
            startTimes = new long[getEnemies().length];
            long relativeTime = 0;
            for(int i = 0;i<startTimes.length;i++) {
                startTimes[i] = ms+relativeTime;
                relativeTime+=getRateOfRelease();
            }
        }
        if(currentEnemyIndex<getEnemies().length) {
            while(startTimes[currentEnemyIndex]>=ms) {
                //TODO: UNCOMMENT WAS FOR TESTING
//                int x = myActiveLevel.getMyMapConfig().getEnemyEnteringGridXPos();
//                int y = myActiveLevel.getMyMapConfig().getEnemyEnteringGridYPos();
//                int direction = myActiveLevel.getMyMapConfig().getEnemyEnteringDirection();
                int x = 0;
                int y = 0;
                int direction = 90;
                EnemyConfig enemyConfig = getEnemies()[currentEnemyIndex];
                MapFeature newMapFeature = new MapFeature(x, y,direction,enemyConfig.getView(),myActiveLevel.getGridHeight(),myActiveLevel.getGridWidth());
                myActiveLevel.addToActiveEnemies(enemyConfig, newMapFeature);
            }
            currentEnemyIndex++;

        }
        else {
            isFinished = true;
        }

//        ArrayAttributeManager.updateArray(myWaveBehaviors, ms);
    }

}
