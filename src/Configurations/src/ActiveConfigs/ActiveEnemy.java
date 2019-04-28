package ActiveConfigs;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.EnemyPackage.EnemyConfig;
import Configs.MapPackage.Terrain;
import Configs.MapPackage.TerrainBehaviors.SpeedModifier;
import Configs.MapPackage.TerrainBehaviors.TerrainBehavior;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;


public class ActiveEnemy extends EnemyConfig implements Updatable, MapFeaturable, Attackable {
    public static final double CONVERSION_TO_SECONDS = .001;
    private MapFeature myMapFeature;
    private Cell[][] activeMapGrid;
    private double distance = 0;
    private ActiveLevel myActiveLevel;
    private double startTime = -Integer.MAX_VALUE;
    private LinkedList<Point> prevLocations = new LinkedList<>();
    private double effectiveSpeed;

    enum MovementDirection {
        DOWN(0, 1, 0),
        UP(0, -1, 180),
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

    enum AITypes{
        SHORTEST_PATH,
        SHORTEST_IGNORE_PATH,
        SHORTEST_PATH_AVOID_WEAPON,
        SHORTEST_IGNORE_PATH_AVOID_WEAPON,
    }


    public ActiveEnemy(EnemyConfig enemyConfig, MapFeature mapFeature, ActiveLevel activeLevel) {
        super(enemyConfig);
        myMapFeature = mapFeature;
        myActiveLevel = activeLevel;
    }

    @Override
    public void attack(int damage) {
        //TODO: FINISH
    }

    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }


    @Override
    public void update(double ms, Updatable parent) {
        TerrainBehavior[] tbs = myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).getMyTerrain().getTerrainBehaviors() ;
        if (tbs!=null) {
            ArrayList<TerrainBehavior> behaviorsList = new ArrayList<TerrainBehavior>(Arrays.asList(tbs));
            for (TerrainBehavior b : behaviorsList) {
                if (b.getClass() == SpeedModifier.class) {
                    effectiveSpeed = this.getUnitSpeedPerSecond() * ((SpeedModifier) (b)).getSpeedMultiplier();
                    break;
                }
                effectiveSpeed = this.getUnitSpeedPerSecond();
            }
        }
        //get x, y from myMapFeature and do logic using the map within the activeLevel
//        if
        //dont forget to update state to PRESENT or DIED in myMapFeature

        System.out.println(myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).getMyTerrain());
        System.out.println(myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).getShortestDistanceHeuristic());
        System.out.println(myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).getShortestDistanceHeuristicIgnorePath());
        System.out.println(myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).getShortestDistanceHeuristicAvoidWeapons());
        System.out.println(myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).getShortestDistanceHeuristicAvoidWeaponsIgnorePath());
//        System.out.println(myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).);


        if (startTime == -Integer.MAX_VALUE){
            startTime = ms;
        }

        int numMovements = 1;

        for (int i = 0; i < numMovements; i++) {
            MovementDirection movementDirection = determineMovementDirection();
            int newX = myMapFeature.getGridXPos()+movementDirection.getX();
            int newY = myMapFeature.getGridYPos()+movementDirection.getY();
            prevLocations.addFirst(new Point(newX, newY));
            if (prevLocations.size()>5){
                prevLocations.removeLast();
            }
            myMapFeature.setGridPos(newX, newY,movementDirection.getDirection());
        }
    }



    private MovementDirection determineMovementDirection(){
        return moveShortestDistance(AITypes.SHORTEST_PATH);
    }

    private MovementDirection moveShortestDistance(AITypes aiTypes) {
        int[]xAdditions = new int[]{0,0,-1,1};
        int[]yAdditions = new int[]{1,-1,0,0};
        int bestOption = 0;
        int bestOptionHeuristic =  Integer.MAX_VALUE;
        for (int k = 0; k < 4; k++) {
            int totalHeuristic = -Integer.MAX_VALUE;
            singleDirection:
            for (int i = 0; i < getView().getHeight(); i++) {
                for (int j = 0; j < getView().getWidth(); j++) {
                    int x = myMapFeature.getGridXPos()+xAdditions[k]+j;
                    int y = myMapFeature.getGridYPos()+yAdditions[k]+i;
                    Point newxy = new Point(x,y);
                    if (isCellValid(x,y)&& !prevLocations.contains(newxy)){
                        Cell myCell = myActiveLevel.getGridCell(x,y);
                        if (aiTypes == AITypes.SHORTEST_PATH) {
                            totalHeuristic += myCell.getShortestDistanceHeuristic() / getView().getHeight() / getView().getWidth();
                        }
                        if (aiTypes == AITypes.SHORTEST_IGNORE_PATH) {
                            totalHeuristic += myCell.getShortestDistanceHeuristicIgnorePath() / getView().getHeight() / getView().getWidth();
                        }
                        if (aiTypes == AITypes.SHORTEST_PATH_AVOID_WEAPON) {
                            totalHeuristic += myCell.getShortestDistanceHeuristicAvoidWeapons() / getView().getHeight() / getView().getWidth();
                        }
                        if (aiTypes == AITypes.SHORTEST_IGNORE_PATH_AVOID_WEAPON) {
                            totalHeuristic += myCell.getShortestDistanceHeuristicAvoidWeaponsIgnorePath() / getView().getHeight() / getView().getWidth();
                        }
                    }
                    else {
                        totalHeuristic = Integer.MAX_VALUE;
                        break singleDirection;
                    }
                }
            }
            if (totalHeuristic<bestOptionHeuristic){
                bestOption = k;
                bestOptionHeuristic = totalHeuristic;
            }
            if (totalHeuristic==bestOptionHeuristic){
//                bestOption = k;
//                bestOptionHeuristic = totalHeuristic;
//                TODO logic to randomize if equal
            }
        }
        return MovementDirection.values()[bestOption];
    }

    private boolean isCellValid(int x, int y){
        if (x<0|x>=myActiveLevel.getMyMapConfig().getGridWidth()){
            return false;
        }
        return !(y < 0 | y >= myActiveLevel.getMyMapConfig().getGridHeight());
    }

    public void killMe(){
        myMapFeature.setDisplayState(DisplayState.DIED);
        //TODO: Make these magic numbers reference the qualities of the enemy
        myActiveLevel.addGameCash(1);
        myActiveLevel.addGameScore(5);
    }
}
