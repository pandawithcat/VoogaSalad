package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.ImmutableImageView;
import Configs.ArsenalConfig.WeaponConfig;

import java.util.List;
import java.util.Optional;

public class PlaceableOnPath extends WeaponBehavior {
    public static final String myLabel = "Placeable On Path";
    @Configure
    protected int rangeOnPath;

    Configuration myConfiguration;

    public PlaceableOnPath(WeaponConfig weaponConfig, Optional<Integer> rangeOnPath){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
        this.rangeOnPath = rangeOnPath.get();
    }

    @Override
    public void update(long ms) {
        //TODO
    }

    @Override
    public String getName() {
        return myLabel;
    }


    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
