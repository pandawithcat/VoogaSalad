package ActiveConfigs;

import Configs.*;
import Configs.EnemyPackage.EnemyConfig;


public class ActiveEnemy extends EnemyConfig implements Updatable, MapFeaturable {
    private MapFeature myMapFeature;
    private Cell[][] activeMapGrid;
    ActiveLevel myLevel;


    public ActiveEnemy(EnemyConfig enemyConfig, MapFeature mapFeature) {
        super(enemyConfig);
        myMapFeature = mapFeature;
        myLevel = getMyWaveConfig().getMyLevel().getGame().getActiveLevel();
    }


    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }


    @Override
    public void update(long ms) {
        //get x, y from myMapFeature and do logic using the map within the activeLevel
//        if
        //dont forget to update state to PRESENT or DIED in myMapFeature
        myMapFeature.setDisplayState();
        getUnitSpeed();



    }

    public void killMe(){
        myMapFeature.setDisplayState(DisplayState.DIED);
    }

}
