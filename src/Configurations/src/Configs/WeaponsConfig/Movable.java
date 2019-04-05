package Configs.WeaponsConfig;


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
}
