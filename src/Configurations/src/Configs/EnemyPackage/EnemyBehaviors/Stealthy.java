package Configs.EnemyPackage.EnemyBehaviors;



import Configs.Configuration;
import Configs.EnemyPackage.EnemyConfig;
import Configs.ProjectilePackage.ProjectileConfig;

public class Stealthy extends EnemyBehavior{
    @Configure
    ProjectileConfig[] projectilesThatCanAttackMe;

    Stealthy(EnemyConfig enemyConfig){
        super(enemyConfig);
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
