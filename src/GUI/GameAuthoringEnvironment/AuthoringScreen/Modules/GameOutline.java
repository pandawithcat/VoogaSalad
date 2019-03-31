package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameOutline extends Module {

    private VBox container;
    private Pane content;
    private TextArea editor;
    private String editorText;
    private int moduleWidth;
    ImageView myImage;


    public GameOutline(int width, int height, String moduleName){
        super(width, height, moduleName);
        content = getPane();
        moduleWidth = getModuleWidth();
        setContent();

    }



    private void setContent(){

        myImage = new ImageView(new Image(getClass().getResourceAsStream("/ButtonImages/Folder")));
        //TODO magic numbers should be changed based on the screensize
        myImage.setFitHeight(50);
        myImage.setFitWidth(125);

        //TODO inbox should be changed to game title and based on the info of the game, treeview should be made
        TreeItem<String> rootItem = new TreeItem<> ("Inbox", myImage);
        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<> ("Message" + i);
            rootItem.getChildren().add(item);
        }
        TreeView<String> tree = new TreeView<>(rootItem);
        content.getChildren().addAll(tree);
    }


}
