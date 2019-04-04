package Configs.weaponsConfigPackage;

import java.util.List;


public class WeaponConfig {
    List<Behavior> myBehaviors;

    public void addBehavior(String behaviorName, Behavior behavior) {
        myBehaviors.add(behavior);
    }
}