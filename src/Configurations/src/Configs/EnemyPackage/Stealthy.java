package Configs.EnemyPackage;



import Configs.ProjectileConfig.ProjectileConfig;
import Configs.View;

import java.util.List;

public class Stealthy extends EnemyBehavior{
    List<ProjectileConfig> projectilesThatCanAttackMe;

    Stealthy(Enemy enemy, List<ProjectileConfig> projectilesThatCanAttackMe){
        super(enemy);
        this.projectilesThatCanAttackMe = projectilesThatCanAttackMe;
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<View> getViews() {
        return null;
    }
}
