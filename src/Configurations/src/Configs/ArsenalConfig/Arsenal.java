package Configs.ArsenalConfig;

import Configs.Configurable.Configure;
import Configs.Configuration;
import Configs.Info;
import Configs.LevelPackage.Level;

import java.util.*;


//used to hold all of the possible weapons configured in the authoring environemnt
public class Arsenal {
    @Configure
    private WeaponConfig[] allWeaponConfigOptions;

    private Configuration myConfiguration;


//    private WeaponConfig[] unlockedWeapons;

    public Arsenal(Level level) {
        myConfiguration = new Configuration(level);
    }

//    public Map<String, TransferImageView> getAllWeaponConfigOptions() {
//        WeaponConfig[] myWeapons = (WeaponConfig[]) myConfiguration.getDefinedAttributes().get(allWeaponConfigOptions.toString());
//        Map<String, TransferImageView> myMap = new ArrayList<>(Arrays.asList(myWeapons)).stream().collect(Collectors.toMap(weapon->weapon.getName(), weapon->weapon.getImageView()));
//        return Collections.unmodifiableMap(myMap);
//    }

    //note: ID is the index of the weapon+1
    public Map<Integer, Info> getAllWeaponConfigOptions() {
        WeaponConfig[] myWeaponConfigs = getConfiguredWeapons();
        Map<Integer, Info> weaponInfoMap = new HashMap<>();
        for(int i = 0; i< myWeaponConfigs.length; i++) {
            weaponInfoMap.put(i+1, new Info(myWeaponConfigs[i].getName(), myWeaponConfigs[i].getImageView()));
        }
        return Collections.unmodifiableMap(weaponInfoMap);

    }

    public Configuration getConfiguration() {
        return myConfiguration;
    }

//    public List<WeaponConfig> getUnlockedWeapons() {
//        return Collections.unmodifiableList(unlockedWeapons);
//    }

    public void unlockWeapon(String name){
        //make a weapon unlocked
    }

    public WeaponConfig[] getConfiguredWeapons() {
        return (WeaponConfig[]) myConfiguration.getDefinedAttributes().get(allWeaponConfigOptions.toString());
    }

    public WeaponConfig generateNewWeapon(int index){
        WeaponConfig[] myWeaponConfigs = getConfiguredWeapons();
        //TODO: NEED TO MAKE COPY CONSTRUCTOR TO RETURN A COPY OF IT
        return myWeaponConfigs[index+1];
    }
}
