package Configs;


import Configs.Wave.WaveSpawner;

public interface IWaveBehavior extends Behavior{
    void registerBehavior(WaveSpawner waveSpawner);
}
