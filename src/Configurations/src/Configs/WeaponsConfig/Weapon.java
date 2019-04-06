package Configs.WeaponsConfig;

import Configs.Behaviors.Behaves;
import Configs.Behaviors.Behavior;
import Configs.Behaviors.BehaviorManager;
import Configs.Configurable;
import Configs.Configuration;
import Configs.View;
import Configs.Viewable;
import Configs.WeaponsConfig.WeaponBehaviors.WeaponBehavior;

import java.util.ArrayList;
import java.util.List;


public class Weapon implements Behaves<WeaponBehavior>, Configurable, Viewable {
    Configuration myConfiguration;
    @Configure
    private String name;
    @Configure
    private Behavior[] behaviors;
    @Configure
    private View myView;

    public Weapon() {
        myConfiguration=new Configuration(this);
    }


    @Override
    public List<View> getViews() {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public BehaviorManager getBehaviorManager() {
        return null;
    }

    @Override
    public String toFrontendString() {
        return "Weapon";


    }
}