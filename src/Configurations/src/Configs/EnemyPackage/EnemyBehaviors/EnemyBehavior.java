package Configs.EnemyPackage.EnemyBehaviors;

import Configs.Behaviors.Behavior;
import Configs.EnemyPackage.Enemy;

public abstract class EnemyBehavior implements Behavior<Enemy>{
    Enemy myEnemy;
    EnemyBehavior(Enemy enemy){
        myEnemy = enemy;
    }

    public Enemy getMyEnemy() {
        return myEnemy;
    }

    public void setMyEnemy(Enemy myEnemy) {
        this.myEnemy = myEnemy;
    }
}
