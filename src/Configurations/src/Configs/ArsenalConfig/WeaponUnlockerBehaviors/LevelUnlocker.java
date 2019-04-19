package Configs.ArsenalConfig.WeaponUnlockerBehaviors;

import Configs.ArsenalConfig.WeaponUnlocker;
import Configs.Configuration;
import Configs.Updatable;

public class LevelUnlocker extends WeaponUnlockerBehavior implements Updatable {
    private WeaponUnlocker myWeaponUnlocker;

    public LevelUnlocker(WeaponUnlocker weaponUnlocker) {
        this.myWeaponUnlocker = weaponUnlocker;

    }

    @Override
    public void update(double ms) {

    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
