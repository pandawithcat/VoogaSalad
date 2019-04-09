package ActiveConfigs;

import Configs.ProjectilePackage.ProjectileConfig;
import Configs.Updatable;
import Configs.Viewable;

public class ActiveProjectile extends ProjectileConfig implements Updatable, Viewable {

    public ActiveProjectile(ProjectileConfig projectileConfig){
        super(projectileConfig);
    }

    @Override
    public void update(long ms) {

    }

}
