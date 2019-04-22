package Configs.ArsenalConfig.WeaponBehaviors;


import Configs.ShooterConfig.Shooter;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Configuration;

public class Shootable extends WeaponBehavior{

    public static final String myLabel = "Shootable";
    @Configure
    private Shooter myShooter;

    Configuration myConfiguration;
    private WeaponConfig weaponConfig;

    public Shootable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public String getName() {
        return myLabel;
    }

    @Override
    public void update(long ms) {
        myShooter.update(ms);
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    public WeaponConfig getWeaponConfig() {
        return weaponConfig;
    }
}
