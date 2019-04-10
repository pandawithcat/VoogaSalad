package Configs.ArsenalConfig.WeaponBehaviors;

<<<<<<< HEAD
import Configs.Configuration;
import Configs.View;
import Configs.ArsenalConfig.Weapon;

import java.util.List;
=======
import ActiveConfigs.Shooter;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Configuration;
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860

public class Shootable extends WeaponBehavior{
    @Configure
    protected Shooter myShooter;

    Configuration myConfiguration;
    private WeaponConfig weaponConfig;

    Shootable(Weapon weapon){
        super(weapon);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(long ms) {
        myShooter.update(ms);
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

<<<<<<< HEAD
    @Override
    public List<View> getViews() {
        return null;
=======
    public WeaponConfig getWeaponConfig() {
        return weaponConfig;
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
    }
}
