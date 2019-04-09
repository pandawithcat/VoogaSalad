package ActiveConfigs;

import Configs.EnemyPackage.EnemyConfig;
import Configs.MapFeaturable;
import Configs.MapFeature;
import Configs.Updatable;
import Configs.Waves.WaveConfig;

public class ActiveWave extends WaveConfig implements Updatable{//, MapFeaturable}  {
    private long[] startTimes;
    private int currentEnemyIndex = 0;
    private boolean isFinished = false;
    //private MapFeature myMapFeature;

    public ActiveWave(WaveConfig waveConfig){//}, MapFeature mapFeature) {
        super(waveConfig);
        //myMapFeature = mapFeature;
    }


    public boolean isFinished() {
        return isFinished;
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
                int x = getMyLevel().getMyMap().getEnemyEnteringGridXPos();
                int y = getMyLevel().getMyMap().getEnemyEnteringGridYPos();
                int direction = getMyLevel().getMyMap().getEnemyEnteringDirection();
                EnemyConfig enemyConfig = getEnemies()[currentEnemyIndex];
                MapFeature newMapFeature = new MapFeature(x, y,direction,enemyConfig.getView());
                getMyLevel().getGame().getActiveLevel().addToActiveEnemies(enemyConfig, newMapFeature);

                currentEnemyIndex++;
            }
        }
        else {
            isFinished = true;
        }
//        ArrayAttributeManager.updateList(myWaveBehaviors, ms);

    }

}
