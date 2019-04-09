package ActiveConfigs;

import Configs.MapFeature;
import Configs.ShooterConfig.ShooterConfig;
import Configs.Updatable;
import Configs.View;

public class Shooter extends ShooterConfig implements Updatable {
    //TODO after demo: implement behaviors

    public Shooter(ShooterConfig shooterConfig){
        super(shooterConfig);
    }

    @Override
    public void update(long ms) {
        //only shooting radially rn
        if(ms%getRateOfFire()==0) {
            ActiveLevel myActiveLevel = getMyShootable().getWeaponConfig().getMyArsenal().getLevel().getParent().getActiveLevel();
            int weaponId = getMyShootable().getWeaponConfig().getWeaponId();
<<<<<<< HEAD
            MapFeature myShooterMapFeature = getMyShootable().getWeaponConfig().getArsenal().getLevel().getGame().getActiveLevel().getActiveWeapon(weaponId).getMapFeature();
            double weaponX = myShooterMapFeature.getPixelXPos();
            double weaponY = myShooterMapFeature.getPixelYPos();
            View view = getMyShootable().getWeaponConfig().getArsenal().getLevel().getGame().getActiveLevel().getActiveWeapon(weaponId).getView();
=======
            MapFeature myShooterMapFeature = myActiveLevel.getActiveWeapon(weaponId).getMapFeature();
            double weaponX = myShooterMapFeature.getPixelXPos();
            double weaponY = myShooterMapFeature.getPixelYPos();
            View view = myActiveLevel.getActiveWeapon(weaponId).getView();
>>>>>>> d225e3e0eda93dc52adcd42da7f402ba32a27e7e
            double width = view.getWidth();
            double height = view.getHeight();
            double projectileStartXPos = weaponX + width/2;
            double projectileStartYPos = weaponY + height/2;
            for(int i = 0 ;i<6;i++) {
                double direction = 60*i;
                MapFeature projectileMapFeature = new MapFeature(projectileStartXPos, projectileStartYPos,direction, getProjectileConfig().getView());
<<<<<<< HEAD
                ActiveProjectile activeProjectile = new ActiveProjectile(getProjectileConfig(), projectileMapFeature, getShooterRange());
                getMyShootable().getWeaponConfig().getArsenal().getLevel().getGame().getActiveLevel().addToActiveProjectiles(activeProjectile);
=======
                ActiveProjectile activeProjectile = new ActiveProjectile(getProjectileConfig(), projectileMapFeature, getRadius(), myActiveLevel);
                getMyShootable().getWeaponConfig().getMyArsenal().getLevel().getParent().getActiveLevel().addToActiveProjectiles(activeProjectile);
>>>>>>> d225e3e0eda93dc52adcd42da7f402ba32a27e7e
            }
        }



    }
}
