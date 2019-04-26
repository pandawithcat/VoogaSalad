package Configs.ArsenalConfig;

import Configs.*;
import Configs.ArsenalConfig.WeaponBehaviors.HealthExpirable;
import Configs.ArsenalConfig.WeaponBehaviors.PlaceableOnPath;
import Configs.ArsenalConfig.WeaponBehaviors.Shootable;
import Configs.ArsenalConfig.WeaponBehaviors.WeaponBehavior;
import Configs.ShooterConfig.Shooter;

import java.util.Arrays;
import java.util.stream.Collectors;


public class WeaponConfig implements  Configurable, Viewable {
    Configuration myConfiguration;
    public static final String DISPLAY_LABEL= "Weapon";
    @Configure
    private String myName;
    @Configure
    private WeaponBehavior[] behaviors;
    @Configure
    private View view;
    @Configure
    private boolean unlocked;

    private Arsenal myArsenal;
    private int weaponId;

    public WeaponConfig(Arsenal myArsenal) {
        myConfiguration=new Configuration(this);
        this.myArsenal = myArsenal;
    }

    public WeaponConfig(WeaponConfig weaponConfig) {
        this.myName = weaponConfig.getName();
        this.behaviors = weaponConfig.getBehaviors();
        this.view = weaponConfig.getView();
    }

    protected boolean isPathWeapon() {
        return Arrays.asList(getBehaviors()).stream().anyMatch(behavior -> behavior instanceof PlaceableOnPath);
    }




    protected Shooter getShooter() throws IllegalStateException {
        if (Arrays.asList(getBehaviors()).stream().anyMatch(behavior -> behavior instanceof Shootable)) {
            return ((Shootable) Arrays.asList(getBehaviors()).stream().filter(behavior -> behavior instanceof Shootable).collect(Collectors.toList()).get(0)).getShooter();
        }
        else throw new IllegalStateException();
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
    public String getName() {
        return myName;
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
