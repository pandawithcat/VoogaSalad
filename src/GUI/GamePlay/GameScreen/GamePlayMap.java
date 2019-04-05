package GUI.GamePlay.GameScreen;

import javafx.geometry.Insets;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class GamePlayMap extends HBox {

    public GamePlayMap(double width, double height) {
        setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));

        setPrefWidth(width);
        setPrefHeight(height);
    }
}
