package Configs.MapPackage.TerrainBehaviors;

import Configs.ArsenalConfig.Weapon;
import Configs.MapPackage.Terrain;

public abstract class TerrainBehavior {
    Terrain myTerrain;
    public TerrainBehavior(Terrain terrain){
        myTerrain = terrain;
    }

    public Terrain getMyTerrain() {
        return myTerrain;
    }

    public void setMyTerrain(Terrain myTerrain) {
        this.myTerrain = myTerrain;
    }
}
