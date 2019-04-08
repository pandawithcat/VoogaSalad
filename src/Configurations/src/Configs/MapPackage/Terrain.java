package Configs.MapPackage;

import Configs.*;

import java.util.List;

public class Terrain implements Viewable, Configurable {
    @Configure
    private int gridXPos;
    @Configure
    private int gridYPos;
    private Configuration myConfiguration;

    public Terrain(){
        myConfiguration = new Configuration(this);
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    //implement special case with the View in this class to set the x and y


}
