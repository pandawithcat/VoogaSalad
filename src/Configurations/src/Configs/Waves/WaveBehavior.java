package Configs.Waves;

import Configs.Behavior;
import Configs.Waves.Wave;

//ideas for wave behavior - every enemy in a wave is stronger and colored different, every
public abstract class WaveBehavior implements Behavior<Wave> {
    Wave myWave;
    public WaveBehavior(Wave wave){
        myWave = wave;
    }

    public Wave getMyWave() {
        return myWave;
    }

    public void setMyWave(Wave myWave) {
        this.myWave = myWave;
    }
}
