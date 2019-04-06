package Configs.WeaponsConfig.WeaponBehaviors;

import Configs.Shooter;
import Configs.View;
import Configs.WeaponsConfig.Weapon;

import java.util.List;

public class Shootable extends WeaponBehavior{
    @Configure
    Shooter myShooter;
    Shootable(Weapon weapon, Shooter shooter){
        super(weapon);
        myShooter = shooter;
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<View> getViews() {
        return null;
    }
}
