package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.ImmutableImageView;
import Configs.ArsenalConfig.WeaponConfig;

import java.util.List;

public class TimeExpirable extends WeaponBehavior{
    public static final String DISPLAY_LABEL= "Time-Expirable";
    @Configure
    private double timeAlive;

    Configuration myConfiguration;

    public TimeExpirable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms, Updatable parent) {
        //TODO
    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }



    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
