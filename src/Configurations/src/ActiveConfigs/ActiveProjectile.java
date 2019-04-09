package ActiveConfigs;

import Configs.*;
import Configs.ProjectilePackage.ProjectileConfig;

public class ActiveProjectile extends ProjectileConfig implements Updatable, MapFeaturable {
    private MapFeature myMapFeature;
    private double distanceLeft;



    public ActiveProjectile(ProjectileConfig projectileConfig, MapFeature mapFeature, double distanceLeft){
        super(projectileConfig);
        this.distanceLeft =distanceLeft;
        myMapFeature = mapFeature;
    }

    @Override
    public void update(long ms) {
        if(distanceLeft>0) {
            myMapFeature.setDisplayState(DisplayState.PRESENT);
            double velocityMs = getVelocityInSeconds()/1000;
            double distanceToTravel = velocityMs*ms;
            double changeX = distanceToTravel*Math.cos(myMapFeature.getTrigDirection());
            double changeY = distanceToTravel*Math.sin(myMapFeature.getTrigDirection());
            myMapFeature.moveRelatively(changeX,changeY);
            distanceLeft-=distanceToTravel;
        }
        else {
            myMapFeature.setDisplayState(DisplayState.DIED);
        }

    }


    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }
}
