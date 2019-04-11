package GameAuthoringEnvironment.AuthoringComponents;

import BackendExternalAPI.Model;
import GameAuthoringEnvironment.AuthoringComponents.Buttons.*;
import GameAuthoringEnvironment.AuthoringScreen.GameController;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class TopMenuBar {

    private HBox TopMenuBar;
    private GameController gameController;
    public TopMenuBar(){
        TopMenuBar = new HBox();

        HelpButton helpButton = new HelpButton();
        SaveButton saveButton = new SaveButton();
        saveButton.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                Model model = new Model();
                model.saveToXML(gameController.getMyGame());
            }
        });

        ImageButton imageButton = new ImageButton();
        LoadButton loadButton = new LoadButton();
        PlayButton playButton = new PlayButton();
        ViewButton viewButton = new ViewButton();
        NewGameButton newGameButton = new NewGameButton();
        newGameButton.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                gameController = new GameController();
            }
        });
        TopMenuBar.getChildren().addAll(newGameButton.getButton(), saveButton.getButton(), loadButton.getButton(), imageButton.getButton(), playButton.getButton(),
                viewButton.getButton(), helpButton.getButton());
    }

    public HBox getTopMenuBar(){
        return TopMenuBar;
    }
}
