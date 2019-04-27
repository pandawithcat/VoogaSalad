package Configs.ShooterConfig.ShooterBehaviors;

import ActiveConfigs.ActiveLevel;
import ActiveConfigs.ActiveProjectile;
import ActiveConfigs.ActiveWeapon;
import Configs.*;
import Configs.ShooterConfig.Shooter;

public class Radial extends ShooterBehavior {
    public static final String DISPLAY_LABEL = "Radial Shooting";
    private transient Configuration myConfiguration;

    public Radial(Shooter shooter){
        super(shooter);
        myConfiguration = new Configuration(this);
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }

    @Override
    public void update(double ms, Updatable parent) {
        //NOTE: parent is the actual active weapon
        if(ms%getMyShooter().getRateOfFire()==0) {
            ActiveLevel myActiveLevel =  ((ActiveWeapon) parent).getActiveLevel();
            MapFeature myShooterMapFeature = ((ActiveWeapon) parent).getMapFeature();
            double weaponX = myShooterMapFeature.getPixelXPos();
            double weaponY = myShooterMapFeature.getPixelYPos();
            View view = ((ActiveWeapon) parent).getView();

            double width = view.getWidth();
            double height = view.getHeight();
            double projectileStartXPos = weaponX + width/2;
            double projectileStartYPos = weaponY + height/2;
            for(int i = 0 ;i<6;i++) {
                double direction = 60*i;
                MapFeature projectileMapFeature = new MapFeature(projectileStartXPos, projectileStartYPos,direction, getMyShooter().getProjectileConfig().getView(), getMyShooter().getMyShootable().getWeaponConfig().getMyArsenal().getGame().getActiveLevel().getPaneWidth(), myShooter.getMyShootable().getWeaponConfig().getMyArsenal().getGame().getActiveLevel().getPaneHeight(), myShooter.getMyShootable().getWeaponConfig().getMyArsenal().getGame().getActiveLevel().getGridWidth(), myShooter.getMyShootable().getWeaponConfig().getMyArsenal().getGame().getActiveLevel().getGridWidth());
                ActiveProjectile activeProjectile = new ActiveProjectile(getMyShooter().getProjectileConfig(), projectileMapFeature, getMyShooter().getShooterRange(), myActiveLevel);
                myActiveLevel.addToActiveProjectiles(activeProjectile);
                ((Shooter) parent).addToProjectilesFired(1);
            }
        }
    }
}
