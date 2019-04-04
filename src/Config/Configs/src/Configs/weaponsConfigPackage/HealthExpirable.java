package Configs.weaponsConfigPackage;

public class HealthExpirable implements IWeaponBehavior {
    int amountofHealth;
    public HealthExpirable(int amountofHealth){
        this.amountofHealth = amountofHealth;
    }
}
