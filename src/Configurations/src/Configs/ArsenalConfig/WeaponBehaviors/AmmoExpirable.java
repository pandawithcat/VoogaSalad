package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.View;
import Configs.ArsenalConfig.Weapon;

public class AmmoExpirable extends WeaponBehavior {
    @Configure
    protected int numberOfEnemiesPossibleToKill;

    private Configuration myConfiguration;

<<<<<<< HEAD
    public AmmoExpirable(Weapon weapon){
        super(weapon);
=======
    public AmmoExpirable(WeaponConfig weaponConfig){
        super(weaponConfig);
        myConfiguration = new Configuration(this);
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
    }

    @Override
    public void update(long ms) {


<<<<<<< HEAD
    @Override
    public List<View> getViews() {
        return null;
=======
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}