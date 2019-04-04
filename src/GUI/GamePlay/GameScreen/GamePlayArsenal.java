package GUI.GamePlay.GameScreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import javax.swing.text.html.ListView;

public class GamePlayArsenal extends ListView {

    public GamePlayArsenal(double width, double height, int padding, String itemList){
        ObservableList<String> items = FXCollections.observableArrayList(itemList);
        setItems(items);
        setPadding(new Insets(textPadding,textPadding,textPadding,textPadding));
        setPrefWidth(width/2 - padding * 2);
        setPrefHeight(height * 0.25 - padding * 3);
        setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    }


}
