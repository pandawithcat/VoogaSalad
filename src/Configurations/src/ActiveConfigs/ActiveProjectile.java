package ActiveConfigs;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.ProjectilePackage.ProjectileBehaviors.ProjectileBehavior;
import Configs.ProjectilePackage.ProjectileConfig;

import java.text.AttributedCharacterIterator;

public class ActiveProjectile extends ProjectileConfig implements Updatable, MapFeaturable {
    private MapFeature myMapFeature;
    private double distanceLeft;
    private ActiveLevel myActiveLevel;
    private double previousMs=0;



    public ActiveProjectile(ProjectileConfig projectileConfig,double distanceLeft, ActiveLevel activeLevel){
        super(projectileConfig);
        this.distanceLeft =distanceLeft;
        myActiveLevel = activeLevel;
    }

    @Override
    public void setMyMapFeature(MapFeature myMapFeature) {
        this.myMapFeature = myMapFeature;
    }

    @Override
    public void update(double ms, Updatable parent) {
        if(distanceLeft>0) {
            move(ms);

            if (getMyBehaviors()!=null) {
                for (ProjectileBehavior b : getMyBehaviors()) {
                    b.update(ms, this);
                }
            }
            checkforCollisions();
        }
        else {
            myMapFeature.setDisplayState(DisplayState.DIED);
        }

    }

    @Override
    public ActiveLevel getActiveLevel() {
        return myActiveLevel;
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
        myCell.getMyEnemies().forEach(e -> e.killMe());
        myMapFeature.setDisplayState(DisplayState.DIED);

    }
    private void move(double ms){
        double velocityMs = getVelocityInSeconds()/1000;
        double distanceToTravel = (velocityMs*(ms-previousMs));
        if(previousMs==0) {
            distanceToTravel = distanceLeft%distanceLeft;
        }
        previousMs = ms;
        double changeX = distanceToTravel*Math.cos(Math.toRadians(myMapFeature.getTrigDirection()));
        double changeY = distanceToTravel*Math.sin(Math.toRadians(myMapFeature.getTrigDirection()));
        myMapFeature.moveRelatively(changeX,changeY);
        distanceLeft-=distanceToTravel;
    }

    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }
}
