package Configs.WeaponsConfig.WeaponBehaviors;

import Configs.WeaponsConfig.Weapon;

public class TimeExpirable extends WeaponBehavior{
    double timeAlive;
    public TimeExpirable(Weapon weapon, double timeAlive){
        super(weapon);
        this.timeAlive = timeAlive;
    }
}
