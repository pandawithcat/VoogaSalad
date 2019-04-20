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
    private Map<Integer,ActiveWeapon> activeWeapons;
    private List<ActiveEnemy> activeEnemies;
    private List<ActiveProjectile> activeProjectiles;
    private Cell[][] myGrid;
    private int myScore;
    private final int gridWidth;
    private final int gridHeight;
    private WaveSpawner myWaveSpawner;

    public ActiveLevel(Level level){//, MapFeature mapFeature) {
        super(level);
        activeEnemies = new ArrayList<>();
        activeProjectiles = new ArrayList<>();
        activeWeapons = new HashMap<>();
        //TODO: fix active wave to be a wave spawner
        myWaveSpawner = new WaveSpawner(getMyWaves());
        myGrid = createMyGrid();
        gridHeight = getMyMapConfig().getGridHeight();
        gridWidth = getMyMapConfig().getGridWidth();
        recalculateMovementHeuristic();
        System.out.println("meep");
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

    public Cell[][] getMyGrid() {
        return myGrid;
    }

    public boolean noMoreEnemiesLeft() {
        //TODO: check logic on seeing if theres no more waves

        return activeEnemies.size()==0;
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
        //FIXME: ALL OF THESE METHODS SHOULD USE STREAM INSTEAD OF FOR LOOPS
        updateWeapons(ms);
        updateEnemies(ms);
        updateProjectiles(ms);
        myWaveSpawner.update(ms);
    }

    private void updateEnemies(double ms){
        activeEnemies.stream().forEach(enemy -> enemy.update(ms));

//        for(ActiveEnemy enemy : activeEnemies){
////            activeEnemies.add(enemy);
////            enemy.getMapFeature().setGridPos(50,50,0);
//            enemy.update(ms);
//        }
    }

    private void updateProjectiles(double ms){
        activeProjectiles.stream().forEach(projectile -> projectile.update(ms));
    }

    private void updateWeapons(double ms){
        activeWeapons.keySet().stream().forEach(id -> activeWeapons.get(id).update(ms));
    }



    private ImmutableImageView evaluateViewToBeRemoved(MapFeaturable feature) {
        if(feature instanceof ActiveWeapon) activeWeapons.remove(feature);
        else if(feature instanceof ActiveProjectile) activeProjectiles.remove(feature);
        else if (feature instanceof ActiveEnemy) activeEnemies.remove(feature);
        return feature.getMapFeature().getImageView();

    }

    public List<ImmutableImageView> getViewsToBeRemoved() {
        List<MapFeaturable> viewsToRemove =Stream.of(activeWeapons.values(), activeEnemies, activeProjectiles)
                .flatMap(Collection::stream).collect(Collectors.toList());
        return viewsToRemove.stream()
                .filter(obj -> obj.getMapFeature().getDisplayState()==DisplayState.DIED)
                .map(feature-> evaluateViewToBeRemoved(feature))
                .collect(Collectors.toList());

    }

    public List<ImmutableImageView> getViewsToBeAdded() {
        List<MapFeaturable> viewsToAdd =Stream.of(activeWeapons.values(), activeEnemies, activeProjectiles)
                .flatMap(Collection::stream).collect(Collectors.toList());
        return viewsToAdd.stream().filter(obj -> obj.getMapFeature().getDisplayState()==DisplayState.NEW).map(obj-> obj.getMapFeature().getImageView()).collect(Collectors.toList());
    }


    public ActiveWeapon getActiveWeapon(int id) {
        return activeWeapons.get(id);
    }

    public int getMyScore() {
        return myScore;
    }



    public void addToActiveEnemies(EnemyConfig enemy, MapFeature mapFeature) {
        activeEnemies.add(new ActiveEnemy(enemy, mapFeature,this));
    }
//
//    public void removeFromActiveEnemies(ActiveEnemy activeEnemy){
//        activeEnemies.remove(activeEnemy);
//    }

    public void addToActiveProjectiles(ActiveProjectile activeProjectile) {
        activeProjectiles.add(activeProjectile);
    }

//    public void removeFromActiveProjectiles(ActiveProjectile activeProjectile){
//        activeProjectiles.remove(activeProjectile);
//
//    }

    public void addToActiveWeapons(ActiveWeapon activeWeapon) {
        activeWeapons.put(activeWeapon.getWeaponId(), activeWeapon);
        recalculateMovementHeuristic();

    }

//    public void removeFromActiveWeapons(ActiveWeapon activeWeapon){
//        activeWeapons.remove(activeWeapon);
//    }


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
