package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.ImmutableImageView;
import Configs.ArsenalConfig.Weapon;

import java.util.List;

public class TimeExpirable extends WeaponBehavior{
    @Configure
    double timeAlive;

    Configuration myConfiguration;

    public TimeExpirable(Weapon weapon){
        super(weapon);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public List<ImmutableImageView> getViewsToBeAdded() {
        return null;
    }

    @Override
    public List<ImmutableImageView> getViewsToBeRemoved() {
        return null;
    }
}
