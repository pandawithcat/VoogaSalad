package ActiveConfigs;

import Configs.*;
import Configs.EnemyPackage.EnemyConfig;


public class ActiveEnemy extends EnemyConfig implements Updatable, MapFeaturable {
    private MapFeature myMapFeature;
    private Cell[][] activeMapGrid;
    private double distance = 0;
    private ActiveLevel myActiveLevel;

    enum MovementDirection {
        UP(0, 1, 0),
        DOWN(0, -1, 180),
        LEFT(-1, 0, 270),
        RIGHT(1, 0, 90);

        int x;
        int y;
        int direction;

        MovementDirection(int x, int y, int direction){
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public int getDirection() {
            return direction;
        }
    }


    public ActiveEnemy(EnemyConfig enemyConfig, MapFeature mapFeature, ActiveLevel activeLevel) {
        super(enemyConfig);
        myMapFeature = mapFeature;
        myActiveLevel = activeLevel;
    }


    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }


    @Override
    public void update(long ms) {
        //get x, y from myMapFeature and do logic using the map within the activeLevel
//        if
        //dont forget to update state to PRESENT or DIED in myMapFeature

        myMapFeature.setDisplayState(DisplayState.PRESENT);
        distance += ms* getUnitSpeedPerSecond();
        int numMovements = (int) distance;
        distance -= numMovements;
        for (int i = 0; i < numMovements; i++) {
            MovementDirection movementDirection = determineMovementDirection();
            myMapFeature.setGridPos(myMapFeature.getGridXPos()+movementDirection.getX(), myMapFeature.getGridYPos()+movementDirection.getY(),movementDirection.getDirection());
        }
    }

    private MovementDirection determineMovementDirection(){
        int[]xAdditions = new int[]{0,0,-1,1};
        int[]yAdditions = new int[]{1,-1,0,0};
        int bestOption = 0;
        int bestOptionHeuristic =  Integer.MAX_VALUE;
        for (int k = 0; k < 3; k++) {
            int totalHeuristic = 0;
            for (int i = myMapFeature.getGridYPos()+yAdditions[k]; i < getView().getHeight()+xAdditions[k]; i++) {
                for (int j = myMapFeature.getGridXPos()+xAdditions[k]; j < getView().getWidth()+xAdditions[k]; j++) {
                    totalHeuristic+=myActiveLevel.getGridCell(i,j).getMovementHeuristic();
                }
            }
            if (totalHeuristic<bestOptionHeuristic){
                bestOption = k;
            }
        }
        return MovementDirection.values()[bestOption];
    }

    public void killMe(){
        myMapFeature.setDisplayState(DisplayState.DIED);
    }
}
