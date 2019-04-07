package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Behaviors.Behavior;
import Configs.ArsenalConfig.Weapon;

public abstract class WeaponBehavior implements Behavior<Weapon> {
    @Configure
    Weapon myWeapon;
    WeaponBehavior(Weapon weapon){
        myWeapon = weapon;
    }

    public Weapon getMyWeapon() {
        return myWeapon;
    }


}
