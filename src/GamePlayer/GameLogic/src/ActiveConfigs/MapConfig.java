package Configs.MapPackage;

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

    public void setTerrainList(List<Terrain> terrain) {
        myTerrain = terrain;
        for(Terrain t : terrain) {

        }
    }

}
