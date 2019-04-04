package Configs.EnemyPackage;



import Configs.ProjectileConfig.ProjectileConfig;

import java.util.List;

public class Stealthy extends EnemyBehavior{
    List<ProjectileConfig> projectilesThatCanAttackMe;

    Stealthy(Enemy enemy, List<ProjectileConfig> projectilesThatCanAttackMe){
        super(enemy);
        this.projectilesThatCanAttackMe = projectilesThatCanAttackMe;
    }
}
