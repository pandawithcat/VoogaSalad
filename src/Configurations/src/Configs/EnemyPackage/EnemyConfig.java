package Configs.EnemyPackage;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.Waves.WaveConfig;

import java.util.List;

public class EnemyConfig implements Configurable{
    private WaveConfig myWaveConfig;
    @Configure
    Behavior<EnemyConfig>[] myBehaviors;
    @Configure
    private View view;

    public EnemyConfig(WaveConfig waveConfig) {
        myWaveConfig = waveConfig;
    }

    public EnemyConfig(EnemyConfig enemyConfig){
        myBehaviors = enemyConfig.getMyBehaviors();
        view = enemyConfig.getView();
    }

    public Behavior<EnemyConfig>[] getMyBehaviors() {
        return myBehaviors;
    }

    public View getView() {
        return view;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
