package Configs.WeaponsConfig;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Movable extends WeaponBehavior{
    double movingSpeed;
<<<<<<< HEAD
    List<Point> movingPattern = new ArrayList<>();
    public Movable(List<Point> movingPattern, double movingSpeed){
=======
    List<Point> movingPattern;
    public Movable(Weapon weapon, List<Point> movingPattern, double movingSpeed){
        super(weapon);
>>>>>>> 2cde7be41990862b4801ebd14630e1bf3cc4c917
        this.movingPattern = movingPattern;
        this.movingSpeed = movingSpeed;
    }
}
