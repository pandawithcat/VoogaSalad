package ActiveConfigs;

import Configs.*;
import Configs.EnemyPackage.EnemyConfig;


public class ActiveEnemy extends EnemyConfig implements Updatable, MapFeaturable {
    private MapFeature myMapFeature;


    public ActiveEnemy(EnemyConfig enemyConfig, MapFeature mapFeature) {
        super(enemyConfig);
        myMapFeature = mapFeature;
    }


    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }


    @Override
    public void update(long ms) {
        //get x, y from myMapFeature and do logic using the map within the activeLevel
        //dont forget to update state to 1 or 2(died) in myMapFeature



    }


}
