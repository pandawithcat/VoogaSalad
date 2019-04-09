package ActiveConfigs;

import Configs.*;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.EnemyPackage.Enemy;
import Configs.GamePackage.Game;
import Configs.LevelPackage.Level;

import java.util.List;

public class ActiveLevel extends Level implements Updatable, Viewable {
    private List<WeaponConfig> activeWeaponConfigs;
    private List<Enemy> activeEnemies;
    private List<ActiveProjectile> activeProjectiles;
    private Cell[][] myMapGrid;
    private int myScore;
    private List<ImmutableImageView> viewsToBeRemoved;
    private List<ImmutableImageView> viewsToBeAdded;

    public ActiveLevel(Level level) {
        super(level);
        activeEnemies =
    }

    @Override
    public void update(long ms) {
        for (ActiveProjectile projectile: activeProjectiles){
            projectile.update(ms);
        }
    }

    @Override
    public List<ImmutableImageView> getViewsToBeRemoved() {
        List<ImmutableImageView> sendViews = viewsToBeRemoved.
        return viewsToBeRemoved;
    }

    @Override
    public List<ImmutableImageView> getViewsToBeAdded() {
        return viewsToBeAdded;
    }

    public int getMyScore() {
        return myScore;
    }
}
