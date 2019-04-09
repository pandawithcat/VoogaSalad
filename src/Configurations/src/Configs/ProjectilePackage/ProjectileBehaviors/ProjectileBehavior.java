package Configs.ProjectilePackage.ProjectileBehaviors;

import Configs.ProjectilePackage.Projectile;

public abstract class ProjectileBehavior {
    Projectile myProjectile;
    ProjectileBehavior(Projectile projectile){
        myProjectile = projectile;
    }

    public Projectile getMyProjectile() {
        return myProjectile;
    }

    public void setMyProjectile(Projectile myProjectile) {
        this.myProjectile = myProjectile;
    }
}
