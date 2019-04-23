package Configs.ShooterConfig;

import ActiveConfigs.ActiveLevel;
import ActiveConfigs.ActiveProjectile;
import Configs.*;
import Configs.ArsenalConfig.WeaponBehaviors.Shootable;
import Configs.ProjectilePackage.ProjectileConfig;
import Configs.ShooterConfig.ShooterBehaviors.ShooterBehavior;

public class Shooter implements Updatable , Configurable {

    private Shootable myShootable;

    public static final String myLabel="Shooter";
    @Configure
    private String myName;
    @Configure
    private double rateOfFire;
    @Configure
    private ProjectileConfig projectileConfig;
    @Configure
    private double shooterRange;
    @Configure
    private ShooterBehavior shooterBehavior;

    private Configuration myConfiguration;

    public Shooter(Shootable shootable){
        myShootable = shootable;
        myConfiguration = new Configuration(this);
    }

    public Shootable getMyShootable() {
        return myShootable;
    }

    public double getShooterRange() {
        return shooterRange;
    }

    public ProjectileConfig getProjectileConfig() {
        return projectileConfig;
    }

    public double getRateOfFire() {
        return rateOfFire;
    }


    @Override
    public String getName() {
        return myName;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public void update(double ms) {
        shooterBehavior.update(ms);

    }
}
