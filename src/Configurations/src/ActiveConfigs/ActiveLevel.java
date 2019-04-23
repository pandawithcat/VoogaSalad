package ActiveConfigs;

import Configs.*;
import Configs.EnemyPackage.EnemyConfig;
import Configs.LevelPackage.Level;
import Configs.MapPackage.Terrain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Configs.MapPackage.Terrain.TERRAIN_SIZE;

public class ActiveLevel extends Level implements Updatable {
    public static final int DISTANCE_HEURISTIC = 1;
    private List<MapFeaturable> activeWeapons;
    private List<MapFeaturable> activeEnemies;
    private List<MapFeaturable> activeProjectiles;
    private Cell[][] myGrid;
    private int myScore;
    private double paneWidth;
    private double paneHeight;
    private final int gridWidth;
    private final int gridHeight;
    private WaveSpawner myWaveSpawner;

    public ActiveLevel(Level level, double paneWidth, double paneHeight){//, MapFeature mapFeature) {
        super(level);
        activeEnemies = new ArrayList<>();
        activeProjectiles = new ArrayList<>();
        activeWeapons = new ArrayList<>();
        myWaveSpawner = new WaveSpawner(getMyWaves());
        myGrid = createMyGrid();
        gridHeight = getMyMapConfig().getGridHeight();
        gridWidth = getMyMapConfig().getGridWidth();
        recalculateMovementHeuristic();
        this.paneHeight = paneHeight;
        this.paneWidth = paneWidth;
    }

    private Cell[][] createMyGrid(){
        Cell[][] tempGrid = new Cell[getMyMapConfig().getGridWidth()][getMyMapConfig().getGridHeight()];//cell[row][col]
        for(Terrain t: getMyMapConfig().getTerrain()) {
            for (int x = 0; x < TERRAIN_SIZE; x++) {
                for (int y = 0; y < TERRAIN_SIZE; y++) {
                    tempGrid[t.getGridXPos() + x][t.getGridYPos() + y] = new Cell(t.getGridXPos() + x, t.getGridYPos() + y, t);
                }
            }
        }
        
        return tempGrid;
    }

    public void addToScore(int points) {
        myScore+=points;
    }

    public int getScore() {
        return myScore;
    }

    public Cell[][] getMyGrid() {
        return myGrid;
    }

    public boolean noMoreEnemiesLeft() {
        return myWaveSpawner.isNoMoreEnemies()&&activeEnemies.isEmpty();

    }



