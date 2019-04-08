package Player;

import BackendExternal.Logic;
import javafx.geometry.Insets;
import javafx.scene.control.TreeView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class GamePlayMap extends GridPane {

    public GamePlayMap(double width, double height, Logic logic) {

        setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        setPrefWidth(width);
        setPrefHeight(height);
    }
}
