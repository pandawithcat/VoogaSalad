package Configs.ProjectilePackage.ProjectileBehaviors;

import Configs.ProjectilePackage.ProjectileConfig;

public abstract class ProjectileBehavior {
    ProjectileConfig myProjectileConfig;
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
