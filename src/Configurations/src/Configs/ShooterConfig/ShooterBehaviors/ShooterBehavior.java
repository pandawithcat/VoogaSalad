package Configs.ShooterConfig.ShooterBehaviors;

import ActiveConfigs.ActiveLevel;
import ActiveConfigs.ActiveProjectile;
import ActiveConfigs.ActiveWeapon;
import Configs.Behaviors.Behavior;
import Configs.MapFeature;
import Configs.ShooterConfig.Shooter;
import Configs.View;

import java.util.Arrays;
import java.util.List;

public abstract class ShooterBehavior implements Behavior<Shooter> {
    public static final String DISPLAY_LABEL = "Shooter Behavior";
    private Shooter myShooter;
    public static final List<Class> IMPLEMENTING_BEHAVIORS = List.of(Aiming.class, Radial.class);
    int startRound=0;

    ShooterBehavior(Shooter shooter){
        myShooter = shooter;
    }

    public Shooter getMyShooter() {
        return myShooter;
    }

    @Override
    public List<Class> getBehaviorOptions() {
        return IMPLEMENTING_BEHAVIORS;
    }

    protected void shoot(double... direction){
            ActiveWeapon activeWeapon = myShooter.getMyShootable().getActiveWeapon();
            ActiveLevel myActiveLevel =  activeWeapon.getActiveLevel();
            MapFeature myShooterMapFeature = activeWeapon.getMapFeature();
            double weaponX = myShooterMapFeature.getPixelXPos();
            double weaponY = myShooterMapFeature.getPixelYPos();
            double width = myShooterMapFeature.getPixelWidth();
            double height = myShooterMapFeature.getPixelHeight();
            double projectileStartXPos = weaponX + width/2;
            double projectileStartYPos = weaponY + height/2;
            for(Double dir:direction) {
                MapFeature projectileMapFeature = new MapFeature(projectileStartXPos, projectileStartYPos,dir, myShooter.getProjectileConfig().getView(), myActiveLevel.getPaneWidth(), myActiveLevel.getPaneHeight(), myActiveLevel.getGridWidth(), myActiveLevel.getGridWidth());
                ActiveProjectile activeProjectile = new ActiveProjectile(myShooter.getProjectileConfig(), projectileMapFeature, myShooter.getShooterRange(), myActiveLevel);
                myActiveLevel.addToActiveProjectiles(activeProjectile);
                myShooter.addToProjectilesFired();
            }

    }




}
