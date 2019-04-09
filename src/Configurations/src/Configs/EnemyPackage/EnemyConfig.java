package Configs.EnemyPackage;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.Waves.WaveConfig;

import java.util.List;

public class EnemyConfig implements Configurable, Viewable {
    private WaveConfig myWaveConfig;
    @Configure
    private Behavior<EnemyConfig>[] myBehaviors;
    @Configure
    private int unitSpeed;
    @Configure
    private View view;

    public EnemyConfig(WaveConfig waveConfig) {
        myWaveConfig = waveConfig;
    }



    public EnemyConfig(EnemyConfig enemyConfig){
        myBehaviors = enemyConfig.getMyBehaviors();
        myWaveConfig = enemyConfig.getMyWaveConfig();
        unitSpeed = enemyConfig.unitSpeed;
        view = enemyConfig.getView();
    }

//    public void setMyBehaviors(List<Behavior<EnemyConfig>> behavior) {
//        myBehaviors = behavior;
//    }

    public Behavior<EnemyConfig>[] getMyBehaviors() {
        return myBehaviors;
    }

    public int getUnitSpeed() {
        return unitSpeed;
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    public WaveConfig getMyWaveConfig() {
        return myWaveConfig;
    }
}
