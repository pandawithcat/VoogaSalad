package ActiveConfigs;

import Configs.ArsenalConfig.WeaponBehaviors.Shootable;
import Configs.Configurable;
import Configs.Configuration;
import Configs.ProjectilePackage.ProjectileConfig;
import Configs.ShooterConfig.ShooterConfig;
import Configs.Updatable;

public class Shooter extends ShooterConfig implements Updatable {
    private Shootable myShootable;

    @Configure
    private ProjectileConfig projectileConfig;

    public Shooter(ShooterConfig shooterConfig){
        super(shooterConfig);
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public void update(long ms) {
    }
}
