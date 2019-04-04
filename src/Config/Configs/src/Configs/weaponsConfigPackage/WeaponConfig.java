package Configs.weaponsConfigPackage;

import java.util.List;


public class WeaponConfig {
    List<IWeaponBehavior> myBehaviors;

    public void setMyBehaviors(List<IWeaponBehavior> behavior) {
        myBehaviors = behavior;
    }

    public List<IWeaponBehavior> getMyBehaviors() {
        return myBehaviors;
    }
}