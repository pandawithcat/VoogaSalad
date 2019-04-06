package Configs.WeaponsConfig;

import Configs.Behaviors.Behaves;
import Configs.Behaviors.Behavior;

import java.util.ArrayList;
import java.util.List;


public class Weapon implements Behaves<Behavior<Weapon>> {
    List<Behavior<Weapon>> myBehaviors = new ArrayList<>();

//    public void setMyBehaviors(List<Behavior<Weapon>> behavior) {
//        myBehaviors = behavior;
//    }
//


    @Override
    public void addBehavior(Behavior<Weapon> behavior) {
        myBehaviors.add(behavior);
    }

    @Override
    public List<Behavior<Weapon>> getMyBehaviors() {
        return myBehaviors;
    }
}