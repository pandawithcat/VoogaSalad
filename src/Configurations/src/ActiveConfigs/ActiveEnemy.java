package ActiveConfigs;

import Configs.*;
import Configs.EnemyPackage.EnemyConfig;


public class ActiveEnemy extends EnemyConfig implements Updatable, MapFeaturable {
    private MapFeature myMapFeature;
    private Cell[][] activeMapGrid;
    private ActiveLevel myActiveLevel;


    public ActiveEnemy(EnemyConfig enemyConfig, MapFeature mapFeature, ActiveLevel activeLevel) {
        super(enemyConfig);
        myMapFeature = mapFeature;
        myActiveLevel = activeLevel;
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

        double distance = ms* getUnitSpeedPerSecond();
        myMapFeature.setDisplayState(DisplayState.PRESENT);



    }

    public void killMe(){
        myMapFeature.setDisplayState(DisplayState.DIED);
    }
}
