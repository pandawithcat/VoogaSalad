package Configs.weaponsConfigPackage;

import java.util.Map;

public class WeaponConfig {


    Map<String,Behavior> myBehaviors;
    private boolean isPathPlaceable;
    private double myHealth;

    public void setIsPathPlaceable(boolean placeable) {
        isPathPlaceable = placeable;
    }

    public void setHealth(int health) {
        myHealth = health;
    }

    public void addBehavior(String behaviorName, Behavior behavior) {
        myBehaviors.put(behaviorName, behavior);
    }

}