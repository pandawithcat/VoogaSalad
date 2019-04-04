package Configs.EnemyPackage;

import Configs.Behavior;
import Configs.weaponsConfigPackage.IWeaponBehavior;

import java.util.List;

public class Enemy {
    List<Behavior<Enemy>> myBehaviors;

    public void setMyBehaviors(List<Behavior<Enemy>> behavior) {
        myBehaviors = behavior;
    }

    public List<Behavior<Enemy>> getMyBehaviors() {
        return myBehaviors;
    }
}
