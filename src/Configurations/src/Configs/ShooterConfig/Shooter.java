package Configs.ShooterConfig;

import ActiveConfigs.ActiveLevel;
import ActiveConfigs.ActiveProjectile;
import Configs.*;
import Configs.ArsenalConfig.WeaponBehaviors.Shootable;
import Configs.ProjectilePackage.ProjectileConfig;

public class Shooter implements Updatable , Configurable {
    //TODO after demo: implement behaviors

    private Shootable myShootable;

    @Configure
    private String myLabel;
    @Configure
    private double rateOfFire;
    @Configure
    private ProjectileConfig projectileConfig;
    @Configure
    private double shooterRange;
    private Configuration myConfiguration;

    public Shooter(Shootable shootable){
        myShootable = shootable;
        myConfiguration = new Configuration(this);
    }

    public Shootable getMyShootable() {
        return myShootable;
    }

    public double getShooterRange() {
        return shooterRange;
    }

    public ProjectileConfig getProjectileConfig() {
        return projectileConfig;
    }

    public double getRateOfFire() {
        return rateOfFire;
    }

    @Override
    public String getLabel() {
        return myLabel;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public void update(double ms) {
        //only shooting radially rn
        if(ms%getRateOfFire()==0) {
            ActiveLevel myActiveLevel =  getMyShootable().getWeaponConfig().getMyArsenal().getGame().getActiveLevel();
            int weaponId = getMyShootable().getWeaponConfig().getWeaponId();
            MapFeature myShooterMapFeature = myActiveLevel.getActiveWeapon(weaponId).getMapFeature();
            double weaponX = myShooterMapFeature.getPixelXPos();
            double weaponY = myShooterMapFeature.getPixelYPos();
            View view = myActiveLevel.getActiveWeapon(weaponId).getView();

            double width = view.getWidth();
            double height = view.getHeight();
            double projectileStartXPos = weaponX + width/2;
            double projectileStartYPos = weaponY + height/2;
            for(int i = 0 ;i<6;i++) {
                double direction = 60*i;
                MapFeature projectileMapFeature = new MapFeature(projectileStartXPos, projectileStartYPos,direction, getProjectileConfig().getView());
                ActiveProjectile activeProjectile = new ActiveProjectile(getProjectileConfig(), projectileMapFeature, getShooterRange(), myActiveLevel);
                myActiveLevel.addToActiveProjectiles(activeProjectile);
            }
        }
    }
}
