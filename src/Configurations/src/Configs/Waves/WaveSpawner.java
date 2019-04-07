package Configs.Waves;

import Configs.*;
import Configs.Behaviors.Behavior;

import java.util.List;

public class WaveSpawner implements Viewable, Updatable {
    List<Wave> myWaves;

    public WaveSpawner(List<Wave> waves) {
        myWaves = waves;
    }

    @Override
    public List<View> getViews() {
        return null;
    }

    @Override
    public void update(long ms) {

    }

}
