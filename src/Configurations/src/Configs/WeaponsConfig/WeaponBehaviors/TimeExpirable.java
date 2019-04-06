package Configs.WeaponsConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.View;
import Configs.WeaponsConfig.Weapon;

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
    public List<View> getViews() {
        return null;
    }
}
