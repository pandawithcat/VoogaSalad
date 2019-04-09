package ActiveConfigs;

import Configs.ArsenalConfig.WeaponConfig;
import Configs.EnemyPackage.Enemy;
import Configs.GamePackage.Game;
import Configs.LevelPackage.Level;

import java.util.List;

public class ActiveLevel extends Level{
    private List<WeaponConfig> activeWeaponConfigs;
    private List<Enemy> activeEnemies;

    public ActiveLevel(Level level) {
        super(level);
    }
}
