package ActiveConfigs;

import Configs.MapFeature;
import Configs.ShooterConfig.ShooterConfig;
import Configs.Updatable;
import Configs.View;

public class Shooter extends ShooterConfig implements Updatable {
    //TODO after demo: implement behaviors

    public Shooter(ShooterConfig shooterConfig, ActiveLevel activeLevel){
        super(shooterConfig);
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
