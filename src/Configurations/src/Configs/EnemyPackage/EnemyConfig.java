package Configs.EnemyPackage;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.Waves.WaveConfig;

public class EnemyConfig implements Configurable, Viewable {
    private WaveConfig myWaveConfig;
    @Configure
    private Behavior<EnemyConfig>[] myBehaviors;
    @Configure
    private int unitSpeedPerSecond;
    @Configure
    private View view;
    private Configuration myConfiguration;

    public EnemyConfig(WaveConfig waveConfig) {
        myWaveConfig = waveConfig;
        myConfiguration = new Configuration(this);
    }

    public EnemyConfig(EnemyConfig enemyConfig){
        myBehaviors = enemyConfig.getMyBehaviors();
        myWaveConfig = enemyConfig.getMyWaveConfig();
        unitSpeedPerSecond = enemyConfig.getUnitSpeedPerSecond();
        view = enemyConfig.getView();
    }

    public WaveConfig getMyWaveConfig() {
        return myWaveConfig;
    }

    //    public void setMyBehaviors(List<Behavior<EnemyConfig>> behavior) {
//        myBehaviors = behavior;
//    }

    public Behavior<EnemyConfig>[] getMyBehaviors() {
        return myBehaviors;
    }

    public int getUnitSpeedPerSecond() {
        return unitSpeedPerSecond;
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

//    public WaveConfig getMyWaveConfig() {
//        return myWaveConfig;
//    }
}
