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
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TODO Change all magic values

public class GameOutline extends Screen{

    private Pane myContent;
    private ImageView myImage;
    private int myHeight;
    private int myWidth;
    private TreeView<Configurable> myTreeView = new TreeView<>();

    public GameOutline(int width, int height){
        super(width, height);
        myHeight = height;
        myWidth = width;
        myContent = getContent();
        Game myGame = new Game();
        setContent(myGame);
    }



    public void setContent(Game game) {

       /* Image test = new Image(getClass().getResourceAsStream("/ButtonImages/"+"Folder.png"));
        myImage = new ImageView(test);
        //TODO magic numbers should be changed based on the screensize
        myImage.setFitHeight(50);
        myImage.setFitWidth(50);*/
    }

    public void makeTreeView(Game game){
        TreeItem<Configurable> myRoot = new TreeItem<>(game);
        createRecursion(myRoot);
        myTreeView.setRoot(myRoot);
        setCellFactory();
        myContent.getChildren().add(myTreeView);
    }

    //recursively create a treeview
    private void createRecursion(TreeItem<Configurable> myConfigurable) {


        Map<String, Object> myMap = myConfigurable.getValue().getConfiguration().getDefinedAttributes();

        for (String key : myMap.keySet()) {
            var value = myMap.get(key);
            if (!value.getClass().isArray()  && value.getClass().isInstance(Configurable.class)) {
                try {
                    TreeItem<Configurable> treeItem = new TreeItem<>((Configurable) value);
                    myConfigurable.getChildren().add(treeItem);
                    createRecursion(treeItem);
                } catch (Exception e) {
                    //TODO Handle Error
                    e.printStackTrace();
                }
            }else if(value.getClass().isArray()){

                int length = Array.getLength(value);
                Object[] valueArray = (Object[]) value;
                for(int b=0; b<valueArray.length ; b++){
                    Configurable configurable = (Configurable) valueArray[b];
                    TreeItem<Configurable> treeItem = new TreeItem<>(configurable);
                    myConfigurable.getChildren().add(treeItem);
                    createRecursion(treeItem);
                }
            }
        }
    }

    private void setCellFactory(){

        myTreeView.setCellFactory(tree -> {
            //TODO Set Images Accordingly
            TreeCell<Configurable> cell = new TreeCell<>() {
                @Override
                public void updateItem(Configurable item, boolean empty) {
                    super.updateItem(item, empty) ;
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item.toString());
                    }
                }
            };
            //controlTreeCellMouseClick(cell);
            return cell ;
        });
    }


    private void controlTreeCellMouseClick(TreeCell<Configurable> cell) {

        cell.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {
                    //TODO Implement reflection here

                }
            }
        });

    }


}
