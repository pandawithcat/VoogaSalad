package Configs.MapPackage;

import Configs.MapPackage.Terrain;

import java.util.List;

public class MapConfig {
    //for the game player frontend to easily display terrain
    private List<Terrain> myTerrain;

    public List<Terrain> getTerrain() {
        return myTerrain;
    }

    public void setTerrainList(List<Terrain> terrain) {
        myTerrain = terrain;
        for(Terrain t : terrain) {

        }
    }

}
