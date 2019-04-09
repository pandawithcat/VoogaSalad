package ActiveConfigs;

import Configs.ArsenalConfig.WeaponConfig;
import Configs.MapFeaturable;
import Configs.MapFeature;
import Configs.Updatable;
import Configs.Viewable;

import java.util.Map;

public class ActiveWeapon extends WeaponConfig implements Updatable, MapFeaturable {
    private MapFeature myMapFeature;

    public ActiveWeapon(WeaponConfig weaponConfig, MapFeature mapFeature) {

        super(weaponConfig);
        myMapFeature = mapFeature;
    }

    @Override
    public void update(long ms) {

        //dont forget to update state to 1 or 2(died) in myMapFeature
    }


    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }
}
