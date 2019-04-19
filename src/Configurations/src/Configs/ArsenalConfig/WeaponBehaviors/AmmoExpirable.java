package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configurable;
import Configs.Configuration;
import Configs.ArsenalConfig.WeaponConfig;

public class AmmoExpirable extends WeaponBehavior {
    private String myLabel;
    @Configure
    private int numberOfEnemiesPossibleToKill;

    private Configuration myConfiguration;

    public AmmoExpirable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms) {
        //TODO
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