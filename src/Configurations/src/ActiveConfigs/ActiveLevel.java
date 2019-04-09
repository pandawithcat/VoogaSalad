package ActiveConfigs;

import Configs.*;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.EnemyPackage.EnemyConfig;
import Configs.GamePackage.Game;
import Configs.LevelPackage.Level;
import Configs.ProjectilePackage.ProjectileConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActiveLevel extends Level implements Updatable, MapFeaturable {
    private Map<Integer,ActiveWeapon> activeWeapons;
    private List<ActiveEnemy> activeEnemies;
    private List<ActiveProjectile> activeProjectiles;
    private Cell[][] myMapGrid;
    private int myScore;
    private List<ImmutableImageView> viewsToBeRemoved;
    private List<ImmutableImageView> viewsToBeAdded;
    private MapFeature myMapFeature;

    public ActiveLevel(Level level, MapFeature mapFeature) {
        super(level);
        activeEnemies = new ArrayList<>();
        activeProjectiles = new ArrayList<>();
        activeWeapons = new HashMap<>();
//        setMyGame(game);
        myMapFeature = mapFeature;
    }

    @Override
    public void update(long ms) {
        for (ActiveProjectile projectile: activeProjectiles){
            projectile.update(ms);
        }
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
        List<ImmutableImageView> sendViews = new ArrayList<>();
        sendViews.addAll(viewsToBeRemoved);
        return viewsToBeRemoved;
    }

    public ActiveWeapon getActiveWeapon(int id) {
        return activeWeapons.get(id);
    }

    public List<ImmutableImageView> getViewsToBeAdded() {
        return viewsToBeAdded;
    }

    public int getMyScore() {
        return myScore;
    }

    public void addViewToBeRemoved(ImmutableImageView imageView) {
        viewsToBeRemoved.add(imageView);
    }

    public void addViewToBeAdded(ImmutableImageView imageView) {
        viewsToBeAdded.add(imageView);
    }

    public void addToActiveEnemies(EnemyConfig enemy, MapFeature mapFeature) {
        activeEnemies.add(new ActiveEnemy(enemy, mapFeature));
    }

    public void addToActiveProjectiles(ProjectileConfig projectile, MapFeature mapFeature) {
        activeProjectiles.add(new ActiveProjectile(projectile, mapFeature));
    }

    public void addToActiveWeapon(WeaponConfig weapon, MapFeature mapFeature) {
        activeWeapons.put(weapon.getWeaponId(), new ActiveWeapon(weapon,mapFeature));
    }
}
