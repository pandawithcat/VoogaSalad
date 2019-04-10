package ActiveConfigs;

import Configs.*;
import Configs.ArsenalConfig.Arsenal;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.EnemyPackage.EnemyConfig;
import Configs.LevelPackage.Level;
import Configs.MapPackage.Terrain;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActiveLevel extends Level implements Updatable {
    private Map<Integer,ActiveWeapon> activeWeapons;
    private List<ActiveEnemy> activeEnemies;
    private List<ActiveProjectile> activeProjectiles;
    private ActiveWave activeWave;
    private Cell[][] myGrid;
    private int myScore;
    private int currentWave=0;

    public ActiveLevel(Level level){//, MapFeature mapFeature) {
        super(level);
        activeEnemies = new ArrayList<>();
        activeProjectiles = new ArrayList<>();
        activeWeapons = new HashMap<>();
        generateCurrentActiveWave();
        activeWave = new ActiveWave(getMyWaveConfigs()[0], this);
//        setMyGame(game);
//        myMapFeature = mapFeature;
        createMyGrid();
    }
    private void createMyGrid() {
        myGrid = new Cell[getMyMapConfig().getGridHeight()][getMyMapConfig().getGridWidth()];
        for (Terrain t : getMyMapConfig().getTerrain()) {
            for (int i = 0; i < t.getHeight(); i++) {
                for (int j = 0; j < t.getWidth(); j++) {
                    myGrid[t.getMapFeature().getGridYPos() + i][t.getMapFeature().getGridXPos() + j] = new Cell(i, j, t);
                }
            }
        }
    }
    public Cell getGridCell(int gridX, int gridY){
        return myGrid[gridY][gridX];
    }

    @Override
    public void update(long ms) {
        updateWeapons(ms);
        updateEnemies(ms);
        updateProjectiles(ms);
        updateActiveWave(ms);
    }

    private void updateEnemies(long ms){
        for(ActiveEnemy enemy : activeEnemies){
            enemy.update(ms);
        }
        if (activeWave.isFinished()) currentWave++;
        //ArrayAttributeManager.updateList(activeWave, ms); ??
    }

    private void updateActiveWave(long ms){
        if (activeWave.isFinished()) {
            currentWave++;
            generateCurrentActiveWave();
        }
        activeWave.update(ms);
    }

    private void generateCurrentActiveWave(){
        activeWave = new ActiveWave(getMyWaveConfigs()[currentWave], this);
    }

    private void updateProjectiles(long ms){
        for (ActiveProjectile projectile: activeProjectiles){
            projectile.update(ms);
        }
    }

    private void updateWeapons(long ms){
        for (int id: activeWeapons.keySet()){
            activeWeapons.get(id).update(ms);
        }
    }


    public boolean isValid(int x, int y, double weaponHeight, double weaponWidth){
        //TODO
        return false;

    }


    public List<ImmutableImageView> getViewsToBeRemoved() {
        List<MapFeaturable> viewsToRemove =Stream.of(activeWeapons.values(), activeEnemies, activeProjectiles)
                .flatMap(Collection::stream).collect(Collectors.toList());
        //TODO: remove it from the list
        return viewsToRemove.stream().filter(obj -> obj.getMapFeature().getDisplayState()==DisplayState.DIED).map(obj-> obj.getMapFeature().getImageView()).collect(Collectors.toList());

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


    //TODO: EventHandler for adding new weapon to map
    public ImmutableImageView generateNewWeapon(int ID, double pixelX, double pixelY){
        WeaponConfig myWeaponConfig = getMyArsenal().getConfiguredWeapons()[ID];
        ActiveWeapon activeWeapon = new ActiveWeapon(myWeaponConfig, new MapFeature(pixelX, pixelY, 0, myWeaponConfig.getView()), this);
        activeWeapon.getMapFeature().setDisplayState(DisplayState.NEW);
        addToActiveWeapons(activeWeapon);
        return activeWeapon.getMapFeature().getImageView();
    }

    //TODO  add EventHandler for isValid


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


    private void recalculateMovementHeuristic(){
        getMyMapConfig();
    }

    public void addToActiveWeapons(ActiveWeapon activeWeapon) {
        activeWeapons.put(activeWeapon.getWeaponId(), activeWeapon);
        recalculateMovementHeuristic();

    }

//    public void removeFromActiveWeapons(ActiveWeapon activeWeapon){
//        activeWeapons.remove(activeWeapon);
//    }
}
