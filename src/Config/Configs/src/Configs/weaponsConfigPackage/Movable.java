package Configs.weaponsConfigPackage;


import Configs.Behavior;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Movable extends  WeaponBehavior{
    Weapon myWeapon;
    double movingSpeed;
    List<Point> movingPattern = new ArrayList<Point>();
    public Movable(Weapon weapon, List<Point> movingPattern, double movingSpeed){
        super(weapon);
        this.movingPattern = movingPattern;
        this.movingSpeed = movingSpeed;
    }
}
