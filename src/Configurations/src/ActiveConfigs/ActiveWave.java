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

            while(currentEnemyIndex<getEnemies().length && startTimes[currentEnemyIndex]>=ms) {
                System.out.println(startTimes);
                System.out.println(currentEnemyIndex);
                System.out.println(ms);
//                int x = myActiveLevel.getMyMapConfig().getEnemyEnteringGridXPos();
//                int y = myActiveLevel.getMyMapConfig().getEnemyEnteringGridYPos();
//                int direction = myActiveLevel.getMyMapConfig().getEnemyEnteringDirection();
                int x = 0;
                int y = 0;
                int direction = 90;
                EnemyConfig enemyConfig = getEnemies()[currentEnemyIndex];
                //TODO: BELOW IS FOR TESTING
                MapFeature newMapFeature = new MapFeature(x, y,direction,new View("thumbnail1.gif", 10, 10),myActiveLevel.getGridHeight(),myActiveLevel.getGridWidth());

//                MapFeature newMapFeature = new MapFeature(x, y,direction,enemyConfig.getView(),myActiveLevel.getGridHeight(),myActiveLevel.getGridWidth());
                myActiveLevel.addToActiveEnemies(enemyConfig, newMapFeature);

                currentEnemyIndex++;


            }



//        ArrayAttributeManager.updateArray(myWaveBehaviors, ms);
    }

}
