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
    @Slider(min = 50, max = 10000)
    @Configure
    private int health;
    @Slider(min=1,max=10)
    @Configure
    private double unitSpeedPerSecond;
    @Configure
    private View view;

    private double speedModifier;

    private transient Configuration myConfiguration;

    public EnemyConfig(Wave wave) {
        myWave = wave;
        myConfiguration = new Configuration(this);
        speedModifier = 1;
    }

    public void setSpeedModifier(double modifier) {
        speedModifier = modifier;
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

    public double getUnitSpeedPerSecond() {
        return unitSpeedPerSecond*speedModifier;
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
