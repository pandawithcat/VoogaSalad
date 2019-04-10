package ActiveConfigs;

import Configs.*;
import Configs.ProjectilePackage.ProjectileConfig;

import java.text.AttributedCharacterIterator;

public class ActiveProjectile extends ProjectileConfig implements Updatable, MapFeaturable {
    private MapFeature myMapFeature;
    private double distanceLeft;
    private ActiveLevel myActiveLevel;



    public ActiveProjectile(ProjectileConfig projectileConfig, MapFeature mapFeature, double distanceLeft, ActiveLevel activeLevel){
        super(projectileConfig);
        this.distanceLeft =distanceLeft;
        myMapFeature = mapFeature;
        myActiveLevel = activeLevel;
    }

    @Override
    public void update(long ms) {
        if(distanceLeft>0) {
            myMapFeature.setDisplayState(DisplayState.PRESENT);
            move(ms);
            checkforCollisions();

        }
        else {
            myMapFeature.setDisplayState(DisplayState.DIED);
        }

    }

    private void checkforCollisions(){
        int myGridX = myMapFeature.getGridXPos();
        int myGridY = myMapFeature.getGridYPos();
        Cell myCell = myActiveLevel.getGridCell(myGridX, myGridY);
        if (myCell.getMyEnemies().size()>0){
            handleEnemyCollision(myCell);
        }
    }

    private void handleEnemyCollision(Cell myCell){
        //TODO: Incorporate the behaviors (weapon/projectile strength/power/features) into it
        for (ActiveEnemy e : myCell.getMyEnemies()){
            e.killMe();
        }
        myMapFeature.setDisplayState(DisplayState.DIED);
    }
    private void move(long ms){
        double velocityMs = getVelocityInSeconds()/1000;
        double distanceToTravel = velocityMs*ms;
        double changeX = distanceToTravel*Math.cos(myMapFeature.getTrigDirection());
        double changeY = distanceToTravel*Math.sin(myMapFeature.getTrigDirection());
        myMapFeature.moveRelatively(changeX,changeY);
        distanceLeft-=distanceToTravel;

    }
    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }
}
