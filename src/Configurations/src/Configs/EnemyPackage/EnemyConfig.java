package Configs.EnemyPackage;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.Waves.WaveConfig;

import java.util.List;

public class EnemyConfig implements Configurable, Viewable, Updatable {
    private WaveConfig myWaveConfig;
    @Configure
    List<Behavior<EnemyConfig>> myBehaviors;
    @Configure
    private View view;

    public EnemyConfig(WaveConfig waveConfig) {
        myWaveConfig = waveConfig;
    }

    public EnemyConfig(EnemyConfig enemyConfig) {
        myBehaviors = enemyConfig.getMyBehaviors();
        view = enemyConfig.getView();
    }

    public void setMyBehaviors(List<Behavior<EnemyConfig>> behavior) {
        myBehaviors = behavior;
    }

    public List<Behavior<EnemyConfig>> getMyBehaviors() {
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
