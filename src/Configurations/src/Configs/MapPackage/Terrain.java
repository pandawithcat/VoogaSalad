package Configs.MapPackage;

import Configs.Configurable;
import Configs.Configuration;
import Configs.View;
import Configs.Viewable;

import java.io.File;
import java.util.List;

<<<<<<< HEAD
public class Terrain implements Viewable, Configurable{

    private int gridXPos;
    private int gridYPos;
    private Configuration myConfiguration;

    public Terrain(){
        myConfiguration = new Configuration(this);
=======
public class Terrain implements MapFeaturable{
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
    private boolean isPath;

    public Terrain(int gridXPos, int gridYPos, File imgFile, double height, double width, boolean isPath){
        view = new View(imgFile, height, width);
        myMapFeature = new MapFeature(gridXPos,gridYPos,0,view);
        this.isPath = isPath;

//        myConfiguration = new Configuration(this);
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
    }

    @Override
    public MapFeature getMapFeature() {
        return myMapFeature;
    }

<<<<<<< HEAD
    @Override
    public List<View> getViews() {
        return null;
    }
=======
    //    @Override
//    public Configuration getConfiguration() {
//        return myConfiguration;
//    }

    //implement special case with the View in this class to set the x and y


>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
}
