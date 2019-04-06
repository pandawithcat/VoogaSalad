package Configs.WeaponsConfig.WeaponBehaviors;

import Configs.View;
import Configs.WeaponsConfig.Weapon;

import java.util.List;

public class TimeExpirable extends WeaponBehavior{
    @Configure
    double timeAlive;
    public TimeExpirable(Weapon weapon, double timeAlive){
        super(weapon);
        this.timeAlive = timeAlive;
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<View> getViews() {
        return null;
    }
}
