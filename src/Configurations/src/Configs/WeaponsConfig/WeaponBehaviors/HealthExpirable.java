package Configs.WeaponsConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.View;
import Configs.WeaponsConfig.Weapon;

import java.util.List;

public class HealthExpirable extends WeaponBehavior{
    @Configure
    int amountOfHealth;

    Configuration myConfiguration;

    public HealthExpirable(Weapon weapon, int amountOfHealth){
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
