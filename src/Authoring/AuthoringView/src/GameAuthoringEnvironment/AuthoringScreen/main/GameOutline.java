package GameAuthoringEnvironment.AuthoringScreen.main;

import GameAuthoringEnvironment.AuthoringScreen.main.Editors.ArsenalEditor;
import GameAuthoringEnvironment.AuthoringScreen.main.Editors.EnemiesEditor;
import GameAuthoringEnvironment.AuthoringScreen.main.Editors.MapEditor;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class GameOutline extends Screen {

    private Pane content;
    private int moduleWidth;
    private final TextArea textArea = new TextArea();
    private ImageView myImage;
    private Group myRoot;

    public GameOutline(Group root, int width, int height, String moduleName){
        super(root, width, height, moduleName, false);
        myRoot = root;
        content = getContent();
        content.setMaxSize(300, 1000);
        content.setMinSize(300, 1000);
        moduleWidth = getModuleWidth();
        setContent();
    }



    public void setContent() {

        var url = this.getClass().getClassLoader().getResource("ButtonImages");
        try {
            File folder = new File(url.toURI());
            Image test = new Image(folder.toURI()+"Folder");
            myImage = new ImageView(test);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //TODO magic numbers should be changed based on the screensize
        myImage.setFitHeight(50);
        myImage.setFitWidth(50);

        //TODO helper should be changed so that it takes in a int parameter(number of levels) and produces same number of level treeitems.
        TreeViewHelper helper = new TreeViewHelper(2);
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
            controlTreeCellMouseClick(cell);
            return cell ;
        });

        content.getChildren().addAll(treeView);
    }

    private void controlTreeCellMouseClick(TreeCell<String> cell) {

        cell.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {
                    //TODO Can change this to reflection
                    if (cell.getTreeItem().getValue().equals("Map")) {
                        //System.out.println("map screen created");
                        createMapScreen();
                    }
                    if (cell.getTreeItem().getValue().equals("Arsenals")) {
                        //System.out.println("Arsenal screen created");
                        createArsenalScreen();
                    }
                    if (cell.getTreeItem().getValue().equals("Enemies")) {
                        //System.out.println("Enemies screen created");
                        createEnemiesScreen();
                    }

                }
            }
        });

    }


    private void createMapScreen(){
        MapEditor mapEditor = new MapEditor(myRoot,300, 300, "Map Editor");
        myRoot.getChildren().add(mapEditor.getVBox());
    }

    private void createArsenalScreen(){
        ArsenalEditor arsenalEditor = new ArsenalEditor(myRoot, 500, 500, "Arsenal Editor");
        myRoot.getChildren().add(arsenalEditor.getVBox());

    }

    private void createEnemiesScreen(){
        EnemiesEditor enemiesEditor = new EnemiesEditor(myRoot, 300, 300, "EnemyPackage Editor");
        myRoot.getChildren().add(enemiesEditor.getVBox());

    }


}
