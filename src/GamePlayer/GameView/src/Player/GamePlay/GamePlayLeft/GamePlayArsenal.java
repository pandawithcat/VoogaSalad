package Player.GamePlay.GamePlayLeft;

import BackendExternal.Logic;
import Configs.Info;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
    private ImageView movingImage;
    private GamePlayMap myMap;
    private Group myRoot;


    private Map <Integer, Info> myTestWeapons ;
    private Map <Integer, Info> myTestObstacles ;


    //list of WeaponInfo objects which has ID and an imageview
    private Map<Integer, Info> myArsenal;

    public GamePlayArsenal(double arsenalWidth, double arsenalHeight, Logic logic, GamePlayMap map, Group root) throws FileNotFoundException {
        myArsenalWidth = arsenalWidth;
        //initialize weapon display first
        isWeapon = true;
        myLogic = logic;
        myMap = map;
        myArsenal = logic.getMyArsenal();
        myRoot = root;
        arsenalDisplay = new ListView();
        arsenalDisplay.setPrefHeight(arsenalHeight * ARSENAL_RATIO);
        arsenalDisplay.setPrefWidth(arsenalWidth);

        //START TEST STUFF
        createTestWeaponArsenal();
        createTestObstacleArsenal();
        viewList = new ArrayList<>();
        setArsenalDisplay(myArsenal,arsenalWidth);


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

    private void setArsenalDisplay(Map<Integer, Info> arsenal, double arsenalWidth) {
        try {
            for (Integer id: arsenal.keySet()) {
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


        arsenalDisplay.setOnDragDetected(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectedImage = (ImageView) arsenalDisplay.getSelectionModel().getSelectedItem();
                Dragboard db = selectedImage.startDragAndDrop(TransferMode.ANY);

                //creates deepcopy of imageview
                var imageCopy = selectedImage.getImage();
                PixelReader pixelReader = imageCopy.getPixelReader();

                int width = (int)imageCopy.getWidth();
                int height = (int)imageCopy.getHeight();

                //Copy from source to destination pixel by pixel
                WritableImage writableImage
                        = new WritableImage(width, height);
                PixelWriter pixelWriter = writableImage.getPixelWriter();

                for (int y = 0; y < height; y++){
                    for (int x = 0; x < width; x++){
                        Color color = pixelReader.getColor(x, y);
                        pixelWriter.setColor(x, y, color);
                    }
                }

                movingImage = new ImageView();
                movingImage.setImage(writableImage);
                movingImage.setFitWidth(myMap.getGridSize());
                movingImage.setFitHeight(myMap.getGridSize());

                myRoot.getChildren().add(movingImage);
                /* Put a string on a dragboard */
                ClipboardContent content = new ClipboardContent();
                content.putString(selectedImage.toString());
//                content.put(DataFormat.IMAGE,selectedImage);
                db.setContent(content);
                mouseEvent.consume();
            }
        });

        myMap.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data is dragged over the target */
                /* accept it only if it is not dragged from the same node
                 * and if it has a string data */
                System.out.println(event.getX());
                System.out.println(event.getY());
                movingImage.setTranslateX(event.getX() - movingImage.getBoundsInParent().getWidth()/2);
                movingImage.setTranslateY(event.getY() - movingImage.getBoundsInParent().getHeight()/2);
                if (event.getGestureSource() != myMap ) {
                    System.out.println(event.getDragboard().getImage());
                    /* allow for both copying and moving, whatever user chooses */
                    event.acceptTransferModes(TransferMode.COPY);
                }
                event.consume();
            }
        });

        myMap.setOnDragEntered(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* the drag-and-drop gesture entered the target */
                System.out.println("onDragEntered");
                /* show to the user that it is an actual gesture target */
                if (event.getGestureSource() != myMap &&
                        event.getDragboard().hasString()) {
                    System.out.println("in the map");
                }

                event.consume();
            }
        });

        myMap.setOnDragExited(new EventHandler <DragEvent>() {
            public void handle(DragEvent event) {
                /* mouse moved away, remove the graphical cues */
                System.out.println("we out the map");
                event.consume();
            }
        });

        myMap.setOnDragDropped(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {
                /* data dropped */
                /* if there is a string data on dragboard, read it and use it */
                Dragboard db = event.getDragboard();
                System.out.println("Complete Drag: " + db);
                boolean success = false;
                if (db.hasImage()) {
                    myLogic.instantiateWeapon(1,5,5);
                    System.out.println("created weapon");
                    success = true;
                }
                if (db.hasString()){
                    System.out.println("X: " + event.getX());
                    System.out.println("Y: " + event.getY());
                    myLogic.instantiateWeapon(1, event.getX(),event.getY());
                    movingImage.setTranslateX(event.getX());
                    movingImage.setTranslateY(event.getY());
                    myRoot.getChildren().remove(movingImage);
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
