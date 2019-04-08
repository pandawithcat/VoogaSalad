package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Behaviors.Behavior;
import Configs.ArsenalConfig.Weapon;

public abstract class WeaponBehavior implements Behavior<Weapon> {
    Weapon myWeapon;
    WeaponBehavior(Weapon weapon){
        myWeapon = weapon;
    }

    public Weapon getMyWeapon() {
        return myWeapon;
    }

    public void setMyWeapon(Weapon myWeapon) {
        this.myWeapon = myWeapon;
    }
}
