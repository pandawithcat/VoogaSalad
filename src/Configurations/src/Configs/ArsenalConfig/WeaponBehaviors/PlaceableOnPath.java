package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.View;
import Configs.ArsenalConfig.Weapon;

import java.util.List;
import java.util.Optional;

public class PlaceableOnPath extends WeaponBehavior {
    @Configure
    int rangeOnPath;
    public PlaceableOnPath(Weapon weapon, Optional<Integer> rangeOnPath){
        super(weapon);
        this.rangeOnPath = rangeOnPath.get();
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<View> getViews() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
