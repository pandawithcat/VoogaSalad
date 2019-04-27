package Player.GamePlay.GamePlayLeft;

import Player.GamePlay.Buttons.FastFowardButton;
import Player.GamePlay.Buttons.PlayButton;
import Player.GamePlay.GamePlayRight.SettingsPanel;
import Player.GamePlay.PlayInterface;
import Player.GamePlay.SelectionInterface;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ButtonPanel extends VBox {

    private PlayButton myPlayButton;
    private FastFowardButton myFastFowardButton;
    private SettingsPanel mySettingsPanel;

    public ButtonPanel(double width, double height, PlayInterface method, PlayInterface fastFoward, SelectionInterface home, MediaPlayer mediaPlayer){
        setPadding(new Insets(0, 0, 30, 0));
        setId("HUD");
        setSpacing(1);
        setAlignment(Pos.CENTER);
        myPlayButton = new PlayButton(width, height* 0.4);
        myFastFowardButton = new FastFowardButton(width, height * 0.4);
        myPlayButton.setOnAction(e-> {
                    try {
                        changeToFastFoward();
                        method.playButton();
                    } catch (Exception error) {
                        error.printStackTrace();
                    }
                });
        myFastFowardButton.setOnAction(e->{
            changeToPlay();
            fastFoward.playButton();
        });
        mySettingsPanel = new SettingsPanel(width, height/2, home, mediaPlayer);
        getChildren().add(mySettingsPanel);
        getChildren().add(myPlayButton);
    }

    private void changeToPlay(){
        getChildren().remove(myFastFowardButton);
        getChildren().add(myPlayButton);
    }
    private void changeToFastFoward(){
        getChildren().remove(myPlayButton);
        getChildren().add(myFastFowardButton);
    }
}
