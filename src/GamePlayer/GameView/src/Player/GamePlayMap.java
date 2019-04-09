package Player;

import BackendExternal.Logic;
import BackendExternal.WeaponInfo;
import Configs.ImmutableImageView;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class GamePlayMap extends GridPane {
    private Logic myLogic;
    private List <ImmutableImageView> terrainList;
    private Group mapRoot;

    public GamePlayMap(double width, double height, Logic logic) {
        myLogic = logic;
        terrainList = myLogic.getLevelTerrain();
        mapRoot = new Group();
        //TODO: fix what the getLevelTerrain() method returns - we need objects that can be added to root
        terrainList.forEach(terrainObject -> mapRoot.getChildren().addAll(terrainObject));
        setPrefWidth(width);
        setPrefHeight(height);
    }

}
