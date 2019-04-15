package Player;

import BackendExternal.Logic;
import Configs.Info;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class GamePlayArsenal extends VBox {

    public static final double ARSENAL_RATIO = 0.90;
    public static final double SELECTOR_RATIO = 0.10;

    private Logic myLogic;
    private GamePlayArsenalSelector myArsenalSelector;
    public static final String WEAPON_IMAGE = "weapon.png";
    public static final String OBSTACLE_IMAGE = "obstacle.png";
    private Image weaponImage;
    private Image obstacleImage;
    private ImageView weaponImageView;
    private ImageView obstacleImageView;
    private boolean isWeapon;
    private ArrayList<ImageView> viewList;
    private ListView arsenalDisplay;
    private double myArsenalWidth;
    private HBox arsenalSelector;


    private Map <Integer, Info> myTestWeapons ;
    private Map <Integer, Info> myTestObstacles ;


    //list of WeaponInfo objects which has ID and an imageview
    private Map<Integer, Info> myArsenal;

    public GamePlayArsenal(double arsenalWidth, double arsenalHeight, Logic logic) throws FileNotFoundException {
        myArsenalWidth = arsenalWidth;
        //initialize weapon display first
        isWeapon = true;
        myLogic = logic;
//        myArsenal = logic.getMyArsenal();
        arsenalDisplay = new ListView();
        arsenalDisplay.setPrefHeight(arsenalHeight * ARSENAL_RATIO);
        arsenalDisplay.setPrefWidth(arsenalWidth);

        //START TEST STUFF
        createTestWeaponArsenal();
        createTestObstacleArsenal();
        viewList = new ArrayList<>();
        setArsenalDisplay(myTestWeapons,arsenalWidth);


        //TODO: implement the hover shit when we set content
//        rootItem.getChildren().addAll(myArsenal);
//        arsenalView.setRoot(rootItem);

        arsenalDisplay.setPrefHeight(arsenalHeight * ARSENAL_RATIO);
        arsenalDisplay.setPrefWidth(arsenalWidth);

        getChildren().addAll(arsenalDisplay);

        //arsenal selector part
//        myArsenalSelector = new GamePlayArsenalSelector(arsenalWidth,arsenalHeight * SELECTOR_RATIO);
        getChildren().add(createArsenalSelector(arsenalWidth,arsenalHeight * SELECTOR_RATIO));
    }

    private HBox createArsenalSelector(double width, double height) throws FileNotFoundException {
        arsenalSelector = new HBox();
        weaponImage = new Image(new FileInputStream("resources/" +WEAPON_IMAGE));
        weaponImageView = new ImageView(weaponImage);
        weaponImageView.setFitHeight(height);
        weaponImageView.setFitWidth(width /2);
        Button weaponButton = new Button("", weaponImageView);
        weaponButton.setOnAction(e -> switchWeaponDisplay());
        arsenalSelector.getChildren().add(weaponButton);

        //obstacles
        obstacleImage = new Image(new FileInputStream("resources/" + OBSTACLE_IMAGE));
        obstacleImageView = new ImageView(obstacleImage);
        obstacleImageView.setFitHeight(height);
        obstacleImageView.setFitWidth(width /2);
        Button obstacleButton = new Button("", obstacleImageView);
        obstacleButton.setOnAction(e -> switchObstacleDisplay());
        arsenalSelector.getChildren().add(obstacleButton);
        return arsenalSelector;
    }

    private void setArsenalDisplay(Map<Integer, Info> currArsenal, double arsenalWidth) {
        try {
            for (int i = 0; i < currArsenal.size(); i++) {
                Image image = new Image(new FileInputStream("resources/" + currArsenal.get(i).getImage()));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(arsenalWidth / 2);
                imageView.setFitHeight(arsenalWidth / 2);
                Tooltip t = new Tooltip("A Square");
                Tooltip.install(imageView, t);
                viewList.add(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObservableList<ImageView> items = FXCollections.observableArrayList(viewList);
        arsenalDisplay.setItems(items);

        //TODO: this definitely does not work
        arsenalDisplay.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("gimme dat");
                ImageView selected = (ImageView) arsenalDisplay.getSelectionModel().getSelectedItem();
                Dragboard db = selected.startDragAndDrop(TransferMode.ANY);

                /* Put a string on a dragboard */
                ClipboardContent content = new ClipboardContent();
                content.put(DataFormat.IMAGE,selected);
                db.setContent(content);
                mouseEvent.consume();
//                lastX = event.getSceneX();
//                lastY = event.getSceneY();
            }
        });

        arsenalDisplay.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                /* accept it only if it is not dragged from the same node
                 * and if it has a string data */
                System.out.println("we in");
                if (event.getDragboard().hasImage()) {
                    System.out.println("yes ba");
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.MOVE);
                }

                event.consume();
            }
        });

        arsenalDisplay.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("image Moved");
                ImageView selected = (ImageView) arsenalDisplay.getSelectionModel().getSelectedItem();

                /* Put a string on a dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(selected.toString());
                mouseEvent.consume();
            }
        });

        arsenalDisplay.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("image set");
//                myLogic.instantiateWeapon();ImageView@534739bf[styleClass=image-view]
            }
        });


    }


    private void switchWeaponDisplay(){
        if (!isWeapon) {
            //TODO: implement display switch
            viewList.clear();
            setArsenalDisplay(myTestWeapons, myArsenalWidth);
            isWeapon = true;
        }
    }

    private void switchObstacleDisplay(){
        if (isWeapon) {
            //TODO: implement display switch
            viewList.clear();
            setArsenalDisplay(myTestObstacles, myArsenalWidth);
            isWeapon = false;
        }
    }


    public ArrayList<TreeItem> getArsenal(){
        ArrayList<TreeItem> myArsenal = new ArrayList<TreeItem>();
        //TODO: Iterate through a list of things users can implement
        TreeItem myWeapons = new TreeItem("Weapons");
        myWeapons.getChildren().addAll(getWeapons(myArsenal));

        TreeItem myObstacles = new TreeItem("Obstacles");
        myObstacles.getChildren().addAll(getObstacles());
        myArsenal.add(myWeapons);
        myArsenal.add(myObstacles);
        return myArsenal;
    }

    private ArrayList<TreeItem> getWeapons(List arsenal){
        ArrayList<TreeItem> weapons = new ArrayList<>();
        for (int i = 0; i < arsenal.size(); i++){
//            String weaponName = arsenal.get(i).get;
//            TreeItem tower = new TreeItem(weaponName);
//            weapons.add(tower);
        }
        return weapons;
    }

    private ArrayList<TreeItem> getObstacles(){
        ArrayList<TreeItem> obstacles = new ArrayList<>();
        //TODO: also should iterate through list
        TreeItem obstacle1 = new TreeItem("barrier");
        TreeItem obstacle2 = new TreeItem("yikes");
        TreeItem obstacle3 = new TreeItem("gang");
        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        return obstacles;
    }

    //TEST DATA
    private void createTestWeaponArsenal(){
        Info testInfo = new Info("test", "weapon.png");
        myTestWeapons = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            myTestWeapons.put(i, testInfo);
        }
    }

    private void createTestObstacleArsenal(){
        Info testInfo = new Info("test", "obstacle.png");
        myTestObstacles = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            myTestObstacles.put(i, testInfo);
        }
    }
}
