package ActiveConfigs;

import Configs.EnemyPackage.EnemyConfig;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.MapPackage.Terrain;

import java.util.List;

public class Cell {

    private ActiveWeapon myWeaponConfig;
    private Terrain myTerrain;
    private List<ActiveEnemy> myEnemies;
    private int movementHeuristic;
//    private int

    public ActiveWeapon getMyWeaponConfig() {
        return myWeaponConfig;
    }

    public void setMyWeaponConfig(ActiveWeapon myWeaponConfig) {
        this.myWeaponConfig = myWeaponConfig;
    }

    public Terrain getMyTerrain() {
        return myTerrain;
    }

    public void setMyTerrain(Terrain myTerrain) {
        this.myTerrain = myTerrain;
    }

    public List<ActiveEnemy> getMyEnemies() {
        return myEnemies;
    }

    public void setMyEnemies(List<ActiveEnemy> myEnemies) {
        this.myEnemies = myEnemies;
    }

    public void setMovementHeuristic(int movementHeuristic) {
        this.movementHeuristic = movementHeuristic;
    }

    public int getMovementHeuristic() {
        return movementHeuristic;
    }
}

