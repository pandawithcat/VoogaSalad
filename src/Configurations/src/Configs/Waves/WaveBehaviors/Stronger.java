package Configs.Waves.WaveBehaviors;

import ActiveConfigs.ActiveEnemy;
import ActiveConfigs.SpeedModifier;
import Configs.Behaviors.Behavior;
import Configs.Configuration;
import Configs.Updatable;
import Configs.Waves.Wave;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Stronger extends WaveBehavior{
    public static final String DISPLAY_LABEL = "Health Multiplier";
    @Configure
    private double healthMultiplier;


    @XStreamOmitField
    private transient Configuration myConfiguration;

    public Stronger(Wave wave) {
        super(wave);
        myConfiguration = new Configuration(this);
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }

    @Override
    public void update(double ms, Updatable parent) {

    }

    @Override
    public void apply(ActiveEnemy enemy) {
        enemy.multiplyHealth(healthMultiplier);
    }

    @Override
    public Behavior copy() {
        Stronger ret = new Stronger(getMyWave());
        ret.healthMultiplier = healthMultiplier;
        return ret;
    }
}
