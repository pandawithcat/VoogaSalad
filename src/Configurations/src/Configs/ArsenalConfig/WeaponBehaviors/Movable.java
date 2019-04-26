package Configs.ArsenalConfig.WeaponBehaviors;


import Configs.Configuration;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Updatable;

import java.awt.*;
import java.util.List;

public class Movable extends WeaponBehavior{
    public static final String DISPLAY_LABEL = "Movable";
    @Configure
    protected double movingSpeed;
    @Configure
    protected Point[] movingPattern;
    private Configuration myConfiguration;

    public Movable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms, Updatable parent) {
        //TODO
    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
