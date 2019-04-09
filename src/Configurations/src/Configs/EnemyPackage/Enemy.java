package Configs.EnemyPackage;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.Waves.WaveConfig;

import java.util.List;

public class Enemy implements Configurable, Viewable, Updatable {
    private WaveConfig myWaveConfig;
    @Configure
    List<Behavior<Enemy>> myBehaviors;
    @Configure
    private View view;

    public Enemy(WaveConfig waveConfig) {
        myWaveConfig = waveConfig;
    }

    public void setMyBehaviors(List<Behavior<Enemy>> behavior) {
        myBehaviors = behavior;
    }

    public List<Behavior<Enemy>> getMyBehaviors() {
        return myBehaviors;
    }

    @Override
    public void update(long ms) {

    }

    public View getView() {
        return view;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
