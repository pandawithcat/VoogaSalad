package Configs.ProjectilePackage;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.ArsenalConfig.WeaponBehaviors.Shooter;

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

    }

    @Override
    public void update(long ms) {

    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
