package Configs.weaponsConfigPackage;

import Configs.Behavior;

import java.util.List;


public class Weapon {
    List<Behavior<Weapon>> myBehaviors;

    public void setMyBehaviors(List<Behavior<Weapon>> behavior) {
        myBehaviors = behavior;
    }

    public List<Behavior<Weapon>> getMyBehaviors() {
        return myBehaviors;
    }
}