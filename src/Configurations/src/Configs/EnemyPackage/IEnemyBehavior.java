package Configs.EnemyPackage;
import Configs.Behavior;

public interface IEnemyBehavior extends Behavior {
    void registerBehavior(Enemy enemy);
}
