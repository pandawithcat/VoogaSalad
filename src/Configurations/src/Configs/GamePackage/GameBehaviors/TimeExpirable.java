package Configs.GamePackage.GameBehaviors;

import ActiveConfigs.ActiveLevel;
import Configs.Configuration;
import Configs.GamePackage.Game;
import Configs.GamePackage.GameStatus;
import Configs.Updatable;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.function.Predicate;

public class TimeExpirable extends GameBehavior{
    public static final String DISPLAY_LABEL = "Beat the Timer";
    @Configure
    private int totalTimeInSec;

    @XStreamOmitField
    private transient Configuration myConfiguration;


    public TimeExpirable(Game game) {
        super(game);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms, Updatable parent) {
        if(ms>=totalTimeInSec*1000) {
            getMyGame().setGameStatus(GameStatus.OVER);
        }
    }


    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public String getName() {
        return DISPLAY_LABEL;
    }
}
