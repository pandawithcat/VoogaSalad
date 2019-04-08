package GUI.GamePlay.GameScreen;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.List;

public class GamePlayMap extends GridPane {

    private Group gameRoot;
    private Logic myGameLogic;
    private List terrainImages;

    public GamePlayMap(double width, double height, Logic gameLogic) {
        myGameLogic = gameLogic;
        terrainImages = myGameLogic.getStaticImageViews();
        gameRoot = new Group();
        gameRoot.addAll(terrainImages);
//        terrainImages.stream().forEach(ImageView -> gameRoot.add(ImageView));
        setPrefWidth(width);
        setPrefHeight(height);
    }
}
