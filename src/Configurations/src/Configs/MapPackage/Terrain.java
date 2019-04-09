package Configs.MapPackage;

import Configs.*;

import java.io.File;
import java.util.List;

public class Terrain implements MapFeaturable {
//    @Configure
//    private int gridXPos;
//    @Configure
//    private int gridYPos;
//    @Configure
//    private int pixelXPos;
//    @Configure
//    private int pixelYPos;
//    @Configure
//    private View view;
//    private Configuration myConfiguration;

    private MapFeature myMapFeature;
    private View view;

    public Terrain(int gridXPos, int gridYPos, File imgFile, double height, double width){
        myMapFeature = new MapFeature(gridXPos,gridYPos);
        view = new View(imgFile, height, width);

//        myConfiguration = new Configuration(this);
    }

    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }

    //    @Override
//    public Configuration getConfiguration() {
//        return myConfiguration;
//    }

    //implement special case with the View in this class to set the x and y


}
