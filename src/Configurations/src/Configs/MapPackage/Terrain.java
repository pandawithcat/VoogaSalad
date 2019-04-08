package Configs.MapPackage;

import Configs.Configurable;
import Configs.Configuration;
import Configs.View;
import Configs.Viewable;

import java.util.List;

public class Terrain implements Viewable, Configurable{

    private int gridXPos;
    private int gridYPos;
    private Configuration myConfiguration;

    public Terrain(){
        myConfiguration = new Configuration(this);
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    @Override
    public List<View> getViews() {
        return null;
    }
}
