package GUI.GamePlay.GameScreen;

import javafx.scene.layout.HBox;

public class GamePlayIDE extends HBox {
    public GamePlayLeftSide myGameLeft;
    public GamePlayRightSide myGameRight;
    public double screenWidth = 1000;
    private double screenHeight = 650;
    private double screenMinX;
    private double screenMinY;

    public GamePlayIDE(){
        myGameLeft = new GamePlayLeftSide();
        myGameRight = new GamePlayRightSide();
        this.getChildren().addAll(myGameLeft,myGameRight);
    }

    public getScreenWidth(){
        retur
    }


}
