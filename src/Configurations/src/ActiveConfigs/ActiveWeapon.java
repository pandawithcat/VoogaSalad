package ActiveConfigs;

import Configs.*;
import Configs.ArsenalConfig.WeaponConfig;

import java.util.Arrays;
import java.util.Map;

public class ActiveWeapon extends WeaponConfig implements Updatable, MapFeaturable {
    private MapFeature myMapFeature;
    private ActiveLevel myActiveLevel;

    public ActiveWeapon(WeaponConfig weaponConfig, MapFeature mapFeature, ActiveLevel activeLevel) {
        super(weaponConfig);
        myMapFeature = mapFeature;
        myActiveLevel = activeLevel;
    }

    @Override
    public void update(long ms) {
        Arrays.stream(getBehaviors()).forEach(b -> b.update(ms));

        updateWeaponDisplayState();

        //dont forget to update state to 1 or 2(died) in myMapFeature
    }

    private void updateWeaponDisplayState(){
        if(false){
            myMapFeature.setDisplayState(DisplayState.DIED);
        }
        myMapFeature.setDisplayState(DisplayState.PRESENT);
    }
    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }
}