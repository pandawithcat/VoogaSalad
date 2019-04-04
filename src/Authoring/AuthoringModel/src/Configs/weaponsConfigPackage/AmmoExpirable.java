package Configs.weaponsConfigPackage;

public class AmmoExpirable implements IWeaponBehavior {
    int numberOfEnemiesPossibleToKill;

    public AmmoExpirable(int numberOfEnemiesPossibleToKill){
        this.numberOfEnemiesPossibleToKill = numberOfEnemiesPossibleToKill;
    }
}