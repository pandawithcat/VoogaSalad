package Configs.weaponsConfigPackage;

import java.util.List;


public class WeaponConfig {
    List<IWeaponBehavior> myBehaviors;

    public void setMyBehaviors(List<IWeaponBehavior> behavior) {
        myBehaviors = behavior;
        for (IWeaponBehavior eachBehavior:myBehaviors){
            eachBehavior.registerBehavior(this);
        }
    }

    public List<IWeaponBehavior> getMyBehaviors() {
        return myBehaviors;
    }
}