package Configs.ArsenalConfig;

import Configs.Configurable.Configure;
import Configs.Configuration;
import Configs.GamePackage.Game;
import Configs.Info;
import Configs.LevelPackage.Level;
import Configs.TransferImageView;

import java.util.*;


//used to hold all of the possible weapons configured in the authoring environemnt
public class Arsenal {
    @Configure
    private Weapon[] allWeaponOptions;

    private Configuration myConfiguration;


//    private Weapon[] unlockedWeapons;

    public Arsenal(Level level) {
        myConfiguration = new Configuration(level);
    }

//    public Map<String, TransferImageView> getAllWeaponOptions() {
//        Weapon[] myWeapons = (Weapon[]) myConfiguration.getDefinedAttributes().get(allWeaponOptions.toString());
//        Map<String, TransferImageView> myMap = new ArrayList<>(Arrays.asList(myWeapons)).stream().collect(Collectors.toMap(weapon->weapon.getName(), weapon->weapon.getImageView()));
//        return Collections.unmodifiableMap(myMap);
//    }

    //note: ID is the index of the weapon+1
    public Map<Integer, Info> getAllWeaponOptions() {
        Weapon[] myWeapons = getConfiguredWeapons();
        Map<Integer, Info> weaponInfoMap = new HashMap<>();
        for(int i = 0;i<myWeapons.length;i++) {
            weaponInfoMap.put(i+1, new Info(myWeapons[i].getName(),myWeapons[i].getImageView()));
        }
        return Collections.unmodifiableMap(weaponInfoMap);

    }

//    public List<Weapon> getUnlockedWeapons() {
//        return Collections.unmodifiableList(unlockedWeapons);
//    }

    public void unlockWeapon(String name){
        //make a weapon unlocked
    }

    public Weapon[] getConfiguredWeapons() {
        return (Weapon[]) myConfiguration.getDefinedAttributes().get(allWeaponOptions.toString());
    }

    public Weapon generateNewWeapon(int index){
        Weapon[] myWeapons = getConfiguredWeapons();
        //TODO: NEED TO MAKE COPY CONSTRUCTOR TO RETURN A COPY OF IT
        return myWeapons[index+1];
    }
}
