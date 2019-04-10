package Player;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ButtonPanel extends VBox {

    private PlayButton myPlayButton;
    private FastFowardButton myFastFowardButton;
    private SettingsPanel mySettingsPanel;

    public ButtonPanel(double width, double height){
        setId("HUD");
        setSpacing(1);
        myPlayButton = new PlayButton(width, height* 0.4);
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 0, 10, 15));
        hbox.setSpacing(10);
        hbox.getChildren().add(myPlayButton);
        myFastFowardButton = new FastFowardButton(width, height * 0.4);
        myPlayButton.setOnAction(e->changeToFastFoward(hbox));
        myFastFowardButton.setOnAction(e->changeToPlay(hbox));
        mySettingsPanel = new SettingsPanel(width, height/2);
        getChildren().add(mySettingsPanel);
        getChildren().add(hbox);
    }
    private void changeToPlay(HBox hbox){
        if(hbox.getChildren().contains(myFastFowardButton)){
            hbox.getChildren().remove(myFastFowardButton);
        }
        hbox.getChildren().add(myPlayButton);
    }
    private void changeToFastFoward(HBox hbox){
        hbox.getChildren().remove(myPlayButton);
        hbox.getChildren().add(myFastFowardButton);
    }
}
