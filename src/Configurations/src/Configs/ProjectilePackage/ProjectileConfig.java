package Configs.ProjectilePackage;

import ActiveConfigs.Shooter;
import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.ShooterConfig.ShooterConfig;

import java.util.List;

public class ProjectileConfig implements Configurable, Viewable {
    private ShooterConfig myShooter;

    Configuration myConfiguration;
    @Configure
    protected View view;
    @Configure
    protected double velocityInSeconds;

    //TODO: after first sprint implement behaviors
//    @Configure
//    private List<Behavior<ProjectileConfig>> myBehaviors;

//    public ProjectileConfig(){
//         ProjectileOptions.values() how to get all options of the enum
//    }

    public ProjectileConfig(ShooterConfig shooter) {
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

    //    private List<Behavior<ProjectileConfig>> getMyBehaviors() {
//        return myBehaviors;
//    }

    public ShooterConfig getMyShooter() {
        return myShooter;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
