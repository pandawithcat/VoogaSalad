package GUI.GamePlay.GameScreen;

import javafx.scene.layout.HBox;

public class GamePlayIDE extends HBox {
    public static final double LEFT_RATIO = 0.75;
    public static final double RIGHT_RATIO = 0.25;
    public GamePlayLeftSide myGameLeft;
    public GamePlayRightSide myGameRight;
    private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private double screenMinX;
    private double screenMinY;

    public GamePlayIDE(double width, double height, Logic gameLogic){
        setPrefWidth(width);
        setPrefHeight(height);
        myGameLeft = new GamePlayLeftSide(width * LEFT_RATIO, height, gameLogic);
        myGameRight = new GamePlayRightSide(width * RIGHT_RATIO, height, gameLogic);
        this.getChildren().addAll(myGameLeft,myGameRight);
        loopGame(FRAMES_PER_SECOND);
    }

    private void loopGame(int sec){
        while (true){
            double start = getCurrentTime();
            checkIfLevelEnd();
            processInput();
            update();
            render();
            sleep();
        }
    }

    private void update(){

    }
    private void render(){

    }
    private void sleep(){

    }
    private void processInput(){
    }


}
