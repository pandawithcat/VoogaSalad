package ActiveConfigs;

import Configs.MapPackage.Terrain;

import java.util.ArrayList;
import java.util.List;

public class Cell implements Comparable{
    private int X;
    private int Y;
    private ActiveWeapon myWeaponConfig;
    private Terrain myTerrain;
    private List<ActiveEnemy> myEnemies;
    private int shortestDistanceHeuristic;
    private int shortestDistanceHeuristicIgnorePath;
    private int shortestDistanceHeuristicAvoidWeapons;
    private boolean cellBlocked;
    private int weaponCoverage;


//    public Cell(){
//
//    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Cell){
            return this.getShortestDistanceHeuristic() - ((Cell) o).getShortestDistanceHeuristic();
        }
        return 0;
    }

    public Cell(int xpos, int ypos, Terrain t){
        X = xpos;
        Y = ypos;
        myTerrain = t;
        myEnemies = new ArrayList<>();
        myWeaponConfig = null;
        shortestDistanceHeuristic = Integer.MAX_VALUE;
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

    public void setShortestDistanceHeuristic(int movementHeuristic) {
        this.shortestDistanceHeuristic = movementHeuristic;
    }

    public int getShortestDistanceHeuristic() {
        return shortestDistanceHeuristic;
    }

    public int getShortestDistanceHeuristicAvoidWeapons() {
        return shortestDistanceHeuristicAvoidWeapons;
    }

    public int getShortestDistanceHeuristicIgnorePath() {
        return shortestDistanceHeuristicIgnorePath;
    }

    public void setShortestDistanceHeuristicAvoidWeapons(int shortestDistanceHeuristicAvoidWeapons) {
        this.shortestDistanceHeuristicAvoidWeapons = shortestDistanceHeuristicAvoidWeapons;
    }

    public void setShortestDistanceHeuristicIgnorePath(int shortestDistanceHeuristicIgnorePath) {
        this.shortestDistanceHeuristicIgnorePath = shortestDistanceHeuristicIgnorePath;
    }

    public int getWeaponCoverage() {
        return weaponCoverage;
    }

    public void setWeaponCoverage(int weaponCoverage) {
        this.weaponCoverage = weaponCoverage;
    }
}

