package Configs.weaponsConfigPackage;

import Configs.Behavior;

public interface IWeaponBehavior extends Behavior {
    void registerBehavior(WeaponConfig weaponConfig);
}
