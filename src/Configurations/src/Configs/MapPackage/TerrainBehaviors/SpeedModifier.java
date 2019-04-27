package Configs.MapPackage.TerrainBehaviors;

import Configs.Configuration;
import Configs.MapPackage.Terrain;
import Configs.Updatable;

public class SpeedModifier extends TerrainBehavior{
    private transient Configuration myConfiguration;

    private final String DISPLAY_LABEL = "Modify Enemy Speed";
    public SpeedModifier(Terrain terrain){
        super(terrain);
        myConfiguration = new Configuration(this);
    }
    @Override
    public void update(double ms, Updatable parent) {
        //TODO
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }
}
