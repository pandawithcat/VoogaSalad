package GUI.GamePlay.GameScreen;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GamePlayMap extends GridPane {

    private Group gameRoot;

    public GamePlayMap(double width, double height) {
        gameRoot = new Group();

        setPrefWidth(width);
        setPrefHeight(height);
    }
}
