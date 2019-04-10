package Configs.ArsenalConfig;

import Configs.*;
import Configs.Behaviors.Behavior;


<<<<<<< HEAD:src/Configurations/src/Configs/ArsenalConfig/Weapon.java

public class Weapon implements  Configurable, Viewable, Updatable {
=======
public class WeaponConfig implements  Configurable, Viewable {
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860:src/Configurations/src/Configs/ArsenalConfig/WeaponConfig.java
    Configuration myConfiguration;
    @Configure
    private String name;
    @Configure
    private Behavior<Weapon>[] behaviors;
    @Configure
<<<<<<< HEAD:src/Configurations/src/Configs/ArsenalConfig/Weapon.java
    private View myView;

    public Weapon() {
=======
    private View view;
    //because the user needs to configure this part and this is the only way to pass in that information
    @Configure
    private boolean unlocked;

    private Arsenal myArsenal;
    private int weaponId;

    public WeaponConfig(Arsenal myArsenal) {
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860:src/Configurations/src/Configs/ArsenalConfig/WeaponConfig.java
        myConfiguration=new Configuration(this);
        this.myArsenal = myArsenal;
    }

    public WeaponConfig(WeaponConfig weaponConfig) {
        this.name = weaponConfig.getName();
        this.behaviors = weaponConfig.getBehaviors();
        this.view = weaponConfig.getView();
    }

    public void setWeaponId(int weaponId) {
        this.weaponId = weaponId;
    }

    public int getWeaponId() {
        return weaponId;
    }


<<<<<<< HEAD:src/Configurations/src/Configs/ArsenalConfig/Weapon.java
    @Override
    public List<View> getViews() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
=======
    public String getName() {
        return name;
    }

    public Behavior<WeaponConfig>[] getBehaviors() {
        return behaviors;
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860:src/Configurations/src/Configs/ArsenalConfig/WeaponConfig.java
    }

    @Override
    public View getView() {
        return view;
    }
<<<<<<< HEAD:src/Configurations/src/Configs/ArsenalConfig/Weapon.java
=======

    public TransferImageView getImageView() {
        return (TransferImageView) myConfiguration.getDefinedAttributes().get(view.toString());
    }

    public Arsenal getMyArsenal() {
        return myArsenal;
    }
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860:src/Configurations/src/Configs/ArsenalConfig/WeaponConfig.java
}