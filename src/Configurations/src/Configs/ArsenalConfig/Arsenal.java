package Configs.ArsenalConfig;

import ActiveConfigs.ActiveWeapon;
import Configs.*;
import Configs.GamePackage.Game;
import Configs.LevelPackage.Level;

import java.util.*;


//used to hold all of the possible weapons configured in the authoring environemnt
public class Arsenal implements Configurable {
    private String myLabel;
    @Configure
    private WeaponConfig[] allWeaponConfigOptions;

    //TODO: UNCOMMENT BELOW
//    @Configure
//    private WeaponUnlocker weponaUnlocker;

    private Configuration myConfiguration;
    private Game myGame;


//    private WeaponConfig[] unlockedWeapons;

    public Arsenal(Game game) {
        myConfiguration = new Configuration(this);
        myGame = game;
    }

    public Game getGame() {
        return myGame;
    }

    @Override
    public String getLabel() {
        return myLabel;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

//        public Map<String, TransferImageView> getAllWeaponConfigOptions() {
//        WeaponConfig[] myWeapons = (WeaponConfig[]) myConfiguration.getDefinedAttributes().get(allWeaponConfigOptions.toString());
//        Map<String, TransferImageView> myMap = new ArrayList<>(Arrays.asList(myWeapons)).stream().collect(Collectors.toMap(weapon->weapon.getLabel(), weapon->weapon.getImageView()));
//        return Collections.unmodifiableMap(myMap);
//    }

    //note: ID is the index of the weapon+1
    public Map<Integer, Info> getAllWeaponConfigOptions() {
        WeaponConfig[] myWeaponConfigs = getConfiguredWeapons();
        Map<Integer, Info> weaponInfoMap = new HashMap<>();
        for(int i = 0; i< myWeaponConfigs.length; i++) {
            weaponInfoMap.put(i+1, new Info(myWeaponConfigs[i].getLabel(), myWeaponConfigs[i].getImage()));
            myWeaponConfigs[i].setWeaponId(i+1);
        }
        return Collections.unmodifiableMap(weaponInfoMap);

    }

    //TODO: EventHandler for adding new weapon to map
    public TransferImageView generateNewWeapon(int ID, double pixelX, double pixelY, int direction){
        WeaponConfig myWeaponConfig = getConfiguredWeapons()[ID-1];
        ActiveWeapon activeWeapon = new ActiveWeapon(myWeaponConfig, new MapFeature(pixelX, pixelY, direction, myWeaponConfig.getView()), myGame.getActiveLevel());
        activeWeapon.getMapFeature().setDisplayState(DisplayState.NEW);
        myGame.getActiveLevel().addToActiveWeapons(activeWeapon);
        return activeWeapon.getMapFeature().getImageView();
    }

    public WeaponConfig[] getConfiguredWeapons() {
        return (WeaponConfig[]) myConfiguration.getDefinedAttributes().get(allWeaponConfigOptions.toString());
    }

    //TODO: ALLOW CHANGE OF DIRECTION
//    public WeaponConfig generateNewWeapon(int ID, double pixelX, double pixelY, double direction){

}
