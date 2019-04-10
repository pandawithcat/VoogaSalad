package Configs.ArsenalConfig.WeaponBehaviors;


import Configs.Configuration;
import Configs.View;
import Configs.ArsenalConfig.Weapon;

import java.awt.*;
import java.util.List;

public class Movable extends WeaponBehavior{
    @Configure
    protected double movingSpeed;
    @Configure
    protected List<Point> movingPattern;
    private Configuration myConfiguration;

<<<<<<< HEAD
    public Movable(Weapon weapon){
        super(weapon);
=======
    public Movable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
    }

    @Override
    public void update(long ms) {

    }

    @Override
<<<<<<< HEAD
    public List<View> getViews() {
        return null;
    }

    @Override
=======
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
