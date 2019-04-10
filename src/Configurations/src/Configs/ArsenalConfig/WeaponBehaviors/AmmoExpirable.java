package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.ArsenalConfig.WeaponConfig;

public class AmmoExpirable extends WeaponBehavior {
    @Configure
    protected int numberOfEnemiesPossibleToKill;

    private Configuration myConfiguration;

    public AmmoExpirable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(long ms) {


    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}