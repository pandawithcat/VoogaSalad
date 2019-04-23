package Configs.ShooterConfig.ShooterBehaviors;

import ActiveConfigs.ActiveLevel;
import ActiveConfigs.ActiveProjectile;
import Configs.*;
import Configs.ShooterConfig.Shooter;

public class Radial implements Updatable, Configurable {
    public static final String myLabel = "Radial Shooting";
    private Configuration myConfiguration;
    private Shooter myShooter;

    public Radial(Shooter shooter){
        myShooter = shooter;
        myConfiguration = new Configuration(this);
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public String getName() {
        return myLabel;
    }

    @Override
    public void update(double ms) {
        if(ms%myShooter.getRateOfFire()==0) {
            ActiveLevel myActiveLevel =  myShooter.getMyShootable().getWeaponConfig().getMyArsenal().getGame().getActiveLevel();
            int weaponId = myShooter.getMyShootable().getWeaponConfig().getWeaponId();
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
                MapFeature projectileMapFeature = new MapFeature(projectileStartXPos, projectileStartYPos,direction, myShooter.getProjectileConfig().getView(), myShooter.getMyShootable().getWeaponConfig().getMyArsenal().getGame().getActiveLevel().getPaneWidth(), myShooter.getMyShootable().getWeaponConfig().getMyArsenal().getGame().getActiveLevel().getPaneHeight(), myShooter.getMyShootable().getWeaponConfig().getMyArsenal().getGame().getActiveLevel().getGridWidth(), myShooter.getMyShootable().getWeaponConfig().getMyArsenal().getGame().getActiveLevel().getGridWidth());
                ActiveProjectile activeProjectile = new ActiveProjectile(myShooter.getProjectileConfig(), projectileMapFeature, myShooter.getShooterRange(), myActiveLevel);
                myActiveLevel.addToActiveProjectiles(activeProjectile);
            }
        }
    }
}
