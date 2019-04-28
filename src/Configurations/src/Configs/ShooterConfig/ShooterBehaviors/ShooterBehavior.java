package Configs.ShooterConfig.ShooterBehaviors;

import ActiveConfigs.ActiveLevel;
import ActiveConfigs.ActiveProjectile;
import ActiveConfigs.ActiveWeapon;
import Configs.Behaviors.Behavior;
import Configs.MapFeature;
import Configs.ShooterConfig.Shooter;
import Configs.View;

import java.util.List;

public abstract class ShooterBehavior implements Behavior<Shooter> {
    public static final String DISPLAY_LABEL = "Shooter Behavior";
    private Shooter myShooter;
    public static final List<Class> IMPLEMENTING_BEHAVIORS = List.of(Aiming.class, Radial.class);
    int startRound;

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

    protected void shoot(double ms, double direction){
        if((int)(ms/(1000/myShooter.getRateOfFire()))>startRound) {
            startRound = (int)(ms/(1000/myShooter.getRateOfFire()));
            ActiveWeapon activeWeapon = myShooter.getMyShootable().getActiveWeapon();
            ActiveLevel myActiveLevel =  activeWeapon.getActiveLevel();
            MapFeature myShooterMapFeature = activeWeapon.getMapFeature();
            double weaponX = myShooterMapFeature.getPixelXPos();
            double weaponY = myShooterMapFeature.getPixelYPos();
            View view = activeWeapon.getView();
            double width = view.getWidth();
            double height = view.getHeight();
            double projectileStartXPos = weaponX + width/2;
            double projectileStartYPos = weaponY + height/2;
            MapFeature projectileMapFeature = new MapFeature(projectileStartXPos, projectileStartYPos,direction, myShooter.getProjectileConfig().getView(), myActiveLevel.getPaneWidth(), myActiveLevel.getPaneHeight(), myActiveLevel.getGridWidth(), myActiveLevel.getGridWidth());
            ActiveProjectile activeProjectile = new ActiveProjectile(myShooter.getProjectileConfig(), projectileMapFeature, myShooter.getShooterRange(), myActiveLevel);
            myActiveLevel.addToActiveProjectiles(activeProjectile);
            myShooter.addToProjectilesFired(1);
            }
        }
}
