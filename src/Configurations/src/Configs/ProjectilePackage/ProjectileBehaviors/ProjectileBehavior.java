package Configs.ProjectilePackage.ProjectileBehaviors;

import Configs.ProjectilePackage.ProjectileConfig;

import java.util.List;

public abstract class ProjectileBehavior {
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
}
