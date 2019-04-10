package Configs.ArsenalConfig.WeaponBehaviors;


import Configs.Configuration;
import Configs.ArsenalConfig.WeaponConfig;

import java.awt.*;
import java.util.List;

public class Movable extends WeaponBehavior{
    @Configure
    private double movingSpeed;
    @Configure
    private List<Point> movingPattern;
    private Configuration myConfiguration;

    public Movable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
