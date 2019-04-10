package Configs.MapPackage.TerrainBehaviors;

import Configs.MapPackage.Terrain;

import java.util.List;

public abstract class TerrainBehavior {
    Terrain myTerrain;
    public static final List<Class> IMPLEMENTING_BEHAVIORS = List.of();

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
