package ActiveConfigs;

import Configs.ArsenalConfig.WeaponBehaviors.Shootable;
import Configs.Configurable;
import Configs.Configuration;
import Configs.ProjectilePackage.ProjectileConfig;
import Configs.Updatable;

public class Shooter implements Configurable, Updatable {
    private Shootable myShootable;

    @Configure
    private ProjectileConfig projectileConfig;

    public Shooter(Shootable shootable){
        super();
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public void update(long ms) {

    }
}
