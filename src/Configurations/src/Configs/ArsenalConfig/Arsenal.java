package Configs.ArsenalConfig;

<<<<<<< HEAD
import Configs.Configurable;
import Configs.LevelPackage.Level;
import Configs.ArsenalConfig.Weapon;
=======
import ActiveConfigs.ActiveWeapon;
import Configs.Configurable.Configure;
import Configs.Configuration;
import Configs.ImmutableImageView;
import Configs.Info;
import Configs.LevelPackage.Level;
import Configs.MapFeature;
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


//used to hold all of the possible weapons configured in the authoring environemnt
public class Arsenal {
<<<<<<< HEAD
    @Configurable.Configure
    private Weapon[] allWeaponOptions;
    private List<Weapon> unlockedWeapons;
    private Level myLevel;

    public Arsenal(Level level) {
        myLevel = level;
    }

=======
    @Configure
    private WeaponConfig[] allWeaponConfigOptions;

    private Configuration myConfiguration;
    private Level myLevel;


//    private WeaponConfig[] unlockedWeapons;

    public Arsenal(Level level) {
        myConfiguration = new Configuration(level);
        myLevel = level;
    }

    public Level getLevel() {
        return myLevel;
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
            myWeaponConfigs[i].setWeaponId(i+1);
        }
        return Collections.unmodifiableMap(weaponInfoMap);
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860

    public List<Weapon> getAllWeaponOptions() {
        return Collections.unmodifiableList(new ArrayList<>(Arrays.asList(allWeaponOptions)));
    }

<<<<<<< HEAD
    public List<Weapon> getUnlockedWeapons() {
        return Collections.unmodifiableList(unlockedWeapons);
    }
=======
    //TODO: FOR SECOND SPRINT - UNLOCKABLES
//    public List<WeaponConfig> getUnlockedWeapons() {
//        return Collections.unmodifiableList(unlockedWeapons);
//    }
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860

//    public void unlockWeapon(String name){
//        //make a weapon unlocked
//    }

<<<<<<< HEAD
    public Weapon generateNewWeapon(String name){
        return null; //weaponOptions.get(name).copy();
    }
=======
    public WeaponConfig[] getConfiguredWeapons() {
        return (WeaponConfig[]) myConfiguration.getDefinedAttributes().get(allWeaponConfigOptions.toString());
    }

    //TODO: ALLOW CHANGE OF DIRECTION
//    public WeaponConfig generateNewWeapon(int ID, double pixelX, double pixelY, double direction){

>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
}
