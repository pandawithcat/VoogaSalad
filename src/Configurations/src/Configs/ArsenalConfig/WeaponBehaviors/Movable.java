package Configs.ArsenalConfig.WeaponBehaviors;


import Configs.Configuration;
import Configs.ArsenalConfig.WeaponConfig;

import java.awt.*;
import java.util.List;

public class Movable extends WeaponBehavior{
    private String myLabel;
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
    public void update(double ms) {
        //TODO
    }

    @Override
    public String getLabel() {
        return myLabel;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
