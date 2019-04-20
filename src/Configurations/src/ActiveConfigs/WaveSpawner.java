package ActiveConfigs;

import Configs.Updatable;
import Configs.Waves.Wave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaveSpawner implements Updatable {
    private List<Wave> currentWaves;
    private List<Wave> myWaves;

    public WaveSpawner(Wave[] waves) {
        myWaves = new ArrayList<>(Arrays.asList(waves));
        currentWaves = new ArrayList<>();
    }


    @Override
    public void update(double ms) {
        currentWaves.stream().forEach(wave -> {
            if(wave.isFinished()) currentWaves.remove(wave);
            else wave.update(ms);
        });
        myWaves.stream().forEach(wave -> {
            if(wave.getTimeToReleaseInMs()>=ms) {
                currentWaves.add(wave);
                myWaves.remove(wave);
            }
        });
    }
}
