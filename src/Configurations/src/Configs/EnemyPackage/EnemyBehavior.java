package Configs.EnemyPackage;

import Configs.Behavior;

public class EnemyBehavior implements Behavior<Enemy>{
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
