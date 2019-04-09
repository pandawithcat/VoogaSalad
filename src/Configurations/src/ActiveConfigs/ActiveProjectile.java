package ActiveConfigs;

import Configs.*;
import Configs.ProjectilePackage.ProjectileConfig;

public class ActiveProjectile extends ProjectileConfig implements Updatable, MapFeaturable {
    private MapFeature myMapFeature;


    public ActiveProjectile(ProjectileConfig projectileConfig, MapFeature mapFeature){

        super(projectileConfig);
        myMapFeature = mapFeature;
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }
}
