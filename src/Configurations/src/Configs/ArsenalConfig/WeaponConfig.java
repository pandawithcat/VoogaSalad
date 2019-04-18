package Configs.ArsenalConfig;

import Configs.*;
import Configs.ArsenalConfig.WeaponBehaviors.WeaponBehavior;


public class WeaponConfig implements  Configurable, Viewable {
    Configuration myConfiguration;
    @Configure
    private String myLabel;
    @Configure
    private WeaponBehavior[] behaviors;
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
        this.myLabel = weaponConfig.getLabel();
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


    @Override
    public String getLabel() {
        return myLabel;
    }

    public WeaponBehavior[] getBehaviors() {
        return behaviors;
    }

    @Override
    public View getView() {
        return view;
    }

    public String getImage() {
        return view.getImagePath();
    }

    public Arsenal getMyArsenal() {
        return myArsenal;
    }
}
