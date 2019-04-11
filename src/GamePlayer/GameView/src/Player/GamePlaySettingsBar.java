package Player;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GamePlaySettingsBar extends StackPane {

    public GamePlaySettingsBar(double width, double height){
        setPrefHeight(height);
        setId("HUD");
        setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        setPadding(new Insets(0,0,10,0));
        Rectangle rect1 = new Rectangle(width/8,height/2);
        rect1.getStyleClass().add("my-rect");
        rect1.setTranslateX(- width *2/6);

        Rectangle rect2 = new Rectangle(width/8,height/2);
        rect2.getStyleClass().add("my-rect");
        rect2.setTranslateX(- width / 6);

        Rectangle rect3 = new Rectangle(width/8,height/2);
        rect3.getStyleClass().add("my-rect");
        rect3.setTranslateX(width /6);

        Rectangle rect4 = new Rectangle(width/8,height/2);
        rect4.getStyleClass().add("my-rect");
        rect4.setTranslateX(width *2/6);
        
        getChildren().addAll(rect1,rect2,rect3,rect4);

        Text score = new Text("Score: ");
        score.setTranslateX(rect1.getTranslateX());
        Text lives = new Text("Lives: ");
        lives.setTranslateX(rect2.getTranslateX());
        Text money = new Text("Money: ");
        money.setTranslateX(rect3.getTranslateX());
        Text level = new Text("Level: ");
        level.setTranslateX(rect4.getTranslateX());
        getChildren().addAll(score,lives,money, level);
    }

}
