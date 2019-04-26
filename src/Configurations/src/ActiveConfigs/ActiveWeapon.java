package ActiveConfigs;

import Configs.*;
import Configs.ArsenalConfig.WeaponBehaviors.HealthExpirable;
import Configs.ArsenalConfig.WeaponBehaviors.PlaceableOnPath;
import Configs.ArsenalConfig.WeaponBehaviors.WeaponBehavior;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Behaviors.Behavior;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class ActiveWeapon extends WeaponConfig implements Updatable, MapFeaturable,Attackable {
    private MapFeature myMapFeature;
    private ActiveLevel myActiveLevel;

    public ActiveWeapon(WeaponConfig weaponConfig, MapFeature mapFeature, ActiveLevel activeLevel) {
        super(weaponConfig);
        myMapFeature = mapFeature;
        myActiveLevel = activeLevel;
    }



    @Override
    public void update(double ms, Updatable parent) {
        Arrays.stream(getBehaviors()).forEach(b -> b.update(ms, this));
    }

    @Override
    public void attack(int damage) {
        if(isHealthExpirable()) {
            HealthExpirable healthExpirable = (HealthExpirable) Arrays.asList(getBehaviors()).stream().filter(behavior -> behavior instanceof HealthExpirable).collect(Collectors.toList()).get(0);
            healthExpirable.damage(damage);
        }
    }

    private boolean isHealthExpirable() {
        return Arrays.asList(getBehaviors()).stream().anyMatch(behavior -> behavior instanceof HealthExpirable);
    }


    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }
}
