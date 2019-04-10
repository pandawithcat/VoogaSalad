package Configs.ProjectilePackage.ProjectileBehaviors;

<<<<<<< HEAD
import Configs.ArsenalConfig.Weapon;
import Configs.ProjectilePackage.Projectile;
=======
import Configs.ProjectilePackage.ProjectileConfig;
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860

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
