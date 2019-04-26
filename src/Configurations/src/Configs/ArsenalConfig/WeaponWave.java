package Configs.ArsenalConfig;

import Configs.ArsenalConfig.WeaponWaveBehaviors.WeaponWaveBehavior;
import Configs.Configurable.Configure;
import Configs.Updatable;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Arrays;

public class WeaponWave implements Updatable {
    public static final String DISPLAY_LABEL = "Weapon Group";
    @Configure
    private WeaponConfig[] weaponOptions;
    @Configure
    private WeaponWaveBehavior weaponWaveBehavior;

    private transient Arsenal myArsenal;
    @XStreamOmitField
    private boolean unlocked;

    public WeaponWave(Arsenal arsenal) {
        myArsenal = arsenal;
        unlocked = false;
    }

    @Override
    public void update(double ms, Updatable parent) {
        //parent is arsenal
        weaponWaveBehavior.update(ms, this);
        if(unlocked) {
            myArsenal.addToUnlockedWeapons(Arrays.asList(weaponOptions));
        }
    }

    public Arsenal getArsenal() {
        return myArsenal;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void unlock() {
        unlocked = true;

    }
}
