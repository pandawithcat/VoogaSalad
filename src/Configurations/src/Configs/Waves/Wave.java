package Configs.Waves;

import ActiveConfigs.ActiveLevel;
import Configs.*;
import Configs.EnemyPackage.EnemyConfig;
import Configs.LevelPackage.Level;
import Configs.Waves.WaveBehaviors.WaveBehavior;


public class Wave implements Updatable, Configurable {
    private Level myLevel;
    private String myLabel = "Wave";
    @Configure
    private String name;
    @Configure
    private double timeToReleaseInMs;
    @Configure
    private double rateOfReleaseEnemiesPerMs;
    @Configure
    private EnemyConfig[] enemies;
    @Configure
    private WaveBehavior[] myWaveBehaviors;

    private Configuration myConfiguration;



    private double[] startTimes;
    private int currentEnemyIndex = 0;
    private boolean isFinished = false;

    public Wave(Level level) {
        myLevel = level;
        myConfiguration = new Configuration(this);
    }


    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public String getLabel() {
        return myLabel;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public void update(double ms) {
        if(startTimes == null) {
            startTimes = new double[enemies.length];
            for(int i = 0;i<startTimes.length;i++) {
                startTimes[i] = ms+(i*rateOfReleaseEnemiesPerMs);
            }
        }
        if(currentEnemyIndex>=enemies.length) {
            isFinished= true;
        }

        while(ms>=timeToReleaseInMs && currentEnemyIndex<enemies.length && startTimes[currentEnemyIndex]<=ms) {
            ActiveLevel activeLevel = myLevel.getGame().getActiveLevel();
            int x = activeLevel.getMyMapConfig().getEnemyEnteringGridXPos();
            int y = activeLevel.getMyMapConfig().getEnemyEnteringGridYPos();
            int direction = activeLevel.getMyMapConfig().getEnemyEnteringDirection();
            EnemyConfig enemyConfig = enemies[currentEnemyIndex];
            MapFeature newMapFeature = new MapFeature(x, y,direction,enemyConfig.getView(), activeLevel.getPaneWidth(), activeLevel.getPaneHeight(), activeLevel.getGridWidth(), activeLevel.getGridWidth());
            activeLevel.addToActiveEnemies(enemyConfig, newMapFeature);
            currentEnemyIndex++;
        }



//        ArrayAttributeManager.updateArray(myWaveBehaviors, ms);
    }

}
