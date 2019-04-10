package GameAuthoringEnvironment.AuthoringComponents;

import GameAuthoringEnvironment.AuthoringComponents.Buttons.*;
import GameAuthoringEnvironment.AuthoringScreen.GameController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class TopMenuBar {

    private HBox TopMenuBar;
    public TopMenuBar(){
        TopMenuBar = new HBox();

        HelpButton helpButton = new HelpButton();
        SaveButton saveButton = new SaveButton();
        ImageButton imageButton = new ImageButton();
        LoadButton loadButton = new LoadButton();
        PlayButton playButton = new PlayButton();
        ViewButton viewButton = new ViewButton();
        NewGameButton newGameButton = new NewGameButton();
        newGameButton.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                GameController gameController = new GameController();
            }
        });
        TopMenuBar.getChildren().addAll(newGameButton.getButton(), saveButton.getButton(), loadButton.getButton(), imageButton.getButton(), playButton.getButton(),
                viewButton.getButton(), helpButton.getButton());
    }

    public HBox getTopMenuBar(){
        return TopMenuBar;
    }
}
