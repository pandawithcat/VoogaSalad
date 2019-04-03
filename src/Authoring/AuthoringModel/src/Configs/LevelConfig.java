package Configs;

import Configs.weaponsConfigPackage.WeaponConfig;

import java.util.List;

public class LevelConfig extends Config {
    private int levelId;
    private MapConfig myMapConfig;
    private List<WeaponConfig> myArsenal;
    private List<WaveConfig> myEnemyWaves;
}
