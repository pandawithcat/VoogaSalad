package Configs.ArsenalConfig.WeaponBehaviors;

import ActiveConfigs.Shooter;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Configurable;
import Configs.Configuration;

public class Shootable extends WeaponBehavior{

    private String myLabel;
    @Configure
    private Shooter myShooter;

    Configuration myConfiguration;
    private WeaponConfig weaponConfig;

    public Shootable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms) {
        myShooter.update(ms);
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public String getLabel() {
        return myLabel;
    }

    public WeaponConfig getWeaponConfig() {
        return weaponConfig;
    }
}
