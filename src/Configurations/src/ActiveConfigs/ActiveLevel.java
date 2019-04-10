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
//        setMyGame(game);
//        myMapFeature = mapFeature;
        activeWave = new ActiveWave(getMyWaveConfigs()[0], this);
        myGrid = createMyGrid();
    }
    private Cell[][] createMyGrid(){
        Cell[][] tempGrid = new Cell[getMyMap().getGridHeight()][getMyMap().getGridWidth()];
        for(Terrain t : getMyMap().getTerrain()){
            tempGrid[t.getMapFeature().getGridYPos()][t.getMapFeature().getGridXPos()].setMyTerrain(t);
        }
        return null;
    }
    public Cell getGridCell(int gridX, int gridY){
        return myGrid[gridY][gridX];
    }

    public Arsenal getMyArsenal() {
        return myArsenal;
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
    //TODO  add EventHandler for isValid

    public void addToActiveEnemies(EnemyConfig enemy, MapFeature mapFeature) {
        activeEnemies.add(new ActiveEnemy(enemy, mapFeature));
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


    public void addToActiveWeapons(WeaponConfig weapon, MapFeature mapFeature) {
        activeWeapons.put(weapon.getWeaponId(), new ActiveWeapon(weapon,mapFeature));
    }

    public void addToActiveWeapons(ActiveWeapon activeWeapon) {
        activeWeapons.put(activeWeapon.getWeaponId(), activeWeapon);
    }

//    public void removeFromActiveWeapons(ActiveWeapon activeWeapon){
//        activeWeapons.remove(activeWeapon);
//    }
}
