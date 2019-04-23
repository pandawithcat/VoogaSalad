package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.ArsenalConfig.WeaponConfig;

import java.util.List;

public class HealthExpirable extends WeaponBehavior{
    public static final String myLabel = "Health-Expirable";
    @Configure
    protected int amountOfHealth;

    Configuration myConfiguration;

    public HealthExpirable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms) {
        //TODO
    }
    @Override
    public String getName() {
        return myLabel;
    }
    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
