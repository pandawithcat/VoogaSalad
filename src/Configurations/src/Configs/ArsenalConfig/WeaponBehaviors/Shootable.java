package Configs.ArsenalConfig.WeaponBehaviors;


import ActiveConfigs.ActiveWeapon;
import Configs.ShooterConfig.Shooter;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Configuration;
import Configs.Updatable;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Shootable extends WeaponBehavior{

    public static final String DISPLAY_LABEL = "Shootable";
    @Configure
    private Shooter myShooter;

    @XStreamOmitField
    private transient Configuration myConfiguration;
    private WeaponConfig weaponConfig;
    @XStreamOmitField
    private ActiveWeapon activeWeapon;

    public Shootable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
        this.weaponConfig = weaponConfig;
    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }

    @Override
    public void update(double ms, Updatable parent) {
        if(activeWeapon==null) activeWeapon = (ActiveWeapon) parent;

        myShooter.update(ms, this);
    }

    public Shooter getShooter() {
        return myShooter;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    public WeaponConfig getWeaponConfig() {
        return weaponConfig;
    }

    public ActiveWeapon getActiveWeapon() {
        return activeWeapon;
    }
}
