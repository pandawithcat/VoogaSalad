package Configs;

import Configs.weaponsConfigPackage.WeaponConfig;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;


//used to hold all of the possible weapons configured in the authoring environemnt
public class Arsenal {
    List<WeaponConfig> allWeaponOptions;
    List<WeaponConfig> unlockedWeapons;


    public List<WeaponConfig> getAllWeaponOptions() {
        return Collections.unmodifiableList(allWeaponOptions);
    }

    public List<WeaponConfig> getUnlockedWeapons() {
        return Collections.unmodifiableList(unlockedWeapons);
    }

    public void unlockWeapon(String name){
        //make a weapon unlocked
    }

    public WeaponConfig generateNewWeapon(String name){
        return null; //weaponOptions.get(name).copy();
    }
}
