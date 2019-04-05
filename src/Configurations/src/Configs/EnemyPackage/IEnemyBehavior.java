package Configs.EnemyPackage;
import Configs.Behaviors.Behavior;

public interface IEnemyBehavior extends Behavior {
    void registerBehavior(Enemy enemy);
}
