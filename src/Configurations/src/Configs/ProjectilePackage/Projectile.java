package Configs.ProjectilePackage;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.ArsenalConfig.WeaponBehaviors.Shooter;

import java.util.List;

public class Projectile implements Viewable, Configurable, Updatable {
    private Shooter myShooter;
    @Configure
    List<Behavior<Projectile>> myBehaviors;
//    public Projectile(){
//         ProjectileOptions.values() how to get all options of the enum
//    }

    public Projectile(Shooter shooter) {
        myShooter = shooter;
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<ImmutableImageView> getViewsToBeAdded() {
        return null;
    }

    @Override
    public List<ImmutableImageView> getViewsToBeRemoved() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}