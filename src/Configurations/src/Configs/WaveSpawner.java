package Configs;

import Configs.weaponsConfigPackage.IWeaponBehavior;

import java.util.List;

public class WaveSpawner {
    List<Wave> myWaves;
    List<IWaveBehavior> myWaveBehaviors;
    public void setMyBehaviors(List<IWaveBehavior> behavior) {
        myWaveBehaviors = behavior;
        for (IWaveBehavior eachBehavior:myWaveBehaviors){
            eachBehavior.registerBehavior(this);
        }
    }
}
