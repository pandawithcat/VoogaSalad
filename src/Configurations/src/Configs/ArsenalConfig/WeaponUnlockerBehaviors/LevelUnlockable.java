package Configs.ArsenalConfig.WeaponUnlockerBehaviors;

import Configs.ArsenalConfig.Arsenal;
import Configs.Configuration;
import Configs.Updatable;

public class LevelUnlockable extends ArsenalBehavior implements Updatable {
    public static final String myLabel = "Level-Unlockable";
    private Arsenal myArsenal;

    public LevelUnlockable(Arsenal arsenal) {
        this.myArsenal = arsenal;

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
        return null;
    }
}
