package Configs.WeaponsConfig.WeaponBehaviors;

import Configs.View;
import Configs.WeaponsConfig.Weapon;

import java.util.List;

public class HealthExpirable extends WeaponBehavior{
    @Configure
    int amountOfHealth;
    public HealthExpirable(Weapon weapon, int amountOfHealth){
        super(weapon);
        this.amountOfHealth = amountOfHealth;
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<View> getViews() {
        return null;
    }
}
