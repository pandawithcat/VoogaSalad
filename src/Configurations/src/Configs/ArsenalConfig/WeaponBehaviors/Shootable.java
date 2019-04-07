package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.View;
import Configs.ArsenalConfig.Weapon;

import java.util.List;

public class Shootable extends WeaponBehavior{
    @Configure
    Shooter myShooter;
    Shootable(Weapon weapon){
        super(weapon);
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
