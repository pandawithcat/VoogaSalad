package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.ArsenalConfig.WeaponConfig;

import java.util.List;

public class HealthExpirable extends WeaponBehavior{
    @Configure
    private String myLabel;
    @Configure
    protected int amountOfHealth;

    Configuration myConfiguration;

    public HealthExpirable(WeaponConfig weaponConfig){
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
