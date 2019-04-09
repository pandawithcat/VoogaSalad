package Configs.MapPackage;

import Configs.ArsenalConfig.Weapon;

import java.util.List;

public class MapConfig {
    private Cell[][] myMapGrid;
    //for the game player frontend to easily display terrain
    private List<Terrain> myTerrain;

    public Cell[][] getMapGrid() {
        return myMapGrid;
    }

    public List<Terrain> getTerrain() {
        return myTerrain;
    }

   //This might have to use a weapon ID

    public boolean isValid(int x, int y, Weapon w){
        return true;
    }

    public void setTerrainList(List<Terrain> terrain) {
        myTerrain = terrain;
        for(Terrain t : terrain) {

        }
    }

}
