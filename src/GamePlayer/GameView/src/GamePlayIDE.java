import javafx.scene.layout.HBox;

public class GamePlayIDE extends HBox {
    public static final double LEFT_RATIO = 0.75;
    public static final double RIGHT_RATIO = 0.25;
    public GamePlayLeftSide myGameLeft;
    public GamePlayRightSide myGameRight;

    private double screenMinX;
    private double screenMinY;

    public GamePlayIDE(double width, double height){
        setPrefWidth(width);
        setPrefHeight(height);
        myGameLeft = new GamePlayLeftSide(width * LEFT_RATIO, height);
        myGameRight = new GamePlayRightSide(width * RIGHT_RATIO, height);
        this.getChildren().addAll(myGameLeft,myGameRight);
    }

}
