package Configs.GamePackage;

import Configs.EventHandlable;
import Configs.LevelPackage.Level;
import Configs.Updatable;
import Configs.View;
import Configs.Viewable;
import org.w3c.dom.events.Event;

import java.util.List;

public class Game implements Updatable, EventHandlable, Viewable {

    List<Level> levelList;
    GameOptions gameType;

    private String myTitle;
    private String myDescription;

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


    public void getTitle()


}
