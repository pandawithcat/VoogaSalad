package Configs.ProjectilePackage;

import ActiveConfigs.Shooter;
import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.ProjectilePackage.ProjectileBehaviors.ProjectileBehavior;
import Configs.ShooterConfig.ShooterConfig;

import java.util.List;

public class ProjectileConfig implements Configurable, Viewable {
    private Shooter myShooter;

    Configuration myConfiguration;
    private String myLabel;
    @Configure
    private String name;
    @Configure
    private View view;
    @Configure
    private double velocityInSeconds;

    //TODO: after first sprint implement behaviors
    @Configure
    private ProjectileBehavior[] myBehaviors;


    public ProjectileConfig(Shooter shooter) {
        myShooter = shooter;
        myConfiguration = new Configuration(this);
    }

    public ProjectileConfig(ProjectileConfig projectileConfig){
//        myBehaviors = projectileConfig.getMyBehaviors();
        myShooter = projectileConfig.getMyShooter();
    }

    @Override
    public View getView() {
        return view;
    }

    public double getVelocityInSeconds() {
        return velocityInSeconds;
    }

    private ProjectileBehavior[] getMyBehaviors() {
        return myBehaviors;
    }

    @Override
    public String getLabel() {
        return myLabel;
    }

    public Shooter getMyShooter() {
        return myShooter;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
