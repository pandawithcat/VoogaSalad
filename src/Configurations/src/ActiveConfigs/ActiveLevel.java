package ActiveConfigs;

import Configs.*;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.EnemyPackage.Enemy;
import Configs.GamePackage.Game;
import Configs.LevelPackage.Level;

import java.util.ArrayList;
import java.util.List;

public class ActiveLevel extends Level implements Updatable, Viewable {
    private List<WeaponConfig> activeWeaponConfigs;
    private List<Enemy> activeEnemies;
    private List<ActiveProjectile> activeProjectiles;
    private Cell[][] myMapGrid;
    private int myScore;
    private List<ImmutableImageView> viewsToBeRemoved;
    private List<ImmutableImageView> viewsToBeAdded;

    public ActiveLevel(Level level, Game game) {
        super(level);
        activeEnemies = new ArrayList<>();
        activeProjectiles = new ArrayList<>();
        activeWeaponConfigs = new ArrayList<>();
        setMyGame(game);
    }

    @Override
    public void update(long ms) {
        for (ActiveProjectile projectile: activeProjectiles){
            projectile.update(ms);
        }
    }

    public List<ImmutableImageView> getViewsToBeRemoved() {
        List<ImmutableImageView> sendViews = new ArrayList<>();
        sendViews.addAll(viewsToBeRemoved);
        return viewsToBeRemoved;
    }

    public List<ImmutableImageView> getViewsToBeAdded() {
        return viewsToBeAdded;
    }

    public int getMyScore() {
        return myScore;
    }
}