    public Cell getGridCell(int gridX, int gridY){
        return myGrid[gridY][gridX];
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getGridHeight() {
        return gridHeight;
    }

    @Override
    public void update(double ms) {
        updateActive(ms, activeEnemies);
        updateActive(ms, activeProjectiles);
        updateActive(ms, activeWeapons);
        myWaveSpawner.update(ms);
    }

    private void updateActive(double ms, List<MapFeaturable> activeList) {
        List<MapFeaturable> activeToRemove = new ArrayList<>();
        activeList.stream().forEach(active -> {
            ((Updatable)active).update(ms);
            if(active.getMapFeature().isOutOfBounds()) activeToRemove.add(active);
        });
        activeList.removeAll(activeToRemove);
    }



    private ImmutableImageView evaluateViewToBeRemoved(MapFeaturable feature) {
        if(feature instanceof ActiveWeapon) activeWeapons.remove(feature);
        else if(feature instanceof ActiveProjectile) activeProjectiles.remove(feature);
        else if (feature instanceof ActiveEnemy) activeEnemies.remove(feature);
        return feature.getMapFeature().getImageView();

    }

    public List<ImmutableImageView> getViewsToBeRemoved() {
        List<MapFeaturable> viewsToRemove =Stream.of(activeWeapons, activeEnemies, activeProjectiles)
                .flatMap(Collection::stream).collect(Collectors.toList());
        return viewsToRemove.stream()
                .filter(obj -> obj.getMapFeature().getDisplayState()==DisplayState.DIED)
                .map(feature-> evaluateViewToBeRemoved(feature))
                .collect(Collectors.toList());

    }

    public List<ImmutableImageView> getViewsToBeAdded() {
        List<MapFeaturable> viewsToAdd =Stream.of(activeWeapons, activeEnemies, activeProjectiles)
                .flatMap(Collection::stream).collect(Collectors.toList());
//        Stream<MapFeaturable> filteredFeatures = viewsToAdd.stream().filter(obj -> obj.getMapFeature().getDisplayState()==DisplayState.NEW);
//        List<ImmutableImageView> retList = filteredFeatures
//                .map(obj-> obj.getMapFeature().getImageView())
//                .collect(Collectors.toList())
//                ;
//        filteredFeatures.forEach(obj->obj.getMapFeature().setDisplayState(DisplayState.PRESENT));
        List<ImmutableImageView> retList = new ArrayList<>();
        for(MapFeaturable mapFeaturable: viewsToAdd){//TODO MAKE STREAM
            if (mapFeaturable.getMapFeature().getDisplayState()== DisplayState.NEW){
                retList.add(mapFeaturable.getMapFeature().getImageView());
                mapFeaturable.getMapFeature().setDisplayState(DisplayState.PRESENT);
            }
        }
        return retList;
    }


    public ActiveWeapon getActiveWeapon(int id) throws IllegalStateException{
        for(MapFeaturable weapon: activeWeapons) {
            if(((ActiveWeapon)weapon).getWeaponId()==id) return (ActiveWeapon) weapon;
        }
        throw new IllegalStateException();
    }

    public int getMyScore() {
        return myScore;
    }



    public void addToActiveEnemies(EnemyConfig enemy, MapFeature mapFeature) {
        activeEnemies.add(new ActiveEnemy(enemy, mapFeature,this));

    }


    public void addToActiveProjectiles(ActiveProjectile activeProjectile) {
        activeProjectiles.add(activeProjectile);
    }



    public void addToActiveWeapons(ActiveWeapon activeWeapon) {
        activeWeapons.add(activeWeapon);
        recalculateMovementHeuristic();

    }



    public double getPaneHeight() {
        return paneHeight;
    }

    public double getPaneWidth() {
        return paneWidth;
    }

    private void recalculateMovementHeuristic(){
        astar(myGrid[getMyMapConfig().getEnemyExitGridXPos()][getMyMapConfig().getEnemyExitGridYPos()]);
    }

    private void astar(Cell startCell){
        startCell.setMovementHeuristic(0);
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        pq.add(startCell);
        while(!pq.isEmpty()){
            Cell expandedCell = pq.remove();
            int[]xAdditions = new int[]{0,0,-1,1};
            int[]yAdditions = new int[]{1,-1,0,0};
            for (int i = 0; i < 3; i++) {
                int x = expandedCell.getX() + xAdditions[i];
                int y = expandedCell.getY() + yAdditions[i];
                if(isCellValid(x,y)){
                    Cell inspectedCell = myGrid[x][y];
                    if (!inspectedCell.getMyTerrain().getIfPath()){
                        inspectedCell.setMovementHeuristic(Integer.MAX_VALUE);
                        continue;
                    }
                    int newHeuristic = expandedCell.getMovementHeuristic() + DISTANCE_HEURISTIC;
                    if (newHeuristic<inspectedCell.getMovementHeuristic()){
                        inspectedCell.setMovementHeuristic(newHeuristic);
                        pq.add(inspectedCell);
                    }
                }
            }
        }
    }

    private void popCellAndCalculateNeighborsHeuristic(LinkedList<Cell> stack) {
        Cell expandedCell = stack.removeFirst();
        int[]xAdditions = new int[]{0,0,-1,1};
        int[]yAdditions = new int[]{1,-1,0,0};
        for (int i = 0; i < 3; i++) {
            int x = expandedCell.getX() + xAdditions[i];
            int y = expandedCell.getY() + yAdditions[i];
            if(isCellValid(x,y)){
                if (myGrid[x][y].getMyTerrain().getIfPath()){
                    myGrid[x][y].setMovementHeuristic(Integer.MAX_VALUE);
                }
                int newHeuristic = expandedCell.getMovementHeuristic() + DISTANCE_HEURISTIC;
                if (newHeuristic<myGrid[x][y].getMovementHeuristic()){
                    myGrid[x][y].setMovementHeuristic(newHeuristic);
                }
            }
        }
    }

    private boolean isCellValid(int x, int y){
        if (x<0|x>=getMyMapConfig().getGridWidth()){
            return false;
        }
        return !(y < 0 | y >= getMyMapConfig().getGridHeight());
    }



}
