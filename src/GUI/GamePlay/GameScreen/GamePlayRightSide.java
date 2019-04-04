package GUI.GamePlay.GameScreen;

import javafx.scene.layout.VBox;

public class GamePlayRightSide extends VBox {

    public GamePlayArsenal myGameArsenal;
    public GamePlayButton play;

    public GamePlayRightSide(){
        myGameArsenal = new GamePlayArsenal();
        play = new GamePlayButton();
        this.getChildren().addAll(myGameArsenal,play);
    }

}
