package Player.GamePlay.GamePlayLeft;

import BackendExternal.Logic;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GamePlaySettingsBar extends StackPane {

    private static final int DEFAULT_SCORE = 0;
    private Image scoreImage = new Image(this.getClass().getClassLoader().getResourceAsStream("scoreboard.png"));
    private Text liveScore;
    private Text numLives;
    private Text myMoney;
    private Text myLevel;
    private Logic myLogic;
    private int padding;
    public GamePlaySettingsBar(double width, double height, Logic logic){
        myLogic = logic;
        padding = 8;
        setPrefHeight(height);
        setId("HUD");
        setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        setPadding(new Insets(0,0,10,0));

        HBox scoreHBox = new HBox();

        ImageView scoreImageView = new ImageView(scoreImage);
        ImageView livesImageView = new ImageView(scoreImage);
        ImageView moneyImageView = new ImageView(scoreImage);
        ImageView levelImageView = new ImageView(scoreImage);

        scoreImageView.setPreserveRatio(true);
        scoreImageView.setFitWidth(width/4 - padding);

        livesImageView.setPreserveRatio(true);
        livesImageView.setFitWidth(width/4  - padding);

        moneyImageView.setPreserveRatio(true);
        moneyImageView.setFitWidth(width/4 - padding) ;

        levelImageView.setPreserveRatio(true);
        levelImageView.setFitWidth(width/4 - padding);


        scoreHBox.setSpacing(8);
        scoreHBox.setAlignment(Pos.CENTER_LEFT);
        scoreHBox.getChildren().addAll(scoreImageView, livesImageView, moneyImageView, levelImageView);
        scoreHBox.setMaxHeight(height);
        scoreHBox.setMaxWidth(width);
        getChildren().add(scoreHBox);
        createText(width);
    }
    private void createText(double width){
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);
        liveScore = new Text("Score: ");
        numLives = new Text("Lives: ");
        myMoney = new Text("Money: ");
        myLevel = new Text("Level: ");
        textHBox.getChildren().addAll(liveScore, numLives, myMoney, myLevel);
        double spacing = (width - 4 * (liveScore.getBoundsInLocal().getWidth()))/4;
        textHBox.setSpacing(spacing);
        getChildren().add(textHBox);
    }

    public void updateVariables(){
        liveScore.setText("Score: " + myLogic.getScore());
//        numLives.setText("Lives: " + myLogic.getLives());
        myMoney.setText("Money: " + myLogic.getCash());
    }

    public void updateLevel(int currLevel){
        myLevel.setText("Level: " + currLevel);
    }

}
