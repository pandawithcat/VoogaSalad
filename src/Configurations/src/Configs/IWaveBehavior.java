package Configs;
import Configs.Waves.WaveSpawner;

public interface IWaveBehavior extends Behavior{
    void registerBehavior(WaveSpawner waveSpawner);
}
