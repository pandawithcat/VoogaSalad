package Configs.WeaponsConfig.WeaponBehaviors;

import java.util.Optional;

public class PlaceableOnPath implements IWeaponBehavior {
    int rangeOnPath;
    public PlaceableOnPath(Optional<Integer> rangeOnPath){
        this.rangeOnPath = rangeOnPath.get();
    }
}
