package Configs.ProjectilePackage;

import Configs.ShooterConfig.Shooter;
import Configs.*;
import Configs.ProjectilePackage.ProjectileBehaviors.ProjectileBehavior;

public class ProjectileConfig implements Configurable, Viewable {
    private Shooter myShooter;

    Configuration myConfiguration;
    public static final String DISPLAY_LABEL="Projectile";
    @Configure
    private String myName;
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
    public String getName() {
        return myName;
    }

    public Shooter getMyShooter() {
        return myShooter;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
