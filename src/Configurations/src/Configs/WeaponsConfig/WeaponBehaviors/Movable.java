package Configs.WeaponsConfig.WeaponBehaviors;


import Configs.View;
import Configs.WeaponsConfig.Weapon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Movable extends WeaponBehavior{
    double movingSpeed;
    List<Point> movingPattern = new ArrayList<>();

    public Movable(Weapon weapon, List<Point> movingPattern, double movingSpeed){
        super(weapon);
        this.movingPattern = movingPattern;
        this.movingSpeed = movingSpeed;
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<View> getViews() {
        return null;
    }
}
