package Configs.Waves.WaveBehaviors;

import Configs.Behaviors.Behavior;
import Configs.Waves.Wave;

//ideas for wave behavior - every enemy in a wave is stronger or colored differently, every
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