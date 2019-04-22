package Configs.ArsenalConfig.WeaponUnlockerBehaviors;

import Configs.ArsenalConfig.Arsenal;
import Configs.Configurable;
import Configs.Configuration;
import Configs.Updatable;

public class LevelUnlockable extends ArsenalBehavior implements Updatable {
    public static final String myLabel = "Level-Unlockable";
    private Arsenal myArsenal;
    private Configuration myConfiguration;

    public LevelUnlockable(Arsenal arsenal) {
        this.myArsenal = arsenal;
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms) {

    }

    @Override
    public String getName() {
        return myLabel;
    }


    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
