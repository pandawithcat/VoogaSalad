package ActiveConfigs;

import Configs.EnemyPackage.EnemyConfig;
import Configs.Updatable;
import Configs.Viewable;

public class Enemy extends EnemyConfig implements Updatable, Viewable {

    public Enemy(EnemyConfig enemyConfig){
        super(enemyConfig);
    }

    @Override
    public void update(long ms) {

    }
}
