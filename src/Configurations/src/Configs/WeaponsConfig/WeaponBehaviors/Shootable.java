package Configs.WeaponsConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.Shooter;
import Configs.View;
import Configs.WeaponsConfig.Weapon;

import java.util.List;

public class Shootable extends WeaponBehavior{
    @Configure
    Shooter myShooter;

    Configuration myConfiguration;

    Shootable(Weapon weapon, Shooter shooter){
        super(weapon);
        myConfiguration = new Configuration(this);
        myShooter = shooter;
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
