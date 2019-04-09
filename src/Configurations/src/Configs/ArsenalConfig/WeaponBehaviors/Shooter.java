package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configurable;
import Configs.Configuration;

public class Shooter implements Configurable {
    @Configure
    private double rateOfFire;

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
