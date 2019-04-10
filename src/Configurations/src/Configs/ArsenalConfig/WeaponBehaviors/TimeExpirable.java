package Configs.ArsenalConfig.WeaponBehaviors;

import Configs.Configuration;
import Configs.View;
import Configs.ArsenalConfig.Weapon;

import java.util.List;

public class TimeExpirable extends WeaponBehavior{
    @Configure
    protected double timeAlive;

    Configuration myConfiguration;

    public TimeExpirable(Weapon weapon){
        super(weapon);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(long ms) {

    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }
<<<<<<< HEAD

    @Override
    public List<View> getViews() {
        return null;
    }
=======
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
}
