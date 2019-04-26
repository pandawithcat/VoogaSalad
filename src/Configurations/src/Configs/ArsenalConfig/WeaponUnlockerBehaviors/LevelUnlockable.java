package Configs.ArsenalConfig.WeaponUnlockerBehaviors;

import Configs.ArsenalConfig.Arsenal;
import Configs.Configurable;
import Configs.Configuration;
import Configs.Updatable;

public class LevelUnlockable extends ArsenalBehavior implements Updatable {
    public static final String DISPLAY_LABEL = "Level-Unlockable";
    private Arsenal myArsenal;
    private Configuration myConfiguration;

    public LevelUnlockable(Arsenal arsenal) {
        this.myArsenal = arsenal;
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms, Updatable parent) {

    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }


    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
