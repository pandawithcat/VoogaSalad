package Configs.ArsenalConfig;

import Configs.*;
import Configs.Behaviors.Behavior;


public class WeaponConfig implements  Configurable, Viewable {
    Configuration myConfiguration;
    @Configure
    private String name;
    @Configure
    private Behavior<WeaponConfig>[] behaviors;
    @Configure
    private View view;
    //because the user needs to configure this part and this is the only way to pass in that information
    @Configure
    private boolean unlocked;

    private Arsenal myArsenal;
    private int weaponId;

    public WeaponConfig(Arsenal myArsenal) {
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

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    public String getName() {
        return name;
    }

    public Behavior<WeaponConfig>[] getBehaviors() {
        return behaviors;
    }

    @Override
    public View getView() {
        return view;
    }

    public TransferImageView getImageView() {
        return (TransferImageView) myConfiguration.getDefinedAttributes().get(view.toString());
    }

    public Arsenal getMyArsenal() {
        return myArsenal;
    }
}
