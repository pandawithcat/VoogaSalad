package Configs.weaponsConfigPackage;

public class AmmoExpirable implements Behavior {
    int numberOfEnemiesPossibleToKill;

    public AmmoExpirable(int numberOfEnemiesPossibleToKill){
        this.numberOfEnemiesPossibleToKill = numberOfEnemiesPossibleToKill;
    }
}