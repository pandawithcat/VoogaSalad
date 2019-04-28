package Player.GamePlay.GamePlayLeft;

import BackendExternal.Logic;
import Configs.ImmutableImageView;
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
    private Image scoreBoardImage = new Image(this.getClass().getClassLoader().getResourceAsStream("scoreboard.png"));

    private Image levelImage = new Image(this.getClass().getClassLoader().getResourceAsStream("level.png"));
    private Image livesImage = new Image(this.getClass().getClassLoader().getResourceAsStream("lives.png"));
    private Image moneyImage = new Image(this.getClass().getClassLoader().getResourceAsStream("money.png"));
    private Image scoreImage = new Image(this.getClass().getClassLoader().getResourceAsStream("score.png"));

    private Text liveScore;
    private Text numLives;
    private Text myMoney;
    private Text myLevel;
    private Logic myLogic;
    private int padding;
    private double height;
    private double width;
    public GamePlaySettingsBar(double width, double height, Logic logic){
        myLogic = logic;
        padding = 8;
        this.height = height;
        this.width = width;
        setPrefHeight(height);
        setId("HUD");
        setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 255), CornerRadii.EMPTY, Insets.EMPTY)));
        setPadding(new Insets(0,0,10,0));

        HBox scoreHBox = new HBox();

        ImageView scoreImageView = new ImageView(scoreBoardImage);
        ImageView livesImageView = new ImageView(scoreBoardImage);
        ImageView moneyImageView = new ImageView(scoreBoardImage);
        ImageView levelImageView = new ImageView(scoreBoardImage);

        scoreImageView.setPreserveRatio(true);
        scoreImageView.setFitWidth(width/4 - padding);

        livesImageView.setPreserveRatio(true);
        livesImageView.setFitWidth(width/4  - padding);

        moneyImageView.setPreserveRatio(true);
        moneyImageView.setFitWidth(width/4 - padding) ;

        levelImageView.setPreserveRatio(true);
        levelImageView.setFitWidth(width/4 - padding);


        scoreHBox.setSpacing(padding);
        scoreHBox.setAlignment(Pos.CENTER_LEFT);
        scoreHBox.getChildren().addAll(scoreImageView, livesImageView, moneyImageView, levelImageView);
        scoreHBox.setMaxHeight(height);
        scoreHBox.setMaxWidth(width);
        getChildren().add(scoreHBox);
        createLabels(scoreImageView.getBoundsInLocal().getWidth(),scoreImageView.getBoundsInLocal().getHeight());
    }
    private void createLabels(double imageWidth, double imageHeight){
        HBox textHBox = new HBox();
        textHBox.setAlignment(Pos.CENTER);
        liveScore = new Text("100");
        numLives = new Text("100");
        myMoney = new Text("100");
        myLevel = new Text("100");
        VBox score = createLabelVBox(scoreImage, imageWidth, imageHeight, liveScore);
        VBox level = createLabelVBox(levelImage, imageWidth, imageHeight, myLevel);
        VBox money = createLabelVBox(moneyImage, imageWidth, imageHeight, myMoney);
        VBox lives = createLabelVBox(livesImage, imageWidth, imageHeight, numLives);

        textHBox.getChildren().addAll(score, level, money, lives);
        textHBox.setSpacing(padding);
        textHBox.setMaxWidth(width);
        textHBox.setMaxHeight(height);
        getChildren().add(textHBox);
    }
    private VBox createLabelVBox(Image image, double imageWidth, double imageHeight, Text text){
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth((width/4  - padding)/2);
        VBox vBox = new VBox();
        vBox.setMaxHeight(height);
        vBox.setPrefWidth(width/4  - padding);
        vBox.getChildren().addAll(imageView, text);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }

    public void updateVariables(){
        liveScore.setText(Double.toString(myLogic.getScore()));
//        numLives.setText("Lives: " + myLogic.getLives());
        myMoney.setText(Double.toString(myLogic.getCash()));
    }

    public void updateLevel(int currLevel){
        myLevel.setText(Integer.toString(currLevel));
    }

}
