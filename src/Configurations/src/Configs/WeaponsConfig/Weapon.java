package Configs.WeaponsConfig;

import Configs.*;
import Configs.Behaviors.Behavior;
import Configs.Behaviors.BehaviorManager;
import Configs.WeaponsConfig.WeaponBehaviors.WeaponBehavior;

import java.util.List;
import java.util.Map;


public class Weapon implements  Configurable, Viewable, Updatable {
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
    public void update(long ms) {
        Map<String, Object> myAttributes = myConfiguration.getDefinedAttributes();
        for (Object o :((Map) myAttributes).values()) {
            if (o instanceof Updatable) {
                ((Updatable) o).update(ms);
            }
        }
    }
}