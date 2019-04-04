package Configs.weaponsConfigPackage;

import Configs.Shooter;

public class Shootable extends WeaponBehavior{
    Shooter myShooter;
    Shootable(Weapon weapon, Shooter shooter){
        super(weapon);
        myShooter = shooter;
    }
}
