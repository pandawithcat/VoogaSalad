package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Behaviors.Behavior;
import Configs.Configurable;
import Configs.Configuration;
import Configs.ImmutableImageView;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Updatable;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.List;
import java.util.Optional;

public class PlaceableOnPath extends WeaponBehavior {
    public static final String DISPLAY_LABEL = "Placeable On Path";
    @Slider(min = 50,max = 10000)
    @Configure
    protected int damage;

    @XStreamOmitField
    private transient Configuration myConfiguration;

    public PlaceableOnPath(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms, Updatable parent) {
        //TODO: does anything even have to be in here?
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }


    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public Behavior copy() {
        PlaceableOnPath ret = new PlaceableOnPath(getMyWeaponConfig());
        ret.damage = damage;
        return ret;
    }
}
