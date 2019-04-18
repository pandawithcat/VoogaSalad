package ActiveConfigs;

import Configs.EnemyPackage.EnemyConfig;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.MapPackage.Terrain;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int X;
    private int Y;
    private ActiveWeapon myWeaponConfig;
    private Terrain myTerrain;
    private List<ActiveEnemy> myEnemies;
    private int movementHeuristic;

    public Cell(){

    }

    public Cell(int xpos, int ypos, Terrain t){
        X = xpos;
        Y = ypos;
        myTerrain = t;
        myEnemies = new ArrayList<>();
        myWeaponConfig = null;

    }

    public boolean isValidWeaponPlacement() {
        return myWeaponConfig==null&&(myWeaponConfig.isPathWeapon() == myTerrain.isPath());
    }



    public ActiveWeapon getMyWeaponConfig() {
        return myWeaponConfig;
    }

    public int getX(){
        return X;
    }
    public int getY(){
        return Y;
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

