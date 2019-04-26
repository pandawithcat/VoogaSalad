package Configs.ArsenalConfig.WeaponBehaviors;

import ActiveConfigs.ActiveWeapon;
import Configs.Configuration;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.DisplayState;
import Configs.Updatable;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Arrays;
import java.util.List;

public class HealthExpirable extends WeaponBehavior{
    public static final String DISPLAY_LABEL = "Health-Expirable";
    @Configure
    protected int amountOfHealth;

    Configuration myConfiguration;
    @XStreamOmitField
    private transient int damage;

    public HealthExpirable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms, Updatable parent) {
        if(damage>=amountOfHealth) {
            ((ActiveWeapon) parent).getMapFeature().setDisplayState(DisplayState.DIED);
        }
    }

    public void damage(int damage) {
        this.damage-=damage;
    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }
    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
