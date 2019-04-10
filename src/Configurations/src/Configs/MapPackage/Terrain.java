package Configs.MapPackage;

import Configs.*;
import javafx.scene.image.ImageView;

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
    private double gridBlockHeight;
    @Configure
    private double gridBlockWidth;
    @Configure
    private boolean isPath;


    private Configuration myConfiguration;
    private MapFeature myMapFeature;

    public Terrain(MapConfig mapConfig, ImageView imageView, String fileName, int gridYPos, int gridXPos, double height, double width, double gridBlockHeight, double gridBlockWidth, boolean isPath){

        myConfiguration = new Configuration(this);
        myMapFeature = new MapFeature(gridXPos,gridYPos,0,view, mapConfig.getGridHeight(), mapConfig.getGridWidth());
        //TODO FINISH

    }

    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
    }

    public boolean getIfPath() {
        return isPath;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public boolean isPath() {
        return isPath;
    }


    //    @Override
//    public Configuration getConfiguration() {
//        return myConfiguration;

//    }

    //implement special case with the View in this class to set the x and y

}
