package Configs.EnemyPackage;

import Configs.Behavior;
import Configs.weaponsConfigPackage.Weapon;

public interface IEnemyBehavior extends Behavior {
    void registerBehavior(Enemy enemy);
}
