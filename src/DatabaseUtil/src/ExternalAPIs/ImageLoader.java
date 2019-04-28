package ExternalAPIs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ImageLoader extends Application {

    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 500;
    public static final String TITLE = "Image Uploader";
    public static final String DEFAULT_FILE_TEXT = "Select a File to Upload";
    public static final String DEFAULT_TYPE_TEXT = "Select an Image Type";
    public static final String SAVE_BUTTON_TXT = "Save Image File";

    private static AuthoringData myAuthoringData;

    private static Stage myStage;
    private static Group myRoot;

    private VBox myVBox;
    private HBox fileSelectionHBox;
    private HBox typeSelectionHBox;
    private FileChooser myFileChooser;

    private File myImageFile;
    private AuthoringData.ImageType myImageType;

    private static int imageID;

    public static int main (String[] args){
        launch(args);
        return imageID;
    }

    @Override
    public void start(Stage stage){
        myAuthoringData = new AuthoringData();
        myFileChooser = new FileChooser();

        myStage = stage;
        setWindowLayout();
        Scene myScene = new Scene(myRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
        myStage.setScene(myScene);
        myStage.setTitle(TITLE);
        myStage.show();

        startFileChooser();
        createImageTypeMenu();
        createSaveButton();

    }

    private void setWindowLayout(){
        myRoot = new Group();
        myVBox = new VBox();
        fileSelectionHBox = new HBox();
        typeSelectionHBox = new HBox();
        myVBox.setSpacing(50);
        fileSelectionHBox.setSpacing(50);
        typeSelectionHBox.setSpacing(50);
        myVBox.getChildren().add(fileSelectionHBox);
        myVBox.getChildren().add(typeSelectionHBox);
        myRoot.getChildren().add(myVBox);
    }

    private void startFileChooser(){
        TextField fileTextBox = new TextField(DEFAULT_FILE_TEXT);
        Button fileButton = makeButton(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                myImageFile = myFileChooser.showOpenDialog(myStage);
                String fileName;
                if (myImageFile.toString().indexOf('/') != -1){
                    fileName = myImageFile.toString().substring(myImageFile.toString().lastIndexOf('/') + 1);
                }
                else {
                    fileName = myImageFile.toString().substring(myImageFile.toString().lastIndexOf('\\') + 1);
                }
                fileTextBox.setText(fileName);
            }
        });
        fileSelectionHBox.getChildren().add(fileTextBox);
        fileSelectionHBox.getChildren().add(fileButton);
    }

    private void createImageTypeMenu(){
        TextField typeTextBox = new TextField(DEFAULT_TYPE_TEXT);
        MenuButton menuButton = new MenuButton();

        for (int i = 0; i < AuthoringData.ImageType.values().length; i++){
            MenuItem mi = new MenuItem(AuthoringData.ImageType.values()[i].name());
            mi.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String selectedTypeString = mi.getText();
                    typeTextBox.setText(selectedTypeString);
                    myImageType = AuthoringData.ImageType.valueOf(selectedTypeString);
                }
            });
            menuButton.getItems().add(mi);

        }
        typeSelectionHBox.getChildren().add(typeTextBox);
        typeSelectionHBox.getChildren().add(menuButton);
    }

    private void createSaveButton(){
        Button saveButton = makeButton(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                testStoreImage();
            }
        });
        myVBox.getChildren().add(saveButton);
    }

    private void testStoreImage(){
        try {
            imageID = myAuthoringData.uploadImage(myImageFile,myImageType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Button makeButton(EventHandler<ActionEvent> handler){
        var newButton = new Button(SAVE_BUTTON_TXT);
        newButton.setOnAction(handler);
        return newButton;
    }
}
