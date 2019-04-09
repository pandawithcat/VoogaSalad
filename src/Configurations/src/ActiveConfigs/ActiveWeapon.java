package ActiveConfigs;

import Configs.*;
import Configs.ArsenalConfig.WeaponConfig;

import java.util.Arrays;
import java.util.Map;

public class ActiveWeapon extends WeaponConfig implements Updatable, MapFeaturable {
    private MapFeature myMapFeature;

    public ActiveWeapon(WeaponConfig weaponConfig, MapFeature mapFeature) {
        super(weaponConfig);
        myMapFeature = mapFeature;
    }

    @Override
    public void update(long ms) {
        Arrays.stream(getBehaviors()).forEach(b -> b.update(ms));
        myMapFeature.setDisplayState(DisplayState.PRESENT);
        //dont forget to update state to 1 or 2(died) in myMapFeature
    }


    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }
}
