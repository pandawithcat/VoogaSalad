package Configs.ArsenalConfig.WeaponBehaviors;


import Configs.ShooterConfig.Shooter;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Configuration;

public class Shootable extends WeaponBehavior{

    public static final String DISPLAY_LABEL = "Shootable";
    @Configure
    private Shooter myShooter;

    private Configuration myConfiguration;
    private WeaponConfig weaponConfig;

    public Shootable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
        this.weaponConfig = weaponConfig;
    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }

    @Override
    public void update(double ms, Updatable parent) {
        myShooter.update(ms, this);
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    public WeaponConfig getWeaponConfig() {
        return weaponConfig;
    }

    public Shooter getShooter() {
        return myShooter;
    }
}
