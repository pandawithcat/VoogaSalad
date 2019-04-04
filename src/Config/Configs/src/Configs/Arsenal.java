package Configs;

import Configs.weaponsConfigPackage.Weapon;

import java.util.Collections;
import java.util.List;


//used to hold all of the possible weapons configured in the authoring environemnt
public class Arsenal {
    List<Weapon> allWeaponOptions;
    List<Weapon> unlockedWeapons;


    public List<Weapon> getAllWeaponOptions() {
        return Collections.unmodifiableList(allWeaponOptions);
    }

    public List<Weapon> getUnlockedWeapons() {
        return Collections.unmodifiableList(unlockedWeapons);
    }

    public void unlockWeapon(String name){
        //make a weapon unlocked
    }

    public Weapon generateNewWeapon(String name){
        return null; //weaponOptions.get(name).copy();
    }
}
