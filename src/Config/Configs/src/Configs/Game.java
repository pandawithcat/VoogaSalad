package Configs;

import Configs.LevelPackage.Level;
import org.w3c.dom.events.Event;

import java.util.List;

public class Game implements Updatable, EventHandlable, Viewable{

    List<Level> levelList;
    GameOptions gameType;

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


}
