package Configs.EnemyPackage;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.Waves.WaveConfig;

import java.util.List;

public class EnemyConfig implements Configurable, Viewable {
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
        myWaveConfig = enemyConfig.myWaveConfig;
        view = enemyConfig.getView();
    }

//    public void setMyBehaviors(List<Behavior<EnemyConfig>> behavior) {
//        myBehaviors = behavior;
//    }

    public Behavior<EnemyConfig>[] getMyBehaviors() {
        return myBehaviors;
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
