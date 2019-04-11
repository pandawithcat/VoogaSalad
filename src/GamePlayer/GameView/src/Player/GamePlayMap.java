package Player;

import BackendExternal.Logic;
import Configs.ImmutableImageView;
import Configs.MapPackage.MapConfig;
import Configs.TransferImageView;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GamePlayMap extends GridPane {
    private Logic myLogic;
    private List<ImmutableImageView> terrainList;
    private Group mapRoot;
    public static final String WEAPON_IMAGE = "wood.jpg";
    public static final String GRASS_IMAGE = "grassplay.jpg";

    private Image image = new Image(WEAPON_IMAGE);


    //TEST TERRAIN
    private List<ImmutableImageView> testTerrain = new ArrayList<ImmutableImageView>();
    private List<ImmutableImageView> imageToAdd;
    private List<ImmutableImageView> imageToRemove;



    public GamePlayMap(double width, double height, Logic logic) {
        myLogic = logic;
        //returns ImmutableImageViewList

        //TODO: uncomment when we have data that works
//        terrainList = myLogic.getLevelTerrain();
//        mapRoot = new Group();
//        terrainList.stream().forEach(img -> mapRoot.getChildren().add(img.getAsNode()));
        mapRoot=new Group();
        createTestTerrain();
        System.out.println(testTerrain.size());
        testTerrain.stream().forEach(img -> {
            getChildren().add(img.getAsNode());
        });

        setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        //TODO: not sure if this works yet
//        terrainList.forEach(terrainNode -> mapRoot.getChildren().add(terrainNode));
        setPrefWidth(width);
        setPrefHeight(height);
        System.out.println("Daddy: " + width);
        System.out.println("Chill: " + height);

    }

    public void update(long elapsedTime){
        myLogic.update(elapsedTime);
        imageToAdd = myLogic.getObjectsToAdd();
        imageToRemove = myLogic.getObjectsToRemove();
        //TODO: third method to move obejcts?
        imageToRemove.stream().forEach(img -> mapRoot.getChildren().remove(img.getAsNode()));
        imageToAdd.stream().forEach(img -> mapRoot.getChildren().add(img.getAsNode()));
    }

    //NOT yet used
    private void createTestTerrain(){
        for (int i = 0; i < 10; i++) {
            for(int j = 0; j < 10 ;j++) {
                System.out.println("sem");
//                Image test = new Image(getClass().getResourceAsStream("/resources/"+WEAPON_IMAGE));
                TransferImageView iv = new TransferImageView(new Image(WEAPON_IMAGE));
                iv.setFitWidth(60);
                iv.setFitHeight(60);
                iv.setTranslateX(60*i);
                iv.setTranslateY(60*j);
                testTerrain.add(iv);
            }
        }
    }




}
