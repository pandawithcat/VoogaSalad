package Configs.WeaponsConfig.WeaponBehaviors;

import Configs.WeaponsConfig.Weapon;

public class AmmoExpirable extends WeaponBehavior {
    int numberOfEnemiesPossibleToKill;

    public AmmoExpirable(Weapon weapon, int numberOfEnemiesPossibleToKill){
        super(weapon);
        this.numberOfEnemiesPossibleToKill = numberOfEnemiesPossibleToKill;
    }
}