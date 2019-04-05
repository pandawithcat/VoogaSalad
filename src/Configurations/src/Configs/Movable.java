package Configs;

import Configs.Behaviors.Behavior;

public class Movable implements Behavior {

    public enum Motion{
        FigureEight, Straight;
    }

    private double myVelocity;
    private Motion myMotionType;

    public void setVelocity(double velocity) {
        myVelocity = velocity;
    }

    public void setMotion(Motion type) {
        myMotionType = type;
    }
}
