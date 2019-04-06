package Configs.Waves;

import Configs.Behaviors.Behavior;
import Configs.Configurable;
import Configs.Updatable;
import Configs.View;
import Configs.Viewable;

import java.util.List;

public class WaveSpawner implements Viewable, Configurable, Updatable {
    @Configure
    List<Wave> myWaves;
    @Configure
    List<Behavior<Wave>> myWaveBehaviors;

    public void setMyBehaviors(List<Behavior<Wave>> behavior) {
        myWaveBehaviors = behavior;
    }

    @Override
    public List<View> getViews() {
        return null;
    }

    @Override
    public void update(long ms) {

    }
}
