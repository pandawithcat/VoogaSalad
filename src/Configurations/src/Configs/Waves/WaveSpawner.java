package Configs.Waves;

import Configs.Behaviors.Behavior;

import java.util.List;

public class WaveSpawner {
    List<Wave> myWaves;
    List<Behavior<Wave>> myWaveBehaviors;
    public void setMyBehaviors(List<Behavior<Wave>> behavior) {
        myWaveBehaviors = behavior;
    }
}
