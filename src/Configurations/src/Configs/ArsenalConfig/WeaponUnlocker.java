package Configs.ArsenalConfig;

import Configs.ArsenalConfig.WeaponUnlockerBehaviors.WeaponUnlockerBehavior;
import Configs.Behaviors.Behavior;
import Configs.Configurable;
import Configs.Configuration;

public class WeaponUnlocker implements Configurable {
    private WeaponConfig myWeapon;
    private Configuration myConfiguration;
    @Configure
    private Behavior weaponUnlockerBehavior;

    public WeaponUnlocker(WeaponConfig weaponConfig) {
        this.myWeapon = weaponConfig;
        this.myConfiguration = new Configuration(this);
    }

    @Configure
    private WeaponUnlockerBehavior unlockerMode;

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public String getLabel() {
        return null;
    }

}
