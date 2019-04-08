package Configs.ArsenalConfig.WeaponBehaviors;


import Configs.Configuration;
import Configs.View;
import Configs.ArsenalConfig.Weapon;

import java.awt.*;
import java.util.List;

public class Movable extends WeaponBehavior{
    @Configure
    private double movingSpeed;
    @Configure
    private List<Point> movingPattern;

    public Movable(Weapon weapon){
        super(weapon);
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<ImmutableImageView> getViewsToBeAdded() {
        return null;
    }

    @Override
    public List<ImmutableImageView> getViewsToBeRemoved() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
