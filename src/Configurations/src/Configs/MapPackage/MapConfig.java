package Configs.MapPackage;

import Configs.MapPackage.Terrain;

import java.util.List;

public class MapConfig {
    //for the game player frontend to easily display terrain
    private List<Terrain> myTerrain;

    public List<Terrain> getTerrain() {
        return myTerrain;
    }

   //This might have to use a weapon ID
//    public boolean isValid(int x, int y, Weapon w){
//
//    }

    public void setTerrainList(List<Terrain> terrain) {
        myTerrain = terrain;
        for(Terrain t : terrain) {

        }
    }

}
