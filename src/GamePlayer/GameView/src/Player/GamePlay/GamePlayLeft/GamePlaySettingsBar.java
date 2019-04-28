package Player.GamePlay.GamePlayLeft;

import BackendExternal.Logic;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GamePlaySettingsBar extends StackPane {

    private static final int DEFAULT_SCORE = 0;

    private Text liveScore;
    private Text numLives;
    private Text myMoney;
    private Text myLevel;
    private Logic myLogic;

    public GamePlaySettingsBar(double width, double height, Logic logic){
        myLogic = logic;
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

        liveScore = new Text("Score: ");
        liveScore.setTranslateX(rect1.getTranslateX());

        numLives = new Text("Lives: ");
        numLives.setTranslateX(rect2.getTranslateX());

        myMoney = new Text("Money: ");
        myMoney.setTranslateX(rect3.getTranslateX());

        myLevel = new Text("Level: ");
        myLevel.setTranslateX(rect4.getTranslateX());

        getChildren().addAll(liveScore,numLives,myMoney,myLevel);
    }

    public void updateVariables(){
        liveScore.setText("Score: " + myLogic.getScore());
//        numLives.setText("Lives: " + myLogic.getLives());
        myMoney.setText("Money: " + myLogic.getCash());
//        myLevel.setText("Level: " + myLogic.getLevel);
    }

}
