package Configs.GamePackage.GameBehaviors;

import Configs.Configuration;
import Configs.GamePackage.Game;
import Configs.Updatable;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class TowerAttack extends GameBehavior{
    public static final String DISPLAY_LABEL = "Tower Attack";

    @XStreamOmitField
    private transient Configuration myConfiguration;


    public TowerAttack(Game game) {
        super(game);
        myConfiguration = new Configuration(this);
    }

    @Override
    public void update(double ms, Updatable parent) {

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
