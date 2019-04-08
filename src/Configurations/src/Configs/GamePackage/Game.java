package Configs.GamePackage;

import Configs.*;
import Configs.LevelPackage.Level;
import org.w3c.dom.events.Event;

import java.util.List;

public class Game implements Configurable, Updatable, EventHandlable, Viewable {

    @Configure
    String gameName;
    @Configure
    List<Level> levelList;
    @Configure
    GameOptions gameType;
    @Configure
    int screenSize;
    @Configure
    int numberOfLevels;

    private Game game;

    @Override
    public void update(long ms) {

    }

    @Override
    public void handleEvent(Event e) {

    }

    @Override
    public List<View> getViews() {
        return null;
    }

    @Override
    public Configuration getConfiguration(){
        Configuration myConfiguration = new Configuration(this);
        return myConfiguration;

    }

}
