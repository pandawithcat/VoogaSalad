package Configs.ShooterConfig;

import Configs.ArsenalConfig.WeaponBehaviors.Shootable;
import Configs.Configurable;
import Configs.Configuration;
import Configs.ProjectilePackage.ProjectileConfig;

public class ShooterConfig implements Configurable{
    private Shootable myShootable;

    @Configure
    private double rateOfFire;
    @Configure
    private ProjectileConfig projectileConfig;
    private Configuration myConfiguration;


    public ShooterConfig(Shootable shootable){
        myShootable = shootable;
        myConfiguration = new Configuration(this);
    }

    public ShooterConfig(ShooterConfig shooterConfig){
        rateOfFire = shooterConfig.getRateOfFire();
        projectileConfig = shooterConfig.getProjectileConfig();
    }

    public Shootable getMyShootable() {
        return myShootable;
    }

    public ProjectileConfig getProjectileConfig() {
        return projectileConfig;
    }

    public double getRateOfFire() {
        return rateOfFire;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
