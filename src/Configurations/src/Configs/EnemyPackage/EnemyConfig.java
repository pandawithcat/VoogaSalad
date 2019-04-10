package Configs.EnemyPackage;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.Waves.WaveConfig;

import java.util.List;

public class EnemyConfig implements Configurable, Viewable {
    protected WaveConfig myWaveConfig;
    @Configure
    protected Behavior<EnemyConfig>[] myBehaviors;
    @Configure
    protected int unitSpeed;
    @Configure
    protected View view;
    private Configuration myConfiguration;

    public EnemyConfig(WaveConfig waveConfig) {
        myWaveConfig = waveConfig;
        myConfiguration = new Configuration(this);
    }



    public EnemyConfig(EnemyConfig enemyConfig){
        myBehaviors = enemyConfig.getMyBehaviors();
        myWaveConfig = enemyConfig.myWaveConfig;
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
        return myConfiguration;
    }

//    public WaveConfig getMyWaveConfig() {
//        return myWaveConfig;
//    }
}
