package Configs.weaponsConfigPackage;


import Configs.Behavior;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Movable extends WeaponBehavior{
    double movingSpeed;
    List<Point> movingPattern;
    public Movable(Weapon weapon, List<Point> movingPattern, double movingSpeed){
        super(weapon);
        this.movingPattern = movingPattern;
        this.movingSpeed = movingSpeed;
    }
}
