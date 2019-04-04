package Configs.weaponsConfigPackage;

import Configs.Shooter;

public class Shootable implements IWeaponBehavior {
    Shooter myShooter;
    Shootable(Shooter shooter){
        myShooter = shooter;
    }
}
