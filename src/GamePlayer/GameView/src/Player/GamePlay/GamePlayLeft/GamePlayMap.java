package Player.GamePlay.GamePlayLeft;

import BackendExternal.Logic;
import Configs.ImmutableImageView;
import Configs.MapPackage.MapConfig;
import Configs.TransferImageView;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GamePlayMap extends Pane{
    private Logic myLogic;
    private List<ImmutableImageView> terrainList;
    private Group mapRoot;
    public static final String WEAPON_IMAGE = "wood.jpg";
    public static final String GRASS_IMAGE = "grassplay.jpg";
    public static final String RESOURCES_PATH = "resources/";
    //TEST TERRAIN
    private List<ImmutableImageView> testTerrain = new ArrayList<ImmutableImageView>();

    public GamePlayMap(double width, double height, Logic logic) {
        myLogic = logic;
        mapRoot=new Group();
        applyCss();
        layout();

        terrainList = myLogic.getLevelTerrain(width, height);
        setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        terrainList.stream().forEach(img -> this.getChildren().add(img.getAsNode()));


//        createFilledTestTerrain(width, height);
//        createSquareTestTerrain(width, height);

//        System.out.println(testTerrain.size());
//        testTerrain.stream().forEach(img -> {
//            getChildren().add(img.getAsNode());
//        });


        //TODO: not sure if this works yet
//        terrainList.forEach(terrainNode -> mapRoot.getChildren().add(terrainNode));

//        took out gridpane for hardcoded animation
        setPrefWidth(width);
        setPrefHeight(height);
        mapRoot.prefWidth(width);
        mapRoot.prefHeight(height);
    }

    public void update(double elapsedTime){
        //commenting out logic to hardcode animation
        myLogic.update(elapsedTime);
        List<ImmutableImageView> imageToAdd = myLogic.getObjectsToAdd();
        List<ImmutableImageView> imageToRemove = myLogic.getObjectsToRemove();

        imageToRemove.stream().forEach(img -> getChildren().remove(img.getAsNode()));
        imageToAdd.stream().forEach(img -> getChildren().add(img.getAsNode()));
    }

    public double getGridSize(){
        return terrainList.get(0).getAsNode().getBoundsInParent().getWidth();
    }

//    //NOT yet used
//    private void createFilledTestTerrain(double width, double height){
//        for (int i = 0; i < 20; i++) {
//
//            for(int j = 0;j<20;j++) {
//                try {
//                    Image image = new Image(new FileInputStream("resources/grass.jpg"));
//                    TransferImageView iv = new TransferImageView(image);
//                    iv.setFitWidth(width/20);
//                    iv.setFitHeight(height/20);
//                    iv.setTranslateX(iv.getFitWidth()*i);
//                    iv.setTranslateY(iv.getFitHeight()*j);
//                    testTerrain.add(iv);
//                }
//                catch (FileNotFoundException e) {
//                    System.out.println(e);
//                }
//
//            }
//        }
//    }
//
//    private void createSquareTestTerrain(double width, double height){
//        for (int i = 0; i < 20; i++) {
//
//            for(int j = 0;j<20;j++) {
////                Image test = new Image(getClass().getResourceAsStream("/resources/"+WEAPON_IMAGE));
//                try {
//                    Image image = new Image(new FileInputStream("resources/grass.jpg"));
////                Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("grass.jpg"));
//                    TransferImageView iv = new TransferImageView(image);
//                    iv.setFitWidth(height/20);
//                    iv.setFitHeight(height/20);
//                    iv.setTranslateX(iv.getFitWidth()*i);
//                    iv.setTranslateY(iv.getFitHeight()*j);
//                    testTerrain.add(iv);
//                }
//                catch (FileNotFoundException e) {
//                    System.out.println(e);
//                }
//
//            }
//        }
//    }




}
