package Configs.WeaponsConfig.WeaponBehaviors;

import Configs.WeaponsConfig.Weapon;

public class HealthExpirable extends WeaponBehavior{
    int amountOfHealth;
    public HealthExpirable(Weapon weapon, int amountOfHealth){
        super(weapon);
        this.amountOfHealth = amountOfHealth;
    }
}
