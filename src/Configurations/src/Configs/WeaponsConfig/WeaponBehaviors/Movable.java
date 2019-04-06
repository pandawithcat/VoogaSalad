package Configs.WeaponsConfig.WeaponBehaviors;


import Configs.Configuration;
import Configs.View;
import Configs.WeaponsConfig.Weapon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Movable extends WeaponBehavior{
    @Configure
    private double movingSpeed;
    @Configure
    List<Point> movingPattern = new ArrayList<>();

    Configuration myConfiguration;

    public Movable(Weapon weapon){
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
