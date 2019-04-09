package ActiveConfigs;

import Configs.*;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.EnemyPackage.EnemyConfig;
import Configs.LevelPackage.Level;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActiveLevel extends Level implements Updatable {
    private Map<Integer,ActiveWeapon> activeWeapons;
    private List<ActiveEnemy> activeEnemies;
    private ActiveWave activeWave;
    private List<ActiveProjectile> activeProjectiles;
    private Cell[][] myGrid;
    private int myScore;
    private MapFeature myMapFeature;
    private int currentWave=0;

    public ActiveLevel(Level level){//, MapFeature mapFeature) {
        super(level);
        activeEnemies = new ArrayList<>();
        activeProjectiles = new ArrayList<>();
        activeWeapons = new HashMap<>();
        generateCurrentActiveWave();
//        setMyGame(game);
//        myMapFeature = mapFeature;

        //TODO: create myGrid
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
    }

    private void updateActiveWave(long ms){
        if (activeWave.getIsFinished()) {
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
        return viewsToRemove.stream().filter(weapon -> weapon.getMapFeature().getDisplayState()==DisplayState.DIED).map(weapon-> weapon.getMapFeature().getImageView()).collect(Collectors.toList());

    }


    public ActiveWeapon getActiveWeapon(int id) {
        return activeWeapons.get(id);
    }

    public List<ImmutableImageView> getViewsToBeAdded() {
        List<MapFeaturable> viewsToAdd =Stream.of(activeWeapons.values(), activeEnemies, activeProjectiles)
                .flatMap(Collection::stream).collect(Collectors.toList());
        return viewsToAdd.stream().filter(weapon -> weapon.getMapFeature().getDisplayState()==DisplayState.NEW).map(weapon-> weapon.getMapFeature().getImageView()).collect(Collectors.toList());
    }

    public int getMyScore() {
        return myScore;
    }


    //TODO: EventHandler for adding new weapon to map
    //TODO  add EventHandler for isValid

    public void addToActiveEnemies(EnemyConfig enemy, MapFeature mapFeature) {
        activeEnemies.add(new ActiveEnemy(enemy, mapFeature));
    }

    public void addToActiveProjectiles(ActiveProjectile activeProjectile) {
        activeProjectiles.add(activeProjectile);
    }

    public void addToActiveWeapon(WeaponConfig weapon, MapFeature mapFeature) {
        activeWeapons.put(weapon.getWeaponId(), new ActiveWeapon(weapon,mapFeature));
    }
}
