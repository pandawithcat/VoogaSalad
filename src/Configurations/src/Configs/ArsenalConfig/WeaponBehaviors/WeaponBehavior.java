package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.ArsenalConfig.WeaponConfig;
import Configs.Behaviors.Behavior;

public abstract class WeaponBehavior implements Behavior<WeaponConfig> {
    WeaponConfig myWeaponConfig;
    WeaponBehavior(WeaponConfig weaponConfig){
        myWeaponConfig = weaponConfig;
    }

    public WeaponConfig getMyWeaponConfig() {
        return myWeaponConfig;
    }

    public void setMyWeaponConfig(WeaponConfig myWeaponConfig) {
        this.myWeaponConfig = myWeaponConfig;
    }
}
