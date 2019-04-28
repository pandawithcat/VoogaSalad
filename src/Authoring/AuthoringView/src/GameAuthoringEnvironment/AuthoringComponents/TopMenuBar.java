package GameAuthoringEnvironment.AuthoringComponents;

import BackendExternalAPI.Model;
import GameAuthoringEnvironment.AuthoringScreen.GameController;
import GameAuthoringEnvironment.AuthoringScreen.GameOutline;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class TopMenuBar {

    private HBox TopMenuBar;
    private GameController gameController;
    private GameOutline myGameOutline;

    //TODO @Hyunjae : Set Style for these buttons

    public TopMenuBar(GameOutline gameOutline){
        myGameOutline = gameOutline;
        TopMenuBar = new HBox();
        TopMenuBar.setSpacing(5);
        TopMenuBar.setPadding(new Insets(10,5,5,5));
        TopMenuBar.setAlignment(Pos.CENTER);
        Button newGameButton = new Button("New Game");
        newGameButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                try {
                    gameController = new GameController();
                    gameController.createConfigurable(gameController.getMyGame());
                } catch (NoSuchFieldException e) {
                    handle(event);
                }
            }
        });

        Button saveButton = new Button("Save");
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(gameController == null || gameController.getMyGame() == null){
                    createAlert();}
                else{
                myGameOutline.makeTreeView(gameController.getMyGame());}
            }
        });


        Button exportButton = new Button("Export");
        exportButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if(gameController == null){
                    createAlert();}
                else{
                Model model = new Model();
                model.saveToXML(gameController.getMyGame());}
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
                if(gameController == null){
                    createAlert();
                }else{
                myGameOutline.makeTreeView(gameController.getMyGame());}
            }
        });

        TopMenuBar.getChildren().addAll(newGameButton, saveButton, exportButton, loadButton, refreshButton);
    }

    private void createAlert() {
        AlertScreen alertScreen = new AlertScreen();
    }

    public HBox getTopMenuBar(){
        return TopMenuBar;
    }
}
