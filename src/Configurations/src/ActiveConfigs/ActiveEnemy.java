package ActiveConfigs;

import Configs.ArsenalConfig.WeaponConfig;
import Configs.EnemyPackage.EnemyConfig;
import Configs.Updatable;
import Configs.Viewable;

public class ActiveEnemy extends EnemyConfig implements Updatable, Viewable {
    public ActiveEnemy(EnemyConfig enemyConfig) {
        super(enemyConfig);
    }

    @Override
    public void update(long ms) {
        super.update(ms);
    }


}
