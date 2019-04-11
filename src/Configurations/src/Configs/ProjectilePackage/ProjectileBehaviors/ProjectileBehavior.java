package Configs.ProjectilePackage.ProjectileBehaviors;

import Configs.Behaviors.Behavior;
import Configs.LevelPackage.Level;
import Configs.ProjectilePackage.ProjectileConfig;

import java.util.List;

public abstract class ProjectileBehavior implements Behavior<ProjectileConfig> {
    ProjectileConfig myProjectileConfig;
    public static final List<Class> IMPLEMENTING_BEHAVIORS = List.of();

    ProjectileBehavior(ProjectileConfig projectileConfig){
        myProjectileConfig = projectileConfig;
    }

    public ProjectileConfig getMyProjectileConfig() {
        return myProjectileConfig;
    }

    public void setMyProjectileConfig(ProjectileConfig myProjectileConfig) {
        this.myProjectileConfig = myProjectileConfig;
    }

    @Override
    public List<Class> getBehaviorOptions() {
        return IMPLEMENTING_BEHAVIORS;
    }
}
