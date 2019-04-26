package Configs.GamePackage.GameBehaviors;

import Configs.Configurable;
import Configs.Configuration;
import Configs.GamePackage.Game;
import Configs.Updatable;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Lives extends GameBehavior{
    public static final String DISPLAY_LABEL = "Lives";
    @Configure
    private int numEnemies;

    @XStreamOmitField
    private transient Configuration myConfiguration;


    public Lives(Game game) {
        super(game);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms, Updatable parent) {
        if (getMyGame().getActiveLevel().getEscapedEnemies()>=numEnemies) {
            getMyGame().setGameOver();
        }
        if (getMyGame().getActiveLevel().noMoreEnemiesLeft()) {
            getMyGame().setGameOver();
            getMyGame().setGameWon(true);
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
