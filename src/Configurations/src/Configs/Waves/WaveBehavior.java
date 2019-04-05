package Configs.Wave;

import Configs.Behavior;
import Configs.Waves.Wave;

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
