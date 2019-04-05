package Configs.WeaponsConfig;

public class TimeExpirable extends WeaponBehavior{
    double timeAlive;
    public TimeExpirable(Weapon weapon, double timeAlive){
        super(weapon);
        this.timeAlive = timeAlive;
    }
}
