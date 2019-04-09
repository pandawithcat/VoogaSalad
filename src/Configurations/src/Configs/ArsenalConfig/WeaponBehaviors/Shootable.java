package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.ArsenalConfig.WeaponConfig;
import Configs.Configuration;

public class Shootable extends WeaponBehavior{
    @Configure
    Shooter myShooter;

    Configuration myConfiguration;

    Shootable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(long ms) {
        myShooter.update(ms);
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
