package ActiveConfigs;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.EnemyPackage.EnemyConfig;
import Configs.MapPackage.Terrain;
//import Configs.MapPackage.TerrainBehaviors.SpeedModifier;
import Configs.MapPackage.TerrainBehaviors.TerrainBehavior;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


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
        DOWN(0, 1, 180),
        UP(0, -1, 0),
        LEFT(-1, 0, 90),
        RIGHT(1, 0, 270);

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


    public ActiveEnemy(EnemyConfig enemyConfig,ActiveLevel activeLevel) {
        super(enemyConfig);
        myActiveLevel = activeLevel;
    }

    public void setMyMapFeature(MapFeature mapFeature) {
        this.myMapFeature = mapFeature;
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
//        TerrainBehavior[] tbs = myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).getMyTerrain().getTerrainBehaviors() ;
//        if (tbs!=null) {
//            ArrayList<TerrainBehavior> behaviorsList = new ArrayList<TerrainBehavior>(Arrays.asList(tbs));
//            for (TerrainBehavior b : behaviorsList) {
//                if (b.getClass() == SpeedModifier.class) {
//                    effectiveSpeed = this.getUnitSpeedPerSecond() * ((SpeedModifier) (b)).getSpeedMultiplier();
//                    break;
//                }
//                effectiveSpeed = this.getUnitSpeedPerSecond();
//            }
//        }
        //get x, y from myMapFeature and do logic using the map within the activeLevel
//        if
        //dont forget to update state to PRESENT or DIED in myMapFeature
//
//        System.out.println(myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).getMyTerrain());
//        System.out.println(myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).getShortestDistanceHeuristic());
//        System.out.println(myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).getShortestDistanceHeuristicIgnorePath());
//        System.out.println(myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).getShortestDistanceHeuristicAvoidWeapons());
//        System.out.println(myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).getShortestDistanceHeuristicAvoidWeaponsIgnorePath());
//        System.out.println(myActiveLevel.getGridCell(myMapFeature.getGridXPos(), myMapFeature.getGridYPos()).);


        if (startTime == -Integer.MAX_VALUE){
            startTime = ms;
        }

        int numMovements = getUnitSpeedPerSecond();

        for (int i = 0; i < numMovements; i++) {
            MovementDirection movementDirection = determineMovementDirection(AITypes.SHORTEST_PATH);
            int newX = myMapFeature.getGridXPos()+movementDirection.getX();
            int newY = myMapFeature.getGridYPos()+movementDirection.getY();
            prevLocations.addFirst(new Point(newX, newY));
            if (prevLocations.size()>5){
                prevLocations.removeLast();
            }
            myMapFeature.setGridPos(newX, newY,movementDirection.getDirection());
        }
    }



    private MovementDirection determineMovementDirection(AITypes aiTypes){
        if (aiTypes == AITypes.SHORTEST_PATH) {
            return moveShortestDistance(cell -> cell.getShortestDistanceHeuristic());
        }
        if (aiTypes == AITypes.SHORTEST_IGNORE_PATH) {
            return moveShortestDistance(cell -> cell.getShortestDistanceHeuristicIgnorePath());
        }
        if (aiTypes == AITypes.SHORTEST_IGNORE_PATH_AVOID_WEAPON) {
            return moveShortestDistance(cell -> cell.getShortestDistanceHeuristicAvoidWeaponsIgnorePath());
        }
        if (aiTypes == AITypes.SHORTEST_PATH_AVOID_WEAPON) {
            return moveShortestDistance(cell -> cell.getShortestDistanceHeuristicAvoidWeapons());
        }
        return null;
    }

    private MovementDirection moveShortestDistance(Function<Cell, Integer> cellConsumer) {
        int[]xAdditions = new int[]{0,0,-1,1};
        int[]yAdditions = new int[]{1,-1,0,0};
        List<Integer> bestOption = new ArrayList<>();
        bestOption.add(0);
        int bestOptionHeuristic =  Integer.MAX_VALUE;
        for (int k = 0; k < 4; k++) {
            int totalHeuristic;
                    int i = getView().getHeight()/2;
                    int j = getView().getWidth()/2;
                    int x = myMapFeature.getGridXPos()+xAdditions[k]+getView().getWidth()/2;
                    int y = myMapFeature.getGridYPos()+yAdditions[k]+getView().getHeight()/2;
                    Point newxy = new Point(x,y);
                    if (isCellValid(x,y)&& !prevLocations.contains(newxy)){
                        Cell myCell = myActiveLevel.getGridCell(x,y);
                            totalHeuristic = cellConsumer.apply(myCell);
                    }
                    else {
                        totalHeuristic = Integer.MAX_VALUE;
                    }
            if (totalHeuristic<bestOptionHeuristic){
                bestOption.clear();
                bestOption.add(k);
                bestOptionHeuristic = totalHeuristic;
            }
            if (totalHeuristic==bestOptionHeuristic){
//                TODO logic to randomize if equal
                bestOption.add(k);
            }
        }
        Random random = new Random();
        return MovementDirection.values()[bestOption.get(random.nextInt(bestOption.size()))];
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

    @Override
    public ActiveLevel getActiveLevel() {
        return myActiveLevel;
    }
}
