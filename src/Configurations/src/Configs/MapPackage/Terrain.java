package Configs.MapPackage;

import Configs.*;
import javafx.scene.image.ImageView;

import java.io.File;

public class Terrain implements MapFeaturable, Configurable, Viewable{
    @Configure
    private String myLabel;
    @Configure
    private View view;

    @Configure
    private double gridBlockHeight;
    @Configure
    private double gridBlockWidth;
    @Configure
    private boolean isPath;
//    @Configure
//    private TerrainBehavior[] terrainBehaviors;


    private Configuration myConfiguration;
    private MapFeature myMapFeature;

    public Terrain(MapConfig mapConfig, ImageView imageView, String fileName, int gridYPos, int gridXPos, double height, double width, double gridBlockHeight, double gridBlockWidth, boolean isPath){
        view = new View(fileName,height, width);
        myMapFeature = new MapFeature(gridXPos, gridYPos, 0, view, mapConfig.getGridHeight(), mapConfig.getGridWidth());
        this.gridBlockHeight = gridBlockHeight;
        this.gridBlockWidth = gridBlockWidth;
        this.isPath = isPath;
        myConfiguration = new Configuration(this);
    }

    @Override
    public View getView() {
        return view;
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
        return view.getHeight();
    }

    public double getWidth() {
        return view.getWidth();
    }

    public boolean isPath() {
        return isPath;
    }

//    public TerrainBehavior[] getTerrainBehaviors() {
//        return terrainBehaviors;
//    }

    @Override
    public String getLabel() {
        return myLabel;
    }

    //    @Override
//    public Configuration getConfiguration() {
//        return myConfiguration;

//    }

    //implement special case with the View in this class to set the x and y

}
