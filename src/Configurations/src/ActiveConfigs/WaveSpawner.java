package ActiveConfigs;

import Configs.Updatable;
import Configs.Waves.Wave;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaveSpawner implements Updatable {
    private List<Wave> myWaves;
    private boolean noMoreEnemies;

    public WaveSpawner(Wave[] waves) {
        myWaves = new ArrayList<>(Arrays.asList(waves));
        noMoreEnemies = false;
    }


    @Override
    public void update(double ms) {
//        myWaves.stream().forEach(wave -> {
//            if(wave.getTimeToReleaseInMs()<=ms) {
//                wave.update(ms);
//            }
//        });
//        myWaves.stream().forEach(wave -> {
//            if(wave.isFinished()) myWaves.remove(wave);
//        });
        for (Wave wave:myWaves){
            if(wave.getTimeToReleaseInMs()<=ms) {
                wave.update(ms);
            }
        }
        for (Wave wave:myWaves){
            if(wave.isFinished()) myWaves.remove(wave);
        }
        if(myWaves.isEmpty()) noMoreEnemies = true;
    }


    public boolean isNoMoreEnemies() {
        return noMoreEnemies;
    }
}
