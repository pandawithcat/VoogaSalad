package ActiveConfigs;

import Configs.EnemyPackage.EnemyConfig;
import Configs.MapFeature;
import Configs.Updatable;
import Configs.View;
import Configs.Waves.WaveConfig;


public class ActiveWave extends WaveConfig implements Updatable {
    private double[] startTimes;
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
    public void update(double ms) {
        if(ms>=getTimeToRelease() && currentEnemyIndex==0) {
            startTimes = new double[getEnemies().length];
            for(int i = 0;i<startTimes.length;i++) {
                startTimes[i] = ms+(i*getRateOfRelease());
            }
        }
        if(currentEnemyIndex>=getEnemies().length) isFinished= true;

        while(ms>=getTimeToRelease() && currentEnemyIndex<getEnemies().length && startTimes[currentEnemyIndex]>=ms) {
            int x = myActiveLevel.getMyMapConfig().getEnemyEnteringGridXPos();
            int y = myActiveLevel.getMyMapConfig().getEnemyEnteringGridYPos();
            int direction = myActiveLevel.getMyMapConfig().getEnemyEnteringDirection();
            EnemyConfig enemyConfig = getEnemies()[currentEnemyIndex];
            MapFeature newMapFeature = new MapFeature(x, y,direction,enemyConfig.getView());
            myActiveLevel.addToActiveEnemies(enemyConfig, newMapFeature);
            currentEnemyIndex++;
        }



//        ArrayAttributeManager.updateArray(myWaveBehaviors, ms);
    }

}
