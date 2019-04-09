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

    ShooterConfig(ShooterConfig shooterConfig){
//        projectileConfig = new ProjectileConfig()
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
