package Configs.Waves.WaveBehaviors;

import Configs.Behaviors.Behavior;
import Configs.Waves.WaveConfig;

//ideas for wave behavior - every enemy in a wave is stronger or colored differently, every
public abstract class WaveBehavior implements Behavior<WaveConfig> {
    WaveConfig myWaveConfig;
    public WaveBehavior(WaveConfig waveConfig){
        myWaveConfig = waveConfig;
    }

    public WaveConfig getMyWaveConfig() {
        return myWaveConfig;
    }

    public void setMyWaveConfig(WaveConfig myWaveConfig) {
        this.myWaveConfig = myWaveConfig;
    }
}
