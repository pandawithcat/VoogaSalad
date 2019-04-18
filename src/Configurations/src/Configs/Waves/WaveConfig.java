package Configs.Waves;

import Configs.*;
import Configs.EnemyPackage.EnemyConfig;
import Configs.LevelPackage.Level;
import Configs.Waves.WaveBehaviors.WaveBehavior;

//holds the enemies
public class WaveConfig implements  Configurable {
    private Level myLevel;
    private String myLabel;
    @Configure
    private String name;
    @Configure
    private double timeToReleaseInMs;
    @Configure
    private double rateOfReleaseEnemiesPerMs;
    @Configure
    private EnemyConfig[] enemies;
    @Configure
    private WaveBehavior[] myWaveBehaviors;

    private Configuration myConfiguration;



    public WaveConfig(Level level) {
        myLevel = level;
        myConfiguration = new Configuration(this);

    }

    public WaveConfig(WaveConfig waveConfig) {
        myLevel = waveConfig.myLevel;
        timeToReleaseInMs = waveConfig.timeToReleaseInMs;
        rateOfReleaseEnemiesPerMs = waveConfig.rateOfReleaseEnemiesPerMs;
        enemies = waveConfig.enemies;
        myWaveBehaviors = waveConfig.getMyWaveBehaviors();
    }

    public WaveBehavior[] getMyWaveBehaviors() {
        return myWaveBehaviors;
    }

    public double getTimeToRelease() {
        return timeToReleaseInMs;
    }

    public double getRateOfRelease() {
        return rateOfReleaseEnemiesPerMs;
    }

    public EnemyConfig[] getEnemies() {
        return enemies;
    }

    public Level getMyLevel() {
        return myLevel;
    }

    @Override
    public String getLabel() {
        return myLabel;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
