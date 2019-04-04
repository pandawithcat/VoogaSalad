package Configs.EnemyPackage;

import Configs.Behavior;
import Configs.projectileConfigPackage.ProjectileConfig;
import Configs.weaponsConfigPackage.Weapon;

import java.util.List;

public class Stealthy extends EnemyBehavior{
    List<ProjectileConfig> projectilesThatCanAttackMe;

    Stealthy(Enemy enemy, List<ProjectileConfig> projectilesThatCanAttackMe){
        super(enemy);
        this.projectilesThatCanAttackMe = projectilesThatCanAttackMe;
    }
}
