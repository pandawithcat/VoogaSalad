package Player.GamePlay.GamePlayLeft;

import BackendExternal.Logic;
import Configs.Info;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    private ImageView selectedImage;


    private Map <Integer, Info> myTestWeapons ;
    private Map <Integer, Info> myTestObstacles ;


    //list of WeaponInfo objects which has ID and an imageview
    private Map<Integer, Info> myArsenal;

    public GamePlayArsenal(double arsenalWidth, double arsenalHeight, Logic logic) throws FileNotFoundException {
        myArsenalWidth = arsenalWidth;
        //initialize weapon display first
        isWeapon = true;
        myLogic = logic;
        myArsenal = logic.getMyArsenal();
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
        myArsenalSelector = new GamePlayArsenalSelector(arsenalWidth,arsenalHeight * SELECTOR_RATIO);
        getChildren().add(myArsenalSelector);
    }

    private void setArsenalDisplay(Map<Integer, Info> currArsenal, double arsenalWidth) {
        try {
            for (Integer id : myArsenal.keySet()) {
                Image image = new Image(new FileInputStream("resources/" + myArsenal.get(id).getImage()));
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
        arsenalDisplay.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedImage = (ImageView)arsenalDisplay.getSelectionModel().getSelectedItem();
            }
        });

        //TODO: this definitely does not work
        selectedImage.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("we are dragging");
                Dragboard db = arsenalDisplay.startDragAndDrop(TransferMode.ANY);

                /* Put a string on a dragboard */
                ClipboardContent content = new ClipboardContent();
                content.put(DataFormat.IMAGE,selectedImage);
                db.setContent(content);
                System.out.println(selectedImage);
                System.out.println(content);
                mouseEvent.consume();
//                lastX = event.getSceneX();
//                lastY = event.getSceneY();
            }
        });

        selectedImage.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                System.out.println("drag over");
                /* data is dragged over the target */
                /* accept it only if it is not dragged from the same node
                 * and if it has a string data */
                System.out.println(event.getDragboard());
                System.out.println(event.getGestureSource());
                System.out.println(event.getGestureSource());
                System.out.println(event.getDragboard().getDragView());
                System.out.println(event.getDragboard().getImage());


                if (event.getGestureSource() != selectedImage && event.getDragboard().hasImage()) {
                    System.out.println(event.getDragboard().getImage());
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }

                event.consume();
            }
        });

        arsenalDisplay.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                System.out.println("Complete Drag: " + db);
                boolean success = false;
                if (db.hasImage()) {
                    myLogic.instantiateWeapon(1,5,5);
                    success = true;
                }
                /* let the source know whether the string was successfully
                 * transferred and used */
                event.setDropCompleted(success);

                event.consume();
            }
        });

//        arsenalDisplay.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                System.out.println("image Moved");
//                ImageView selected = (ImageView) arsenalDisplay.getSelectionModel().getSelectedItem();
//
//                /* Put a string on a dragboard */
//                ClipboardContent content = new ClipboardContent();
//                content.putString(selected.toString());
//                mouseEvent.consume();
//            }
//        });
//
//        arsenalDisplay.setOnMouseReleased(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                System.out.println("image set");
////                myLogic.instantiateWeapon();ImageView@534739bf[styleClass=image-view]
//            }
//        });


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
