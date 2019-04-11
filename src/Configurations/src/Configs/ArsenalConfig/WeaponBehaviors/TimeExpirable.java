package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.ImmutableImageView;
import Configs.ArsenalConfig.WeaponConfig;

import java.util.List;

public class TimeExpirable extends WeaponBehavior{
    @Configure
    private String myLabel;
    @Configure
    private double timeAlive;

    Configuration myConfiguration;

    public TimeExpirable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public String getLabel() {
        return myLabel;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
