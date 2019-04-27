package Configs.EnemyPackage;

import Configs.*;
import Configs.EnemyPackage.EnemyBehaviors.EnemyBehavior;
import Configs.Waves.Wave;

public class EnemyConfig implements Configurable, Viewable {
    private Wave myWave;
    public static final String DISPLAY_LABEL = "Enemy";
    @Configure
    private String myName;
    @Configure
    private EnemyBehavior[] myBehaviors;
    @Slider(min=1,max=10)
    @Configure
    private int unitSpeedPerSecond;
    @Configure
    private View view;

    private transient Configuration myConfiguration;

    public EnemyConfig(Wave wave) {
        myWave = wave;
        myConfiguration = new Configuration(this);
    }

    public EnemyConfig(EnemyConfig enemyConfig){
        myBehaviors = enemyConfig.getMyBehaviors();
        myWave = enemyConfig.getMyWave();
        unitSpeedPerSecond = enemyConfig.getUnitSpeedPerSecond();
        view = enemyConfig.getView();
    }

    public Wave getMyWave() {
        return myWave;
    }

    //    public void setMyBehaviors(List<Behavior<EnemyConfig>> behavior) {
//        myBehaviors = behavior;
//    }

    public EnemyBehavior[] getMyBehaviors() {
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
    public String getName() {
        return myName;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

//    public Wave getMyWave() {
//        return myWave;
//    }
}
