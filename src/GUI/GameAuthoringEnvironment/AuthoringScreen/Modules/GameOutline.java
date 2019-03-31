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

import java.util.ArrayList;

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
        content.setMaxSize(300, 1000);
        content.setMinSize(300, 1000);
        moduleWidth = getModuleWidth();
        setContent();

    }



    public void setContent() {

        myImage = new ImageView(new Image(getClass().getResourceAsStream("/ButtonImages/Folder")));
        //TODO magic numbers should be changed based on the screensize
        myImage.setFitHeight(50);
        myImage.setFitWidth(50);

        TreeViewHelper helper = new TreeViewHelper();
        ArrayList<TreeItem> levels = helper.getLevels();

        // Create the TreeView
        TreeView treeView = new TreeView();
        // Create the Root TreeItem
        TreeItem rootItem = new TreeItem("Game title", myImage);
        // Add children to the root
        rootItem.getChildren().addAll(levels);
        // Set the Root Node
        treeView.setRoot(rootItem);
        treeView.setMinWidth(300);
        treeView.setMaxWidth(300);
        treeView.setMinHeight(1000);
        treeView.setMaxHeight(1000);

        content.getChildren().addAll(treeView);
    }

/*
        //TODO inbox should be changed to game title and based on the info of the game, treeview should be made
        TreeItem<String> rootItem = new TreeItem<> ("Game Title", myImage);
        rootItem.setExpanded(true);
        // TODO i should be replaced with the number of levels
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<> ("Level" + i);
            rootItem.getChildren().add(item);
        }

        TreeView<String> tree = new TreeView<>(rootItem);
        content.getChildren().addAll(tree);
    }*/


}
