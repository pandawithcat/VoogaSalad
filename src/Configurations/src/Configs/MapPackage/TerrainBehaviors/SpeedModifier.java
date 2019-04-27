package Configs.MapPackage.TerrainBehaviors;

import ActiveConfigs.ActiveEnemy;
import ActiveConfigs.ActiveLevel;
import ActiveConfigs.Cell;
import Configs.Configuration;
import Configs.MapPackage.Terrain;
import Configs.Updatable;

import java.util.List;

public class SpeedModifier extends TerrainBehavior{
    private transient Configuration myConfiguration;
    private ActiveLevel myActiveLevel;
    private final String DISPLAY_LABEL = "Modify Enemy Speed";



    @Configure
    private double speedMultiplier;

    public SpeedModifier(Terrain terrain){
        super(terrain);
        myConfiguration = new Configuration(this);
    }
    @Override
    public void update(double ms, Updatable parent) {
        for (ActiveEnemy ae : getEnemiesOnMyCell()){

        }
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }
}
