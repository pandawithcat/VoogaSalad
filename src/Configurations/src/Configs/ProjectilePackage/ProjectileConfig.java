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
    @Slider(min = 5, max = 20)
    @Configure
    private double velocityInSeconds;
    @Slider(min = 50, max = 10000)
    @Configure
    private double strength;
    @Configure
    private ProjectileBehavior[] myBehaviors;


    public ProjectileConfig(Shooter shooter) {
        myShooter = shooter;
        myConfiguration = new Configuration(this);
    }

    public ProjectileConfig(ProjectileConfig projectileConfig){
        myBehaviors = projectileConfig.getMyBehaviors();
        myShooter = projectileConfig.getMyShooter();
        myName = projectileConfig.myName;
        view = projectileConfig.view;
        velocityInSeconds = projectileConfig.velocityInSeconds;
        strength = projectileConfig.strength;
    }

    @Override
    public View getView() {
        return view;
    }

    public double getVelocityInSeconds() {
        return velocityInSeconds;
    }

    protected ProjectileBehavior[] getMyBehaviors() {
        return myBehaviors;
    }

    public double getStrength() {
        return strength;
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
