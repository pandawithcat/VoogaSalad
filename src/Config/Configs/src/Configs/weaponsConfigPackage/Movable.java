package Configs.weaponsConfigPackage;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Movable {
    double movingSpeed;
    List<Point> movingPattern = new ArrayList<>();
    public Movable(List<Point> movingPattern, double movingSpeed){
        this.movingPattern = movingPattern;
        this.movingSpeed = movingSpeed;
    }
}
