package Configs.WeaponsConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.View;
import Configs.WeaponsConfig.Weapon;

import java.util.List;

public class AmmoExpirable extends WeaponBehavior {
    @Configure
    int numberOfEnemiesPossibleToKill;

    public AmmoExpirable(Weapon weapon, int numberOfEnemiesPossibleToKill){
        super(weapon);
        this.numberOfEnemiesPossibleToKill = numberOfEnemiesPossibleToKill;
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