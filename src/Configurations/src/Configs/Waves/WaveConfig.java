package Configs.Waves;

import Configs.*;
import Configs.EnemyPackage.EnemyConfig;
import Configs.LevelPackage.Level;
import Configs.Waves.WaveBehaviors.WaveBehavior;

//holds the enemies
public class WaveConfig implements  Configurable {
    private Level myLevel;
    @Configure
    private String myLabel;
    @Configure
    private long timeToRelease;
    @Configure
    private long rateOfRelease;
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
        timeToRelease = waveConfig.timeToRelease;
        rateOfRelease = waveConfig.rateOfRelease;
        enemies = waveConfig.enemies;
        myWaveBehaviors = waveConfig.getMyWaveBehaviors();
    }

    public WaveBehavior[] getMyWaveBehaviors() {
        return myWaveBehaviors;
    }

    public long getTimeToRelease() {
        return timeToRelease;
    }

    public long getRateOfRelease() {
        return rateOfRelease;
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
