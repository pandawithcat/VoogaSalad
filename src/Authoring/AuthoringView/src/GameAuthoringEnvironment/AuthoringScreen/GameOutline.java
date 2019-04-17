package GameAuthoringEnvironment.AuthoringScreen;

import Configs.Configurable;
import Configs.GamePackage.Game;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
//import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Map;

//TODO Change all magic values

public class GameOutline extends Screen{

    private Pane myContent;
    private ImageView myImage;
    private int myHeight;
    private int myWidth;
    private TreeView<Configurable> myTreeView = new TreeView<>();
    private Game myGame;

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
        myGame = game;
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
                        setText(item.getLabel());
                    }
                }
            };

            controlTreeCellMouseClick(cell);
            return cell ;
        });
    }


    private void controlTreeCellMouseClick(TreeCell<Configurable> cell) {

        cell.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                if (mouseEvent.getClickCount() == 2) {
                    Object object = cell.getTreeItem().getValue();
                    //Don't open pop up screen if Game is clicked;
                    if(!object.getClass().equals(Game.class)){
                    findMyClass(object, myGame);}
                }
            }
        });

    }

    //recursively search the right class
    private void findMyClass(Object myObject, Configurable configurable) {

        Map<String, Object> myMap = configurable.getConfiguration().getDefinedAttributes();

        for (String key : myMap.keySet()) {
            var value = myMap.get(key);
            //base case
            if(value.equals(myObject)){
                showTheScreen();
            }
            // if value configurable recurse!
            else if (!value.getClass().isArray()  && value instanceof Configurable) {
                Configurable temp = (Configurable) value;
                findMyClass(myObject, temp);
            // if value is an array
            } else if(value.getClass().isArray()){

                Object[] valueArray = (Object[]) value;
                for(int b=0; b<valueArray.length ; b++){
                    if(valueArray[b].equals(myObject)){
                        showTheScreen();
                    }
                    else {
                        Configurable myConfigurable = (Configurable) valueArray[b];
                        findMyClass(myObject, myConfigurable);
                    }
                }
            }
        }
    }


    private void showTheScreen(){
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Change Settings");
        VBox layout = new VBox(10.00);
        VBox.setMargin(layout, new Insets(20, 20, 20, 20));
        Scene scene= new Scene(layout, 500, 500);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();
    }
}
