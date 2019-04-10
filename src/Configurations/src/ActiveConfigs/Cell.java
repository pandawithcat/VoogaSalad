package ActiveConfigs;

import Configs.EnemyPackage.EnemyConfig;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.MapPackage.Terrain;

import java.util.List;

public class Cell {
    ActiveWeapon myWeaponConfig;
    Terrain myTerrain;
    List<ActiveEnemy> myEnemies;
    int movementHeuristic;
}

