package Configs.ArsenalConfig;

import Configs.Configurable;
import Configs.LevelPackage.Level;
import Configs.ArsenalConfig.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


//used to hold all of the possible weapons configured in the authoring environemnt
public class Arsenal {
    @Configurable.Configure
    private Weapon[] allWeaponOptions;
    private List<Weapon> unlockedWeapons;
    private Level myLevel;

    public Arsenal(Level level) {
        myLevel = level;
    }


    public List<Weapon> getAllWeaponOptions() {
        return Collections.unmodifiableList(new ArrayList<>(Arrays.asList(allWeaponOptions)));
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
