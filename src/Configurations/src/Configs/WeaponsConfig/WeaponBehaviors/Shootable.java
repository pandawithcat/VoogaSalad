package Configs.WeaponsConfig.WeaponBehaviors;

import Configs.Shooter;
import Configs.WeaponsConfig.Weapon;

public class Shootable extends WeaponBehavior{
    Shooter myShooter;
    Shootable(Weapon weapon, Shooter shooter){
        super(weapon);
        myShooter = shooter;
    }
}
