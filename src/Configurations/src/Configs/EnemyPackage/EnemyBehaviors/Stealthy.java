package Configs.EnemyPackage.EnemyBehaviors;



import Configs.Configuration;
import Configs.EnemyPackage.Enemy;
import Configs.ImmutableImageView;
import Configs.ProjectilePackage.ProjectileConfig;

import java.util.List;

public class Stealthy extends EnemyBehavior{
    @Configure
    ProjectileConfig[] projectilesThatCanAttackMe;

    Stealthy(Enemy enemy){
        super(enemy);
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
