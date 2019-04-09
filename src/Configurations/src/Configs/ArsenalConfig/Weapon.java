package Configs.ArsenalConfig;

import Configs.*;
import Configs.Behaviors.Behavior;

import java.util.List;
import java.util.Map;


public class Weapon implements  Configurable, Viewable, Updatable {
    Configuration myConfiguration;
    @Configure
    private String name;
    @Configure
    private Behavior<Weapon>[] behaviors;
    @Configure
    private View view;

    //because the user needs to configure this part and this is the only way to pass in that information
    @Configure
    private boolean unlocked;

    public Weapon() {
        myConfiguration=new Configuration(this);
    }




    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    public String getName() {
        return (String) myConfiguration.getDefinedAttributes().get(name.toString());
    }

    public TransferImageView getImageView() {
        return (TransferImageView) myConfiguration.getDefinedAttributes().get(view.toString());
    }

    @Override
    public List<ImmutableImageView> getViewsToBeAdded() {
        return null;
    }

    @Override
    public List<ImmutableImageView> getViewsToBeRemoved() {
        return null;
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