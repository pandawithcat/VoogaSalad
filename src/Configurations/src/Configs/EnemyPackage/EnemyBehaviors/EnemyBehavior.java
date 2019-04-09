package Configs.EnemyPackage.EnemyBehaviors;

import Configs.Behaviors.Behavior;
import Configs.EnemyPackage.EnemyConfig;

public abstract class EnemyBehavior implements Behavior<EnemyConfig>{
    EnemyConfig myEnemyConfig;
    EnemyBehavior(EnemyConfig enemyConfig){
        myEnemyConfig = enemyConfig;
    }

    public EnemyConfig getMyEnemyConfig() {
        return myEnemyConfig;
    }

    public void setMyEnemyConfig(EnemyConfig myEnemyConfig) {
        this.myEnemyConfig = myEnemyConfig;
    }
}
