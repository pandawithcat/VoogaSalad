package Player;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GamePlaySettingsBar extends HBox {

    public GamePlaySettingsBar(double width, double height){
        setPrefHeight(height);
        setId("HUD");
        setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));

        Rectangle rect = new Rectangle(500,100);
        rect.getStyleClass().add("my-rect");

        Label score = new Label("Score: ");
        score.setPrefWidth(width/3);
        Label lives = new Label("Lives: ");
        lives.setPrefWidth(width/3);
        Label money = new Label("Money: ");
        money.setPrefWidth(width/3);
        getChildren().addAll(score,lives,money);
    }
}
