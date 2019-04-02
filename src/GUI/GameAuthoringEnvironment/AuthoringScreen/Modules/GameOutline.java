package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules;

import GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Editors.ArsenalEditor;
import GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Editors.EnemiesEditor;
import GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Editors.MapEditor;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
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
    private final TextArea textArea = new TextArea();
    private ImageView myImage;
    private BorderPane myRoot;

    public GameOutline(BorderPane root, int width, int height, String moduleName){
        super(width, height, moduleName);
        myRoot = root;
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

        //TODO helper should be changed so that it takes in a int parameter(number of levels) and produces same number of level treeitems.
        TreeViewHelper helper = new TreeViewHelper();
        ArrayList<TreeItem> levels = helper.getLevels();

        // Create the TreeView
        TreeView treeView = new TreeView();
        // Create the Root TreeItem
        TreeItem rootItem = new TreeItem("Game title");
        // Add children to the root
        rootItem.getChildren().addAll(levels);
        // Set the Root Node
        treeView.setRoot(rootItem);
        treeView.setMinWidth(300);
        treeView.setMaxWidth(300);
        treeView.setMinHeight(1000);
        treeView.setMaxHeight(1000);

        treeView.setCellFactory(tree -> {

            TreeCell<String> cell = new TreeCell<>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        if(super.getTreeItem().getValue().equals("Game title")){
                            setGraphic(myImage);
                        }
                        setText(item);
                    }
                }
            };

            cell.setOnMouseClicked(event -> {
                if (! cell.isEmpty()) {
                    if(cell.getTreeItem().getValue().equals("Map")){
                        System.out.println("map screen created");
                        createMapScreen();
                    }
                    if(cell.getTreeItem().getValue().equals("Arsenals")){
                        System.out.println("Turret screen created");
                        createArsenalScreen();
                    }
                    if(cell.getTreeItem().getValue().equals("Enemies")){
                        System.out.println("Enemies screen created");
                        createEnemiesScreen();
                    }

                }
            });
            return cell ;
        });


        content.getChildren().addAll(treeView);
    }

    private void createMapScreen(){
        MapEditor mapEditor = new MapEditor(300, 300, "Map Editor");
        myRoot.setCenter(mapEditor.getContent());

    }

    private void createArsenalScreen(){
        ArsenalEditor arsenalEditor = new ArsenalEditor(300, 300, "Arsenal Editor");
        myRoot.setRight(arsenalEditor.getContent());

    }

    private void createEnemiesScreen(){
        EnemiesEditor enemiesEditor = new EnemiesEditor(300, 300, "Enemy Editor");
        myRoot.setCenter(enemiesEditor.getContent());


    }




}