package GameAuthoringEnvironment.AuthoringComponents;

import BackendExternalAPI.Model;
import Configs.GamePackage.Game;
import GameAuthoringEnvironment.AuthoringScreen.GameController;
import GameAuthoringEnvironment.AuthoringScreen.GameOutline;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;

public class TopMenuBar {

    private HBox TopMenuBar;
    private GameController gameController;
    private GameOutline myGameOutline;

    //TODO @Hyunjae : Set Style for these buttons

    public TopMenuBar(GameOutline gameOutline){
        myGameOutline = gameOutline;
        TopMenuBar = new HBox();

        Button newGameButton = new Button("New Game");
        newGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                gameController = new GameController();
            }
        });

        Button saveButton = new Button("Save");
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                myGameOutline.makeTreeView(gameController.getMyGame());
                /*Model model = new Model();
                model.saveToXML(gameController.getMyGame());*/
            }
        });

        Button loadButton = new Button("Load");
        loadButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                //TODO Import XML using filechooser and call a game.d
               /* FileChooser fileChooser = new FileChooser();

                File selectedFile = fileChooser.showOpenDialog(myStage);
                if (selectedFile != null) {
                    //TODO Make Game based on this
                    String filepath = selectedFile.toString();
                    // TODO game should be created by reading in the xml
            *//*Game importedGame = new Game();
            importedGame = new Model(filepath);*//*

                    if (!filepath.endsWith("XML")) {
                        //TODO Alert
                    }
                }
                makeGame(new Game());*/
            }
        });

        Button settingsButton = new Button("Settings");
        settingsButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

               //TODO This button should show a pop up screen that allows users to change css settings, font size etc.
            }
        });

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                //TODO This button refresh the game outline
            }
        });

        TopMenuBar.getChildren().addAll(newGameButton, saveButton, loadButton, refreshButton);
    }

    public HBox getTopMenuBar(){
        return TopMenuBar;
    }
}
