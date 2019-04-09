package Configs.ProjectilePackage;

import Configs.*;
import Configs.Behaviors.Behavior;

import java.util.List;

public class ProjectileConfig implements Viewable, Configurable, Updatable {
    private Shooter myShooter;
    @Configure
    List<Behavior<ProjectileConfig>> myBehaviors;
//    public ProjectileConfig(){
//         ProjectileOptions.values() how to get all options of the enum
//    }

    public ProjectileConfig(Shooter shooter) {
        myShooter = shooter;
    }

    public ProjectileConfig(ProjectileConfig projectileConfig){
        myBehaviors = projectileConfig.getMyBehaviors();
        myShooter = projectileConfig.getMyShooter();
    }

    private List<Behavior<ProjectileConfig>> getMyBehaviors() {
        return myBehaviors;
    }

    public Shooter getMyShooter() {
        return myShooter;
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
