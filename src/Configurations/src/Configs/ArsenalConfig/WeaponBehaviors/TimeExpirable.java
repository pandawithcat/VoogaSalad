package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.View;
import Configs.ArsenalConfig.Weapon;

import java.util.List;

public class TimeExpirable extends WeaponBehavior{
    @Configure
    double timeAlive;
    public TimeExpirable(Weapon weapon){
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
