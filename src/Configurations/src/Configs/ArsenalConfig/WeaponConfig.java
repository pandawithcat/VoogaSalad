package Configs.ArsenalConfig;

import Configs.*;
import Configs.Behaviors.Behavior;

import java.util.List;
import java.util.Map;


public class WeaponConfig implements  Configurable, Updatable {
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

    public WeaponConfig() {
        myConfiguration=new Configuration(this);
    }

    public WeaponConfig(WeaponConfig weaponConfig) {
        this.name = getName();
        this.behaviors = getBehaviors();
        this.view = getView();
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

    public View getView() {
        return view;
    }

    public TransferImageView getImageView() {
        return (TransferImageView) myConfiguration.getDefinedAttributes().get(view.toString());
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