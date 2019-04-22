package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configurable;
import Configs.Configuration;
import Configs.ArsenalConfig.WeaponConfig;

public class AmmoExpirable extends WeaponBehavior {
    public static final String myLabel = "Ammo-Expirable";
    @Configure
    private int numberOfEnemiesPossibleToKill;

    private Configuration myConfiguration;

    public AmmoExpirable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(long ms) {
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