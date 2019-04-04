package Configs.EnemyPackage;

import java.util.List;

public class Enemy {
    List<IEnemyBehavior> myBehaviors;

    public void setMyBehaviors(List<IEnemyBehavior> behavior) {
        myBehaviors = behavior;
        for (IEnemyBehavior eachBehavior:myBehaviors){
            eachBehavior.registerBehavior(this);
        }
    }

    public List<IEnemyBehavior> getMyBehaviors() {
        return myBehaviors;
    }
}
