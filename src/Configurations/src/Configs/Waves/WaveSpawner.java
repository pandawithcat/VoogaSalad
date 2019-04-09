package Configs.Waves;

import Configs.*;

import java.util.List;

public class WaveSpawner implements Viewable, Updatable {
    List<WaveConfig> myWaveConfigs;

    public WaveSpawner(List<WaveConfig> waveConfigs) {
        myWaveConfigs = waveConfigs;
    }

    @Override
    public List<ImmutableImageView> getViewsToBeAdded() {
        return null;
    }

    @Override
    public List<ImmutableImageView> getViewsToBeRemoved() {
        return null;
    }

    @Override
    public void update(long ms) {

    }

}
