package GameAuthoringEnvironment.AuthoringScreen;

import Configs.Configurable;
import Configs.GamePackage.Game;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
//import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Map;

//TODO Change all magic values

public class GameOutline extends Screen{

    private Pane myContent;
    private ImageView myImage;
    private int myHeight;
    private int myWidth;
    private TreeView<Class> myTreeView = new TreeView<>();

    public GameOutline(int width, int height){
        super(width, height);
        myHeight = height;
        myWidth = width;
        myContent = getContent();
        Game myGame = new Game();
        setContent(myGame);
    }



    public void setContent(Game game) {

        Image test = new Image(getClass().getResourceAsStream("/ButtonImages/"+"Folder.png"));
        myImage = new ImageView(test);
        //TODO magic numbers should be changed based on the screensize
        myImage.setFitHeight(50);
        myImage.setFitWidth(50);

        // Set the Root Node
        //TODO Tree Not working properly
        TreeItem<Class> myRoot = new TreeItem<>(game.getClass());
        myTreeView.setRoot(myRoot);
        createTreeView(game);
        myContent.getChildren().addAll(myTreeView);
    }

    //recursively create a treeview
    private void createTreeView(Configurable myConfigurable){
        // Create the TreeView
        // Create the Root TreeItem
        TreeItem<Class> rootItem = new TreeItem(myConfigurable);
        // Add children to the root
        Map<String, Class> myMap = myConfigurable.getConfiguration().getAttributes();

        for(String key: myMap.keySet()){
            var clazz = myMap.get(key);
            if(clazz.isInstance(Configurable.class)){
                TreeItem<Class> treeItem = new TreeItem<>(clazz);
                rootItem.getChildren().add(treeItem);
                try {
                    Class<?> clazz1 = Class.forName(clazz.getName());
                    Constructor<?> cons = clazz1.getConstructor(Configurable.class);
                    var object = cons.newInstance(myConfigurable);
                    System.out.println(object.getClass());
                    createTreeView((Configurable) object);
                }catch (Exception e){
                    //TODO Handle Error
                    e.printStackTrace();
                }
            }else{
                TreeItem<Class> treeItem = new TreeItem<>(clazz);
                rootItem.getChildren().add(treeItem);
            }
        }



        myTreeView.setCellFactory(tree -> {
            //TODO Set Images Accordingly
            TreeCell<Class> cell = new TreeCell<>() {
                @Override
                public void updateItem(Class item, boolean empty) {
                    super.updateItem(item, empty) ;
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        if(super.getTreeItem().getValue().equals("Game title")){
                            setGraphic(myImage);
                        }
                        setText(item.getSimpleName());
                    }
                }
            };
            controlTreeCellMouseClick(cell);
            return cell ;
        });
    }


    private void controlTreeCellMouseClick(TreeCell<Class> cell) {

        cell.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {
                    //TODO Implement reflection here

                }
            }
        });

    }


}
