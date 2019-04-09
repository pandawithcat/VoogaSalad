package ActiveConfigs;

import Configs.ArsenalConfig.WeaponConfig;
import Configs.EnemyPackage.Enemy;
import Configs.EventHandlable;
import Configs.GamePackage.Game;
import Configs.ImmutableImageView;
import Configs.LevelPackage.Level;
import Configs.Updatable;
import Configs.Viewable;

import java.util.List;

public class ActiveLevel extends Level implements Updatable, Viewable {
    private List<WeaponConfig> activeWeaponConfigs;
    private List<Enemy> activeEnemies;
    private Cell[][] myMapGrid;

    public ActiveLevel(Level level) {
        super(level);
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public List<ImmutableImageView> getViewsToBeRemoved() {
        return null;
    }

    @Override
    public List<ImmutableImageView> getViewsToBeAdded() {
        return null;
    }
}
