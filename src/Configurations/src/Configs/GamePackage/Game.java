package Configs.GamePackage;

import Configs.*;
import Configs.ArsenalConfig.WeaponConfig;
import Configs.Behaviors.Behavior;
import Configs.LevelPackage.Level;
import org.w3c.dom.events.Event;

import java.util.List;

public class Game implements Updatable, EventHandlable, Viewable, Configurable {

    @Configure
    List<Level> levelList;
    @Configure
    Behavior<Game>[] gameType;
    @Configure
    WeaponConfig[] allWeaponConfigs;
    @Configure
    Configuration myConfiguration;

    public Game(){
        myConfiguration = new Configuration(this);
    }

    private String myTitle;
    private String myDescription;

    @Override
    public void update(long ms) {

    }

    @Override
    public void handleEvent(Event e) {

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
    public Configuration getConfiguration() {
        return myConfiguration;
    }
}
