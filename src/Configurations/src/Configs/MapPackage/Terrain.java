package Configs.MapPackage;

import Configs.*;

import java.io.File;
import java.util.List;

public class Terrain implements MapFeaturable, Configurable{
    @Configure
    private int gridXPos;
    @Configure
    private int gridYPos;
    @Configure
    private View view;
    @Configure
    private String image;
    @Configure
    private double height;
    @Configure
    private double width;
    @Configure
    private double gridHeigth;
    @Configure
    private double gridWidth;
    @Configure
    private boolean isPath;



    private Configuration myConfiguration;
    private MapFeature myMapFeature;

    public Terrain(){
        myConfiguration = new Configuration(this);
        myMapFeature = new MapFeature(gridXPos,gridYPos,0,view);

    }

    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    //    @Override
//    public Configuration getConfiguration() {
//        return myConfiguration;
//    }

    //implement special case with the View in this class to set the x and y


}
