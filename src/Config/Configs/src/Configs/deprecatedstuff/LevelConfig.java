package Configs.deprecatedstuff;

import Configs.Config;
import Configs.WaveConfig;
import Configs.weaponsConfigPackage.Weapon;

import java.util.List;

public class LevelConfig extends Config {
    private int levelId;
    private MapConfig myMapConfig;
    private List<Weapon> myArsenal;
    private List<WaveConfig> myEnemyWaves;
}
