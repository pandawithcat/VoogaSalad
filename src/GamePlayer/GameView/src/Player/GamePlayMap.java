package Player;

import BackendExternal.Logic;
import Configs.ImmutableImageView;
import Configs.TransferImageView;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class GamePlayMap extends GridPane {
    private Logic myLogic;
    private List<ImmutableImageView> terrainList;
    private Group mapRoot;

    //TEST TERRAIN
    private List<ImmutableImageView> testTerrain = new ArrayList<ImmutableImageView>();


    public GamePlayMap(double width, double height, Logic logic) {
        myLogic = logic;
        //returns ImmutableImageViewList

        //TODO: uncomment when we have data that works
//        terrainList = myLogic.getLevelTerrain();
//        mapRoot = new Group();
//        terrainList.stream().forEach(img -> mapRoot.getChildren().add(img.getAsNode()));
        setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        //TODO: not sure if this works yet
//        terrainList.forEach(terrainNode -> mapRoot.getChildren().add(terrainNode));
        setPrefWidth(width);
        setPrefHeight(height);
    }



    //NOT yet used
    private void createTestTerrain(){
        for (int i = 0; i < 10; i++){

        }
    }




}
