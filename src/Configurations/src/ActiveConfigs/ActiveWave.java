package ActiveConfigs;

import Configs.EnemyPackage.EnemyConfig;
import Configs.GamePackage.Game;
import Configs.MapFeaturable;
import Configs.MapFeature;
import Configs.Updatable;
import Configs.Waves.WaveConfig;


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
        if(ms>=timeToRelease && currentEnemyIndex==0) {
            startTimes = new long[getEnemies().length];
            long relativeTime = 0;
            for(int i = 0;i<startTimes.length;i++) {
                startTimes[i] = ms+relativeTime;
                relativeTime+=rateOfRelease;
            }
        }
        if(currentEnemyIndex<getEnemies().length) {
            while(startTimes[currentEnemyIndex]>=ms) {
                int x = myActiveLevel.getMyMap().getEnemyEnteringGridXPos();
                int y = myActiveLevel.getMyMap().getEnemyEnteringGridYPos();
                int direction = myActiveLevel.getMyMap().getEnemyEnteringDirection();
                EnemyConfig enemyConfig = enemies[currentEnemyIndex];
                MapFeature newMapFeature = new MapFeature(x, y,direction,enemyConfig.getView());
                myLevel.getGame().getActiveLevel().addToActiveEnemies(enemyConfig, newMapFeature);

                currentEnemyIndex++;
            }
        }
        else {
            isFinished = true;
        }

//        ArrayAttributeManager.updateArray(myWaveBehaviors, ms);
    }

}
