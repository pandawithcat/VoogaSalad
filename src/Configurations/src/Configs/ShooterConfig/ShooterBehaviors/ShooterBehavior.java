package Configs.ShooterConfig.ShooterBehaviors;

import Configs.Behaviors.Behavior;
import Configs.ShooterConfig.Shooter;

import java.util.List;

public abstract class ShooterBehavior implements Behavior<Shooter> {
    public static final String myLabel = "Shooter Behavior";
    private Shooter myShooter;
    public static final List<Class> IMPLEMENTING_BEHAVIORS = List.of(Aiming.class, Radial.class);

    ShooterBehavior(Shooter shooter){
        myShooter = shooter;
    }



    @Override
    public List<Class> getBehaviorOptions() {
        return IMPLEMENTING_BEHAVIORS;
    }
}
