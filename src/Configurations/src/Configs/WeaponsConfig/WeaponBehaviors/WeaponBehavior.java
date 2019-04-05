package Configs.WeaponsConfig.WeaponBehaviors;

import Configs.Behaviors.Behavior;
import Configs.WeaponsConfig.Weapon;

public abstract class WeaponBehavior implements Behavior<Weapon> {
    Weapon myWeapon;
    WeaponBehavior(Weapon weapon){
        myWeapon = weapon;
    }

    public Weapon getMyWeapon() {
        return myWeapon;
    }

//    public void setMyWeapon(Weapon myWeapon) {
//        this.myWeapon = myWeapon;
//    }
}
