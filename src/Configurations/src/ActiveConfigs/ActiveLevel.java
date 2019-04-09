package ActiveConfigs;

import Configs.*;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.EnemyPackage.EnemyConfig;
import Configs.GamePackage.Game;
import Configs.LevelPackage.Level;
import Configs.ProjectilePackage.ProjectileConfig;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActiveLevel extends Level implements Updatable, MapFeaturable {
    private Map<Integer,ActiveWeapon> activeWeapons;
    private List<ActiveEnemy> activeEnemies;
    private List<ActiveProjectile> activeProjectiles;
    private Cell[][] myMapGrid;
    private int myScore;
//    private List<ImmutableImageView> viewsToBeRemoved;
//    private List<ImmutableImageView> viewsToBeAdded;
    private MapFeature myMapFeature;
    private int currentWave=0;

    public ActiveLevel(Level level){//, MapFeature mapFeature) {
        super(level);
        activeEnemies = new ArrayList<>();
        activeProjectiles = new ArrayList<>();
        activeWeapons = new HashMap<>();
//        setMyGame(game);
//        myMapFeature = mapFeature;
    }

    @Override
    public void update(long ms) {
        for (ActiveProjectile projectile: activeProjectiles){
            projectile.update(ms);
        }
        if (getMyWaveConfigs()[currentWave].getIsFinished()) currentWave++;
        ArrayAttributeManager.updateList(getMyWaveConfigs(), ms);
    }


    public boolean isValid(int x, int y, double weaponHeight, double weaponWidth){
        //TODO
        return false;

    }

    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }

    public List<ImmutableImageView> getViewsToBeRemoved() {
        List<MapFeaturable> viewsToRemove =Stream.of(activeWeapons.values(), activeEnemies, activeProjectiles)
                .flatMap(Collection::stream).collect(Collectors.toList());
        return viewsToRemove.stream().filter(weapon -> weapon.getMapFeature().getDisplayState()==2).map(weapon-> weapon.getMapFeature().getImageView()).collect(Collectors.toList());

    }


    public ActiveWeapon getActiveWeapon(int id) {
        return activeWeapons.get(id);
    }

    public List<ImmutableImageView> getViewsToBeAdded() {
        List<MapFeaturable> viewsToAdd =Stream.of(activeWeapons.values(), activeEnemies, activeProjectiles)
                .flatMap(Collection::stream).collect(Collectors.toList());
        return viewsToAdd.stream().filter(weapon -> weapon.getMapFeature().getDisplayState()==0).map(weapon-> weapon.getMapFeature().getImageView()).collect(Collectors.toList());
    }

    public int getMyScore() {
        return myScore;
    }



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
